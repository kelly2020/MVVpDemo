package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import education.wondersgroup.com.mvvpdemo.R;
import education.wondersgroup.com.mvvpdemo.adapter.BottomSheetAdapter;

/**
 * Created by zhangwentao on 16/9/19.
 * Description : bottomSheetDialog 使用 底部弹出dialog
 * Version :1.0
 */
public class BottomSheetDialogActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addView;
    private List<String> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
        initDatas();
        initListener();
    }

    private void initListener() {
        addView.setOnClickListener(this);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("item" + i);
        }
    }

    private void initView() {
        addView = (Button) findViewById(R.id.add);
    }

    @Override
    public void onClick(View v) {
        if (v == addView) {
            RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.bottom_sheet_list, null);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            BottomSheetAdapter adapter = new BottomSheetAdapter(this, mDatas);

            recyclerView.setAdapter(adapter);

            //对话框实例
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(recyclerView);
            dialog.show();
        }
    }
}
