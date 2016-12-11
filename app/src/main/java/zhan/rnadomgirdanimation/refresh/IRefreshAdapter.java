package zhan.rnadomgirdanimation.refresh;

import android.support.v7.widget.RecyclerView;
import java.util.List;

/**
 * Created by zhan on 2016/12/11.
 */

public abstract class IRefreshAdapter<T> extends RecyclerView.Adapter {

  protected List<T> mData;

  public void setData(List<T> data) {
    mData = data;
  }

  @Override public int getItemCount() {
    return mData == null ? 0 : mData.size();
  }
}
