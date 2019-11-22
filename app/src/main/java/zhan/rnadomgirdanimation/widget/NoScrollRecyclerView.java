package zhan.rnadomgirdanimation.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class NoScrollRecyclerView extends RecyclerView {

    private boolean isNoScroll;

    public NoScrollRecyclerView(Context context) {
        super(context);
        init();
    }

    public NoScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        isNoScroll = true;
    }

    public void setNoScroll(boolean isNoScroll) {
        this.isNoScroll = isNoScroll;
    }

    public boolean isNoScroll() {
        return isNoScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (isNoScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(e);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (isNoScroll) {
            return false;
        }
        return super.onTouchEvent(e);
    }
}
