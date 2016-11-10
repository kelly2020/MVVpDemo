package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import education.wondersgroup.com.mvvpdemo.R;

/**
 * Created by zhangwentao on 16/9/22.
 * Description :NestedScrollView
 * Version :嵌套滑动
 */
public class NestedScrollViewActivity extends Activity {
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view);

        initData(1);
        initView();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.mipmap.back);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("Tab" + i));
        }


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initData(tab.getPosition() + 1);
                setScrollViewContent();

            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void setScrollViewContent() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.ll_sc_content);
        layout.removeAllViews();

        for (int i = 0; i < mData.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.recycler_item, null, false);
            TextView textView = (TextView) view.findViewById(R.id.textview);
            textView.setText(mData.get(i));
            layout.addView(view,i);
        }
    }



    private void initData(int pager) {
        mData = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }
}
