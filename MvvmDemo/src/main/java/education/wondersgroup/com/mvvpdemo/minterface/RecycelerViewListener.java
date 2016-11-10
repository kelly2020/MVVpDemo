package education.wondersgroup.com.mvvpdemo.minterface;

import android.view.View;

/**
 * Created by zhangwt on 16/9/6.
 */
public interface RecycelerViewListener {
    public void setOnItemClickListener(View view,int position);
    public void setOnItemLongClickListener(View view,int position);
}
