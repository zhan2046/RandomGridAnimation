package zhan.rnadomgirdanimation.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zhan on 2016/12/11.
 */

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

  @Override public boolean onInterceptTouchEvent(MotionEvent e) {
    if(isNoScroll) {
      return false;
    }
    return super.onInterceptTouchEvent(e);
  }

  @Override public boolean onTouchEvent(MotionEvent e) {
    if(isNoScroll) {
      return false;
    }
    return super.onTouchEvent(e);
  }
}
