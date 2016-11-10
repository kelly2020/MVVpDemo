package education.wondersgroup.com.mvvpdemo.UI.Activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import education.wondersgroup.com.mvvpdemo.UI.Activity.CoordinatorLayoutActivity;

/**
 * Created by zhangwentao on 16/9/27.
 * Description :
 * Version :
 */
public class TabFragment extends Fragment {
    private CoordinatorLayoutActivity coordinatorActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        coordinatorActivity = (CoordinatorLayoutActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(coordinatorActivity);
        textView.setText("哈哈哈哈");
        return textView;
    }
}
