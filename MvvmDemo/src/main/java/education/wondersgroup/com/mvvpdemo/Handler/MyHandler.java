package education.wondersgroup.com.mvvpdemo.Handler;

import android.view.View;
import android.widget.Toast;

/**
 * Created by zhangwt on 16/9/2.
 */
public class MyHandler {
    public void myClick(View view){
        Toast.makeText(view.getContext(),"点击了",Toast.LENGTH_LONG).show();
    }
}
