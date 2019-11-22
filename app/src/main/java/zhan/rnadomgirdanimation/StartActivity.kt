package zhan.rnadomgirdanimation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import zhan.rnadomgirdanimation.refresh.IDiffCallBack
import zhan.rnadomgirdanimation.refresh.IRefreshAdapter
import zhan.rnadomgirdanimation.refresh.RefreshHelper
import zhan.rnadomgirdanimation.refresh.SimpleDiffCallBack
import zhan.rnadomgirdanimation.widget.NoScrollRecyclerView
import java.util.*

class StartActivity : AppCompatActivity() {

    private var list: NoScrollRecyclerView? = null

    private var mRefreshHelper: RefreshHelper<Int>? = null
    private var mIRefreshAdapter: IRefreshAdapter<Int>? = null
    private var mIDiffCallBack: IDiffCallBack<Int>? = null

    private val mData = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        initData()
        initRecyclerView()
    }

    private fun initData() {
        val random = Random()
        for (x in 0..119) {
            when (random.nextInt(4)) {
                0 -> mData.add(R.mipmap.pkq01)

                1 -> mData.add(R.mipmap.xhl01)

                2 -> mData.add(R.mipmap.mwzz01)

                3 -> mData.add(R.mipmap.jng01)
            }
        }
    }

    private fun initRecyclerView() {
        list = findViewById(R.id.list)
        val manager = GridLayoutManager(this, 4)
        list!!.layoutManager = manager
        //1. init
        mIRefreshAdapter = SimpleAdapter()
        mIDiffCallBack = SimpleDiffCallBack()
        mRefreshHelper = RefreshHelper(this, mIDiffCallBack!!, mIRefreshAdapter!!)
        //2.
        mRefreshHelper!!.setRecyclerView(list!!, 400)
        list!!.adapter = mIRefreshAdapter
        //3. use helper set data, not adapter, called can auto start refresh task
        mRefreshHelper!!.setData(mData)
    }

    override fun onResume() {
        super.onResume()
        if (mRefreshHelper != null) {
            mRefreshHelper!!.startRefreshTask()
        }
    }

    override fun onPause() {
        super.onPause()
        //you must call stop task
        if (mRefreshHelper != null) {
            mRefreshHelper!!.stopRefreshTask()
        }
    }
}
