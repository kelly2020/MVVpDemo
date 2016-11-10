package education.wondersgroup.com.mvvpdemo.util;

/**
 * Created by zhangwentao on 16/9/14.
 * Description :
 * Version :
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder viewHolder, T t, int position);

}
