package education.wondersgroup.com.mvvpdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import education.wondersgroup.com.mvvpdemo.R;

/**
 * Created by zhangwentao on 16/9/27.
 * Description :
 * Version :
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList,Context context) {
        super(fm);
        fragments = fragmentList;
        mContext = context;
    }


    @Override
    public int getCount() {
        if (null == fragments){
            return 0;
        }
        return fragments.size();
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_layout_item, null, false);
        return view;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}
