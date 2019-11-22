package zhan.rnadomgirdanimation.refresh;

import android.os.Handler;

import java.lang.ref.WeakReference;

public class RefreshTask implements Runnable {

    private WeakReference<RefreshHelper> mWeakRefHelper;
    private WeakReference<Handler> mWeakRefHandler;

    public RefreshTask(RefreshHelper helper, Handler handler) {
        mWeakRefHelper = new WeakReference<>(helper);
        mWeakRefHandler = new WeakReference<>(handler);
    }

    @Override
    public void run() {
        RefreshHelper helper = mWeakRefHelper.get();
        if (helper != null) {
            helper.generateNewData();

            Handler handler = mWeakRefHandler.get();
            if (handler != null && helper.isRunning()) {
                handler.postDelayed(this, helper.getRefreshTime());
            }
        }
    }
}
