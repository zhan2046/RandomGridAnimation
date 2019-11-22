package zhan.rnadomgirdanimation

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import zhan.rnadomgirdanimation.refresh.IRefreshAdapter

class SimpleAdapter : IRefreshAdapter<Int>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SimpleHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SimpleHolder).bind(mData!![position])
    }
}
