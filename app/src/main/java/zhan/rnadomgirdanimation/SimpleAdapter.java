package zhan.rnadomgirdanimation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import zhan.rnadomgirdanimation.refresh.IRefreshAdapter;

/**
 * Created by zhan on 2016/12/11.
 */

public class SimpleAdapter extends IRefreshAdapter<Integer> {

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new SimpleHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((SimpleHolder)holder).bind(mData.get(position));
  }
}
