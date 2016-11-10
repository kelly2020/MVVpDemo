package education.wondersgroup.com.mvvpdemo.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by zhangwentao on 16/9/14.
 * Description :
 * Version :
 */
public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;

    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnRecyclerItemClickListener mItemClickListener;

    public MultiItemTypeAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;

        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int itemViewLayoutId = itemViewDelegate.getItemViewLayoutId();

        ViewHolder viewHolder = ViewHolder.createViewHolder(mContext, parent, itemViewLayoutId);

        onViewHolderCreated(viewHolder, viewHolder.getConvertView());

        return viewHolder;
    }

    public void convert(ViewHolder viewHolder, T t) {
        mItemViewDelegateManager.convert(viewHolder, t, viewHolder.getLayoutPosition());

    }

    public void onViewHolderCreated(ViewHolder holder, View itemView) {

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);

        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }

    public List<T> getDatas() {
        return mDatas;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType,ItemViewDelegate<T> itemViewDelegate){
        mItemViewDelegateManager.addDelegate(viewType,itemViewDelegate);
        return this;
    }

    protected void setListener(ViewGroup parent, final ViewHolder viewHolder, int itemType) {
        if (!isEnabled(itemType)) return;

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClickListener(v, viewHolder, viewHolder.getLayoutPosition());
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemLongClickListener(v, viewHolder, viewHolder.getLayoutPosition());
                }
                return true;
            }
        });

    }

    public interface OnRecyclerItemClickListener {
        void onItemClickListener(View view, RecyclerView.ViewHolder viewHolder, int position);

        void onItemLongClickListener(View view, RecyclerView.ViewHolder viewHolder, int position);

    }

    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        mItemClickListener = listener;
    }

}
