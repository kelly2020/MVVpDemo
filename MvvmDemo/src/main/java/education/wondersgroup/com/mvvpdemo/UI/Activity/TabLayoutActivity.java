package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import education.wondersgroup.com.mvvpdemo.R;

/**
 * Created by zhangwentao on 16/9/22.
 * Description :tabLayout 使用
 * Version :1.0
 */
public class TabLayoutActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //方式1 : 代码里面添加
        tabLayout = new TabLayout(this);

        tabLayout.setLayoutParams(new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);//tabLayout 显示位置 center（居中） fill(平铺)

        tabLayout.setTabTextColors(Color.BLACK, Color.RED);//(默认颜色，选中颜色)

        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));//指示器颜色
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//MODE_SCROLLABLE tab可以进行滑动 MODE_FIXED tab 固定

        tabLayout.setSelectedTabIndicatorHeight(0);//指示器不显示

        for (int i = 0; i < 8; i++) {

//            //添加text
//            tabLayout.addTab(tabLayout.newTab().setText("tab" + i));
//
//            //添加icon
//            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));

            //添加view
            View view = View.inflate(this, R.layout.tab_layout_item, null);
            TextView textView = (TextView) view.findViewById(R.id.tab_text);
            ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);

            if (i == 0) {
                icon.setImageResource(R.mipmap.heart);
                textView.setText("收藏");
            } else if (i == 1) {
                icon.setImageResource(R.mipmap.play_icon);
                textView.setText("视频");
            } else if (i == 2) {
                icon.setImageResource(R.mipmap.heart);
                textView.setText("收藏");
            } else if (i == 4) {
                icon.setImageResource(R.mipmap.play_icon);
                textView.setText("视频");
            } else if (i == 5) {
                icon.setImageResource(R.mipmap.heart);
                textView.setText("收藏");
            } else if (i == 6) {
                icon.setImageResource(R.mipmap.heart);
                textView.setText("收藏");
            } else if (i == 7) {
                icon.setImageResource(R.mipmap.play_icon);
                textView.setText("视频");
            }
            tabLayout.addTab(tabLayout.newTab().setCustomView(view));
        }

        setContentView(tabLayout);

        initListener();


        isSelected(tabLayout.getTabAt(0), true);
        //方式2 xml 文件引用

//        setContentView(R.layout.tab_layout);
//
//        initView();

    }

    private void initListener() {

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //tab 选中
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                isSelected(tabLayout.getTabAt(0), false);
                isSelected(tab, true);

            }

            //tab 未选中
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                isSelected(tab, false);

            }
            //tab重新选中
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                isSelected(tab, true);
            }
        });
    }

    /**
     * 设置选中的tab是否带缩放效果
     *
     * @param tab
     * @param isSelected
     */
    private void isSelected(TabLayout.Tab tab, boolean isSelected) {
        View view = tab.getCustomView();
        ImageView icon = (ImageView) tab.getCustomView().findViewById(R.id.tab_icon);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);

        //当前选中tab 变大 如果未选中则恢复之前大小
        if (null != view) {
            view.setScaleX(isSelected ? 1.3f : 1.0f);
            view.setScaleY(isSelected ? 1.3f : 1.0f);
        }

        // 根据 tab 选择状态图标和文字对应变化
        if (null != icon && null != textView) {
            //选中tab 和未选中 tab 文字颜色 变化
            textView.setTextColor(isSelected ? getResources().getColor(R.color.colorPrimary) : getResources().getColor(R.color.gary));
            if (tab.getPosition() == 0) {
                //选中tab 和未选中tab icon 变化
                icon.setImageResource(isSelected ? R.mipmap.heart_red : R.mipmap.heart);
            } else if (tab.getPosition() == 1) {
                icon.setImageResource(isSelected ? R.mipmap.play_green : R.mipmap.play_icon);
            } else if (tab.getPosition() == 2) {
                icon.setImageResource(isSelected ? R.mipmap.heart_red : R.mipmap.heart);
            } else if (tab.getPosition() == 4) {
                icon.setImageResource(isSelected ? R.mipmap.play_green : R.mipmap.play_icon);
            } else if (tab.getPosition() == 5) {
                icon.setImageResource(isSelected ? R.mipmap.heart_red : R.mipmap.heart);
            } else if (tab.getPosition() == 6) {
                icon.setImageResource(isSelected ? R.mipmap.heart_red : R.mipmap.heart);
            } else if (tab.getPosition() == 7) {
                icon.setImageResource(isSelected ? R.mipmap.play_green : R.mipmap.play_icon);
            }
        }
    }

    //
    private void initView() {
//       TabItem tabItem = (TabItem) findViewById(R.id.tab_item_layout);

//        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }
}
