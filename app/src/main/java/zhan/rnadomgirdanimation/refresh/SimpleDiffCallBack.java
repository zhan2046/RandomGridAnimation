package zhan.rnadomgirdanimation.refresh;

/**
 * Created by zhan on 2016/12/11.
 */

public class SimpleDiffCallBack extends IDiffCallBack<Integer> {

  @Override public boolean isItemsTheSame(int oldItemPosition, int newItemPosition) {
    return mOldData.get(oldItemPosition) == mNewData.get(newItemPosition);
  }

  @Override public boolean isContentsTheSame(int oldItemPosition, int newItemPosition) {
    return mOldData.get(oldItemPosition) == mNewData.get(newItemPosition);
  }
}
