package education.wondersgroup.com.mvvpdemo.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangwentao on 16/9/14.
 * Description :viewholder 封装
 * Version :1.0
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolder(Context context, View itemView) {
        super(itemView);
        mViews = new SparseArray<View>();
        mContext = context;
        mConvertView = itemView;
    }

    public static ViewHolder createViewHolder(Context context, View itemView) {
        ViewHolder viewHolder = new ViewHolder(context, itemView);
        return viewHolder;
    }

    public static ViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(context, itemView);
        return viewHolder;
    }

    /**
     * 根据view id 获取View
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }

}
