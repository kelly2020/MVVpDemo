package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import education.wondersgroup.com.mvvpdemo.R;
import education.wondersgroup.com.mvvpdemo.minterface.RecycelerViewListener;
import education.wondersgroup.com.mvvpdemo.util.CommonAdapter;
import education.wondersgroup.com.mvvpdemo.util.DividerItemDecoration;
import education.wondersgroup.com.mvvpdemo.util.ViewHolder;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;

/**
 * Created by zhangwt on 16/9/5.
 */
public class BaseRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener, RecycelerViewListener {
    private RecyclerView recyclerView;
    private Button delete;
    private Button add;

    private RecycleViewAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);

        initDatas();

        initView();

        initListener();

    }

    private void initListener() {
        delete.setOnClickListener(this);
        add.setOnClickListener(this);

        adapter.setOnClickListener(this);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        delete = (Button) findViewById(R.id.delete);
        add = (Button) findViewById(R.id.add);


//        listview 样式显示
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));

//            网格样式显示
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));


        //瀑布流样式
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));//显示样式
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));//item 分割线

//        recyclerView.setItemAnimator(new ScaleInOutItemAnimator(recyclerView));//item  自带伸缩动画
//        recyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(recyclerView));//item  自带伸缩动画并且从右边进入和移除
//            recyclerView.setItemAnimator(new SlideInOutRightItemAnimator(recyclerView));//从右边进入和移除动画
//            recyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(recyclerView));//从左边边进入和移除动画
//            recyclerView.setItemAnimator(new SlideInOutTopItemAnimator(recyclerView));//从上边边进入和移除动画
        recyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(recyclerView));//从下边边进入和移除动画

        adapter = new RecycleViewAdapter(datas);


        recyclerView.setAdapter(adapter);


//        AlphaAnimatorAdapter alphaAdapter = new AlphaAnimatorAdapter(adapter,recyclerView);// alpha 动画
//        recyclerView.setAdapter(alphaAdapter);

    }

    private void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("i ...." + i);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == add) {
            adapter.addItem(4);
        } else if (v == delete) {
//            adapter.deleteItem(2);

            startActivity(new Intent(this, MultiRecyclerViewActivity.class));
        }
    }

    @Override
    public void setOnItemClickListener(View view, int position) {
        Toast.makeText(this, "点击事件" + position, Toast.LENGTH_SHORT).show();
//        adapter.deleteItem(position);
    }

    @Override
    public void setOnItemLongClickListener(View view, int position) {
        Toast.makeText(this, "长按点击事件" + position, Toast.LENGTH_SHORT).show();
    }


    /**
     * recyclerView adapter
     */
    class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

        private List<String> mdatas;

        //通过回调为recyclerView 添加监听
        private RecycelerViewListener recyclerViewListener;

        public void setOnClickListener(RecycelerViewListener listener) {
            recyclerViewListener = listener;
        }

        RecycleViewAdapter(List<String> datas) {
            mdatas = datas;
        }

        /**
         * 获取ViewHolder 以及item布局
         *
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(BaseRecyclerViewActivity.this).inflate(R.layout.recycler_item, parent, false));
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(BaseRecyclerViewActivity.this).inflate(R.layout.card_view_item, parent, false));
            return myViewHolder;
        }


        /**
         * 获取viewHolder 中布局 控件
         *
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
//            holder.tv.setText(mdatas.get(position));
            holder.setData(mdatas.get(position));
//            holder.tv.setHeight((position + 1) * 10);

            //为recyclerview 添加监听事件
            if (recyclerViewListener != null) {

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerViewListener.setOnItemClickListener(holder.itemView, holder.getLayoutPosition());
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        recyclerViewListener.setOnItemLongClickListener(holder.itemView, holder.getLayoutPosition());
                        return true;
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            if (mdatas == null)
                return 0;
            return mdatas.size();
        }

        /**
         * 添加item
         *
         * @param position
         */
        public void addItem(int position) {
            mdatas.add(position, "哈哈哈");
            notifyItemInserted(position);
        }

        /**
         * 删除item
         *
         * @param position
         */
        public void deleteItem(int position) {
            mdatas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mdatas.size() - position);
        }

        /**
         * 获取布局文件控件
         */
        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
//                tv = (TextView) itemView.findViewById(R.id.textview);
                tv = (TextView) itemView.findViewById(R.id.card_view_text);

            }

            public void setData(String data) {
                tv.setText(data);
            }
        }
    }
}
