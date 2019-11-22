package zhan.rnadomgirdanimation.refresh;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.WindowManager;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RefreshHelper<T> {

    private int mRefreshTime = 1000;//normal refresh time
    private int mRefreshSize = 6; //random refresh size
    private int mWidthSize = 4; //normal width show 4 item

    private Random mRandom;
    private IRefreshAdapter<T> mIRefreshAdapter;
    private IDiffCallBack<T> mIDiffCallBack;
    private RefreshCallBack mRefreshCallBack;

    private RefreshTask mRefreshTask;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private List<T> mData;
    private List<T> mOldData;
    private List<T> mNewData;

    private boolean isRunning;
    private boolean isReady;
    private int mItemSize;

    public RefreshHelper(Context context, IDiffCallBack<T> diffCallBack, IRefreshAdapter<T> adapter) {
        if (diffCallBack == null || adapter == null) {
            throw new RuntimeException("NoScrollHelper: diffCallBack or adapter is null");
        }
        mIRefreshAdapter = adapter;
        mRandom = new Random();
        mIDiffCallBack = diffCallBack;
        initAdapterItemSize(context);
    }

    /**
     * 初始化 Grid 行数与列数
     */
    private void initAdapterItemSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        float itemWidthPx = screenWidth / mWidthSize;
        int heightSize = Math.round(screenHeight / itemWidthPx + 0.5f);//四舍五入,宁可多一行,不留空白
        mItemSize = heightSize * mWidthSize;
    }

    public void setRecyclerView(RecyclerView recyclerView, int animationTime) {
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();

        //add remove animation time
        itemAnimator.setAddDuration(animationTime);
        itemAnimator.setRemoveDuration(animationTime);

        //hide change and move animation
        itemAnimator.setChangeDuration(0);
        itemAnimator.setMoveDuration(0);

        recyclerView.setItemAnimator(itemAnimator);
    }

    /**
     * 设置数据集
     */
    public void setData(List<T> data) {
        if (data == null) {
            return;
        }
        mData = data;

        if (mOldData == null) {
            mOldData = new ArrayList<>();
        }
        if (mNewData == null) {
            mNewData = new ArrayList<>();
        }
        mOldData.clear();
        mNewData.clear();

        for (int x = 0; x < mItemSize; x++) {
            int randomIndex = mRandom.nextInt(mData.size());
            mOldData.add(mData.get(randomIndex));
        }

        mNewData.addAll(mOldData);

        generateNewData();

        mIRefreshAdapter.setData(mOldData);
        mIRefreshAdapter.notifyDataSetChanged();

        //load data finish flag
        isReady = true;

        startRefreshTask();
    }

    public List<T> getOldData() {
        return mOldData;
    }

    public List<T> getNewData() {
        return mNewData;
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        mIDiffCallBack.setData(mOldData, mNewData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(mIDiffCallBack, false);

        if (mRefreshCallBack == null) {
            mRefreshCallBack = new RefreshCallBack(mIRefreshAdapter);
        }
        diffResult.dispatchUpdatesTo(mRefreshCallBack);
        mIRefreshAdapter.setData(mNewData);
    }

    /**
     * 生成新数据集
     */
    public void generateNewData() {
        if (mData == null || mOldData == null || mNewData == null) {
            return;
        }

        mOldData.clear();
        mOldData.addAll(mNewData);

        for (int x = 0; x < mRefreshSize; x++) {
            int randomIndex = mRandom.nextInt(mData.size());
            int randomOldIndex = mRandom.nextInt(mOldData.size());

            T bean = mData.get(randomIndex);
            mNewData.remove(randomOldIndex);
            mNewData.add(randomOldIndex, bean);
        }

        refreshData();
    }

    /**
     * 开始任务
     */
    public void startRefreshTask() {
        if (mRefreshTask == null) {
            mRefreshTask = new RefreshTask(this, mHandler);
        }
        stopRefreshTask();

        if (isReady) {//wait load data finish to can start
            mHandler.postDelayed(mRefreshTask, mRefreshTime);
            isRunning = true;
        }
    }

    /**
     * 停止任务
     */
    public void stopRefreshTask() {
        mHandler.removeCallbacks(mRefreshTask);
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isReady() {
        return isReady;
    }

    public int getRefreshTime() {
        return mRefreshTime;
    }

    public void setRefreshTime(int refreshTime) {
        this.mRefreshTime = refreshTime;
    }

    public int getRefreshSize() {
        return mRefreshSize;
    }

    public void setRefreshSize(int refreshSize) {
        this.mRefreshSize = refreshSize;
    }

    public int getWidthSize() {
        return mWidthSize;
    }

    public void setWidthSize(int widthSize) {
        this.mWidthSize = widthSize;
    }
}
