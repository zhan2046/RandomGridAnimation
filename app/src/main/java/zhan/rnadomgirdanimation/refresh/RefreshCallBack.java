package zhan.rnadomgirdanimation.refresh;

import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

public class RefreshCallBack implements ListUpdateCallback {

    private WeakReference<RecyclerView.Adapter> mWeakRefAdapter;

    public RefreshCallBack(RecyclerView.Adapter adapter) {
        mWeakRefAdapter = new WeakReference<>(adapter);
    }

    @Override
    public void onInserted(int position, int count) {
        RecyclerView.Adapter adapter = mWeakRefAdapter.get();
        if (adapter != null) {
            adapter.notifyItemRangeInserted(position, count);
        }
    }

    @Override
    public void onRemoved(int position, int count) {
        RecyclerView.Adapter adapter = mWeakRefAdapter.get();
        if (adapter != null) {
            adapter.notifyItemRangeRemoved(position, count);
        }
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        RecyclerView.Adapter adapter = mWeakRefAdapter.get();
        if (adapter != null) {
            adapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override
    public void onChanged(int position, int count, Object payload) {
        RecyclerView.Adapter adapter = mWeakRefAdapter.get();
        if (adapter != null) {
            adapter.notifyItemRangeChanged(position, count, payload);
        }
    }
}
