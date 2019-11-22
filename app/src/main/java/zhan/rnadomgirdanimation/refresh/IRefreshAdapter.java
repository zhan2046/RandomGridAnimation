package zhan.rnadomgirdanimation.refresh;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class IRefreshAdapter<T> extends RecyclerView.Adapter {

    protected List<T> mData;

    public void setData(List<T> data) {
        mData = data;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
