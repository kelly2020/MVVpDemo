package education.wondersgroup.com.mvvpdemo.UI.Activity.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import education.wondersgroup.com.mvvpdemo.R;
import education.wondersgroup.com.mvvpdemo.UI.Activity.CoordinatorLayoutActivity;
import education.wondersgroup.com.mvvpdemo.adapter.BottomSheetAdapter;
import education.wondersgroup.com.mvvpdemo.util.DividerItemDecoration;

/**
 * Created by zhangwentao on 16/9/27.
 * Description :
 * Version :
 */
public class Tab2Fragment extends Fragment {
    private CoordinatorLayoutActivity coordinatorActivity;
    private RecyclerView recyclerView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        coordinatorActivity = (CoordinatorLayoutActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycler_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(coordinatorActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(coordinatorActivity,DividerItemDecoration.VERTICAL_LIST));

        List<String> list = new ArrayList<>();

        for (int i =0;i< 20;i++){
            list.add("data=" + i);
        }

        BottomSheetAdapter adapter = new BottomSheetAdapter(coordinatorActivity,list);
        recyclerView.setAdapter(adapter);

    }
}
