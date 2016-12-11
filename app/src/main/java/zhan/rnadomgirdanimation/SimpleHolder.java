package zhan.rnadomgirdanimation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zhan on 2016/12/11.
 */

public class SimpleHolder extends RecyclerView.ViewHolder {

  private ImageView mIconIv;

  public SimpleHolder(View itemView) {
    super(itemView);

    mIconIv = (ImageView) itemView.findViewById(R.id.icon_iv);
  }

  public void bind(Integer resId) {
    mIconIv.setImageResource(resId);
  }
}
