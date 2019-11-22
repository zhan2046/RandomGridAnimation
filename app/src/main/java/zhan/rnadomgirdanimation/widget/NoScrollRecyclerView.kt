package zhan.rnadomgirdanimation.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class NoScrollRecyclerView : RecyclerView {

    var isNoScroll: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        isNoScroll = true
    }

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        return if (isNoScroll) {
            false
        } else super.onInterceptTouchEvent(e)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean {
        return if (isNoScroll) {
            false
        } else super.onTouchEvent(e)
    }
}
