package zhan.rnadomgirdanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import zhan.rnadomgirdanimation.refresh.IDiffCallBack;
import zhan.rnadomgirdanimation.refresh.IRefreshAdapter;
import zhan.rnadomgirdanimation.refresh.RefreshHelper;
import zhan.rnadomgirdanimation.refresh.SimpleDiffCallBack;
import zhan.rnadomgirdanimation.widget.NoScrollRecyclerView;

/**
 * Created by zhan on 2016/12/11.
 */

public class StartActivity extends AppCompatActivity {

  private NoScrollRecyclerView list;

  private RefreshHelper<Integer> mRefreshHelper;
  private IRefreshAdapter<Integer> mIRefreshAdapter;
  private IDiffCallBack<Integer> mIDiffCallBack;

  private List<Integer> mData = new ArrayList<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);

    initData();
    initRecyclerView();
  }

  private void initData() {
    Random random = new Random();
    for (int x = 0; x < 120; x++) {
      int value = random.nextInt(4);

      switch (value) {
        case 0:
          mData.add(R.mipmap.pkq01);
          break;

        case 1:
          mData.add(R.mipmap.xhl01);
          break;

        case 2:
          mData.add(R.mipmap.mwzz01);
          break;

        case 3:
          mData.add(R.mipmap.jng01);
          break;
      }
    }
  }

  private void initRecyclerView() {
    list = (NoScrollRecyclerView) findViewById(R.id.list);

    GridLayoutManager manager = new GridLayoutManager(this, 4);
    list.setLayoutManager(manager);

    //1. init
    mIRefreshAdapter = new SimpleAdapter();
    mIDiffCallBack = new SimpleDiffCallBack();
    mRefreshHelper = new RefreshHelper<Integer>(this, mIDiffCallBack, mIRefreshAdapter);

    //2.
    mRefreshHelper.setRecyclerView(list, 400);
    list.setAdapter(mIRefreshAdapter);

    //3. use helper set data, not adapter, called can auto start refresh task
    mRefreshHelper.setData(mData);
  }

  @Override protected void onResume() {
    super.onResume();

    if (mRefreshHelper != null) {
      mRefreshHelper.startRefreshTask();
    }
  }

  @Override protected void onPause() {
    super.onPause();

    //you must call stop task
    if (mRefreshHelper != null) {
      mRefreshHelper.stopRefreshTask();
    }
  }
}
