package zhan.rnadomgirdanimation.refresh

import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

import java.lang.ref.WeakReference

class RefreshCallBack(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : ListUpdateCallback {

    private val mWeakRefAdapter: WeakReference<RecyclerView.Adapter<RecyclerView.ViewHolder>> = WeakReference(adapter)

    override fun onInserted(position: Int, count: Int) {
        val adapter = mWeakRefAdapter.get()
        adapter?.notifyItemRangeInserted(position, count)
    }

    override fun onRemoved(position: Int, count: Int) {
        val adapter = mWeakRefAdapter.get()
        adapter?.notifyItemRangeRemoved(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        val adapter = mWeakRefAdapter.get()
        adapter?.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        val adapter = mWeakRefAdapter.get()
        adapter?.notifyItemRangeChanged(position, count, payload)
    }
}
