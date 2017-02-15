package app.demo.wondersgroup.com.recyclerviewrefresh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import app.demo.wondersgroup.com.recyclerviewrefresh.R;
import app.demo.wondersgroup.com.recyclerviewrefresh.fragment.ItemFragment;
import app.demo.wondersgroup.com.recyclerviewrefresh.fragment.dummy.DummyContent;

/**
 * Created by zhangwentao on 2017/2/14.
 */

public class TabLayoutActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TabLayoutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.fruit_view_toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager_tablayout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        setSupportActionBar(toolbar);

        ItemFragment linearFragment = new ItemFragment().newInstance(1);
        ItemFragment gridViewFragment = new ItemFragment().newInstance(2);
        fragments.add(linearFragment);
        fragments.add(gridViewFragment);


        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return getResources().getStringArray(R.array.tabTitle)[position];
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
