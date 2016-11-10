package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import education.wondersgroup.com.mvvpdemo.R;
import education.wondersgroup.com.mvvpdemo.UI.Activity.fragment.Tab1Fragment;
import education.wondersgroup.com.mvvpdemo.UI.Activity.fragment.Tab2Fragment;
import education.wondersgroup.com.mvvpdemo.UI.Activity.fragment.TabFragment;
import education.wondersgroup.com.mvvpdemo.adapter.ViewPagerAdapter;

/**
 * Created by zhangwentao on 16/9/27.
 * Description :coordinatorLayout 使用
 * Version :1.0
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_coordinator_layout);

        initView();

        initListener();
    }

    private void initListener() {

    }

    private void initView() {
        TabFragment tabFragment = new TabFragment();
        Tab1Fragment tab1Fragment = new Tab1Fragment();
        Tab2Fragment tab2Fragment = new Tab2Fragment();

        fragments.add(tabFragment);
        fragments.add(tab1Fragment);
        fragments.add(tab2Fragment);

        tabLayout = (TabLayout) findViewById(R.id.coordinator_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.coordinator_viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, this);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // 给tablayout 添加每个tab 要从adapter 里面获取view 然后从tablayout 获取每个tab再分别添加
        // 因为这里已经使用了 tabLayout  viewpager fragment
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);
            tabAt.setCustomView(adapter.getTabView(i));
        }

    }
}
