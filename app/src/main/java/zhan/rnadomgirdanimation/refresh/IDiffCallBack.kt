package zhan.rnadomgirdanimation.refresh

import androidx.recyclerview.widget.DiffUtil

abstract class IDiffCallBack<T> : DiffUtil.Callback() {

    protected var mOldData: List<T>? = null
    protected var mNewData: List<T>? = null

    fun setData(oldData: List<T>, newData: List<T>) {
        mOldData = oldData
        mNewData = newData
    }

    override fun getOldListSize(): Int {
        return if (mOldData == null) 0 else mOldData!!.size
    }

    override fun getNewListSize(): Int {
        return if (mNewData == null) 0 else mNewData!!.size
    }

    abstract fun isItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return isItemsTheSame(oldItemPosition, newItemPosition)
    }

    abstract fun isContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return isContentsTheSame(oldItemPosition, newItemPosition)
    }
}
