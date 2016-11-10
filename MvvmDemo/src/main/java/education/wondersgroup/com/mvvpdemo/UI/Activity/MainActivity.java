package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import education.wondersgroup.com.mvvpdemo.Model.User;
import education.wondersgroup.com.mvvpdemo.R;
import education.wondersgroup.com.mvvpdemo.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        User user = new User("kelly","pwd:123");
        binding.setUser(user);

    }

}
