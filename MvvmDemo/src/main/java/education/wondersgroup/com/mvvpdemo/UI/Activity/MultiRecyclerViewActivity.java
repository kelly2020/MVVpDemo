package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import education.wondersgroup.com.mvvpdemo.Model.ChatMsg;
import education.wondersgroup.com.mvvpdemo.R;
import education.wondersgroup.com.mvvpdemo.util.DividerItemDecoration;
import education.wondersgroup.com.mvvpdemo.util.ItemViewDelegate;
import education.wondersgroup.com.mvvpdemo.util.MultiItemTypeAdapter;
import education.wondersgroup.com.mvvpdemo.util.ViewHolder;

/**
 * Created by zhangwentao on 16/9/14.
 * Description :多种item type 样式
 * Version :1.0
 */
public class MultiRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private List<ChatMsg> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
    }

    private void initView() {
        for (int i = 0; i < 10; i++) {
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.setContent("消息" + i);
            if (i % 2 == 0) {
                chatMsg.setType(true);
            }
            datas.add(chatMsg);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));

        MultiItemTypeAdapter<ChatMsg> adapter = new MultiItemTypeAdapter<>(this, datas);

        adapter.addItemViewDelegate(new MsgFromItemDelegate());
        adapter.addItemViewDelegate(new MsgComingDelegate());

        recyclerView.setAdapter(adapter);
    }


    class MsgFromItemDelegate implements ItemViewDelegate<ChatMsg> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_from;
        }

        @Override
        public boolean isForViewType(ChatMsg item, int position) {
            return item.isType();
        }

        @Override
        public void convert(ViewHolder viewHolder, ChatMsg s, int position) {
            Button view = viewHolder.getView(R.id.item_from_btn);
            view.setText(s.getContent());
        }
    }


    class MsgComingDelegate implements ItemViewDelegate<ChatMsg>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_coming;
        }

        @Override
        public boolean isForViewType(ChatMsg item, int position) {
            return item.isType();
        }

        @Override
        public void convert(ViewHolder viewHolder, ChatMsg chatMsg, int position) {
            Button view = viewHolder.getView(R.id.item_coming_btn);
            view.setText(chatMsg.getContent());
        }
    }

}
