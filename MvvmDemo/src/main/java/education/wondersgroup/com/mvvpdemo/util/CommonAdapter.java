package education.wondersgroup.com.mvvpdemo.util;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by zhangwentao on 16/9/14.
 * Description :
 * Version :
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {
    private int mLayoutId;
    private Context mContext;
    private List<T> mDatas;
    private LayoutInflater mLayoutInflater;


    public CommonAdapter(Context context, List<T> datas, final int layoutId) {
        super(context, datas);
        mContext = context;
        mDatas = datas;

        mLayoutId = layoutId;

        mLayoutInflater = LayoutInflater.from(context);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder viewHolder, T t, int position) {
                CommonAdapter.this.convert(viewHolder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T t, int position);
}
