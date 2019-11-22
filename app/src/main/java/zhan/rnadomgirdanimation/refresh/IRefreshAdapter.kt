package zhan.rnadomgirdanimation.refresh

import androidx.recyclerview.widget.RecyclerView

abstract class IRefreshAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var mData: List<T>? = null

    fun setData(data: List<T>) {
        mData = data
    }

    override fun getItemCount(): Int {
        return if (mData == null) 0 else mData!!.size
    }
}
