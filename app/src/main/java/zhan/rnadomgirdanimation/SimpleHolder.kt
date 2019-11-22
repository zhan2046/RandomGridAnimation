package zhan.rnadomgirdanimation

import android.view.View
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView

class SimpleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mIconIv: ImageView

    init {

        mIconIv = itemView.findViewById<View>(R.id.icon_iv) as ImageView
    }

    fun bind(resId: Int?) {
        mIconIv.setImageResource(resId!!)
    }
}
