package zhan.rnadomgirdanimation.refresh

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RefreshHelper<T>(context: Context, private val mIDiffCallBack: IDiffCallBack<T>,
                       private val mIRefreshAdapter: IRefreshAdapter<T>) {

    var refreshTime = 1000//normal refresh time
    var refreshSize = 6 //random refresh size
    var widthSize = 4 //normal width show 4 item

    private val mRandom: Random = Random()
    private var mRefreshCallBack: RefreshCallBack? = null

    private var mRefreshTask: RefreshTask? = null
    private val mHandler = Handler(Looper.getMainLooper())

    private var mData: List<T>? = null
    private var mOldData: MutableList<T>? = null
    private var mNewData: MutableList<T>? = null

    var isRunning: Boolean = false
        private set
    var isReady: Boolean = false
        private set
    private var mItemSize: Int = 0

    val oldData: List<T>?
        get() = mOldData

    val newData: List<T>?
        get() = mNewData

    init {
        initAdapterItemSize(context)
    }

    private fun initAdapterItemSize(context: Context) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val screenWidth = display.width
        val screenHeight = display.height

        val itemWidthPx = (screenWidth / widthSize).toFloat()
        val heightSize = Math.round(screenHeight / itemWidthPx + 0.5f)//四舍五入,宁可多一行,不留空白
        mItemSize = heightSize * widthSize
    }

    fun setRecyclerView(recyclerView: RecyclerView, animationTime: Int) {
        val itemAnimator = DefaultItemAnimator()
        //add remove animation time
        itemAnimator.addDuration = animationTime.toLong()
        itemAnimator.removeDuration = animationTime.toLong()

        //hide change and move animation
        itemAnimator.changeDuration = 0
        itemAnimator.moveDuration = 0
        recyclerView.itemAnimator = itemAnimator
    }

    fun setData(data: List<T>?) {
        if (data == null) {
            return
        }
        mData = data

        if (mOldData == null) {
            mOldData = ArrayList()
        }
        if (mNewData == null) {
            mNewData = ArrayList()
        }
        mOldData!!.clear()
        mNewData!!.clear()
        for (x in 0 until mItemSize) {
            val randomIndex = mRandom.nextInt(mData!!.size)
            mOldData!!.add(mData!![randomIndex])
        }
        mNewData!!.addAll(mOldData!!)
        generateNewData()

        mIRefreshAdapter.setData(mOldData!!)
        mIRefreshAdapter.notifyDataSetChanged()
        //load data finish flag
        isReady = true
        startRefreshTask()
    }

    private fun refreshData() {
        mIDiffCallBack.setData(mOldData!!, mNewData!!)
        val diffResult = DiffUtil.calculateDiff(mIDiffCallBack, false)

        if (mRefreshCallBack == null) {
            mRefreshCallBack = RefreshCallBack(mIRefreshAdapter)
        }
        diffResult.dispatchUpdatesTo(mRefreshCallBack!!)
        mIRefreshAdapter.setData(mNewData!!)
    }

    fun generateNewData() {
        if (mData == null || mOldData == null || mNewData == null) {
            return
        }
        mOldData!!.clear()
        mOldData!!.addAll(mNewData!!)
        for (x in 0 until refreshSize) {
            val randomIndex = mRandom.nextInt(mData!!.size)
            val randomOldIndex = mRandom.nextInt(mOldData!!.size)

            val bean = mData!![randomIndex]
            mNewData!!.removeAt(randomOldIndex)
            mNewData!!.add(randomOldIndex, bean)
        }
        refreshData()
    }

    fun startRefreshTask() {
        if (mRefreshTask == null) {
            mRefreshTask = RefreshTask(this, mHandler)
        }
        stopRefreshTask()
        if (isReady) {//wait load data finish to can start
            mHandler.postDelayed(mRefreshTask, refreshTime.toLong())
            isRunning = true
        }
    }

    fun stopRefreshTask() {
        mHandler.removeCallbacks(mRefreshTask)
        isRunning = false
    }
}
