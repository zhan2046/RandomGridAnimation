package zhan.rnadomgirdanimation;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

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
