package education.wondersgroup.com.mvvpdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import education.wondersgroup.com.mvvpdemo.R;

/**
 * Created by zhangwentao on 16/9/19.
 * Description :
 * Version :
 */
public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.SheetViewHolder> {
    private Context mContext;
    private List<String> mDatas;

    public BottomSheetAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }


    @Override
    public SheetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SheetViewHolder viewHolder = new SheetViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SheetViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class SheetViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public SheetViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
