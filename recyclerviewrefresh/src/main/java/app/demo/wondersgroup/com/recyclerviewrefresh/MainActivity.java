package app.demo.wondersgroup.com.recyclerviewrefresh;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.demo.wondersgroup.com.recyclerviewrefresh.adapter.RecyclerViewAdapter;
import app.demo.wondersgroup.com.recyclerviewrefresh.view.LoadMoreRecyclerView;

public class MainActivity extends AppCompatActivity {
    private LoadMoreRecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeLayout;
    private List<String> datas = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Handler mhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initListener() {
        //下拉刷新
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addDatas();
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                        mSwipeLayout.setRefreshing(false);
                    }
                },2500);
            }
        });
        // 上拉加载更多
        mRecyclerView.setOnLoadingListener(new LoadMoreRecyclerView.onLoadingMoreListener() {
            @Override
            public void onLoading() {
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreDatas();
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                        mRecyclerView.loadFinished();
                    }
                },2000);
            }
        });
    }

    private void loadMoreDatas() {
        for (int i = 0; i < 10; i++) {
            datas.add("添加列表数据i ==" + i);
        }
    }

    private void addDatas() {
        datas.clear();
        for (int i = 0; i < 10; i++) {
            datas.add("刷新列表数据i ==" + i);
        }
    }

    private void initView() {
        mRecyclerView = (LoadMoreRecyclerView) findViewById(R.id.recyclerview);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);

        mSwipeLayout.setColorSchemeColors(Color.BLUE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerViewAdapter(this, datas);
        mRecyclerView.setAdapter(adapter);

//        mSwipeLayout.setRefreshing(true);
    }
}
