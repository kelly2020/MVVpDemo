package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import education.wondersgroup.com.mvvpdemo.R;
import education.wondersgroup.com.mvvpdemo.adapter.BottomSheetAdapter;
import education.wondersgroup.com.mvvpdemo.util.DividerItemDecoration;

/**
 * Created by zhangwentao on 16/9/26.
 * Description :CollapsingToolBarLayout 的使用
 * Version :1.0
 */
public class CollapsingToolBarActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BottomSheetAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_collapsingtoolbar);

        initView();

        initData();
        initListener();
    }

    private void initListener() {

    }

    private void initData() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("hahah " + i);
        }
        adapter = new BottomSheetAdapter(this, datas);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {

        //添加toolBar 为当前activity 的actionBar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ImageView backImg = (ImageView) findViewById(R.id.toolbar_back_img);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tool_bar_layout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        //toolBar 的title 值 不能直接通过toolBar 来添加而是通过它所在的父容器 collapsingToolBarLayout 添加
        collapsingToolbarLayout.setTitle("Collapsing");
        // 标题 收缩的时候的颜色值
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);
        //标题 扩展开的颜色值
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);


    }


}
