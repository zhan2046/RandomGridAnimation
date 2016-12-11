package zhan.rnadomgirdanimation.refresh;

import android.support.v7.util.DiffUtil;
import java.util.List;

/**
 * Created by zhan on 2016/12/11.
 */

public abstract class IDiffCallBack<T> extends DiffUtil.Callback {

  protected List<T> mOldData;
  protected List<T> mNewData;

  public void setData(List<T> oldData, List<T> newData) {
    mOldData = oldData;
    mNewData = newData;
  }

  @Override public int getOldListSize() {
    return mOldData == null ? 0 : mOldData.size();
  }

  @Override public int getNewListSize() {
    return mNewData == null ? 0 : mNewData.size();
  }

  public abstract boolean isItemsTheSame(int oldItemPosition, int newItemPosition);

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return isItemsTheSame(oldItemPosition, newItemPosition);
  }

  public abstract boolean isContentsTheSame(int oldItemPosition, int newItemPosition);

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return isContentsTheSame(oldItemPosition, newItemPosition);
  }
}
