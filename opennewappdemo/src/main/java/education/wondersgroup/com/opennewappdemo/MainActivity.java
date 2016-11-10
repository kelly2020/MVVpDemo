package education.wondersgroup.com.opennewappdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMarket(View view) {
//
        boolean appInstalled = isAppInstalled(this, "com.wondersgroup.ismileStudent");
        if (appInstalled) {
            //打开一个新的app 对应activity
            ComponentName componet = new ComponentName("com.wondersgroup.ismileStudent",
                    "com.wondersgroup.ismileStudent.SmileLauncherActivity");
            Intent intent = new Intent();
            intent.setComponent(componet);
            startActivity(intent);

        } else {
            //应用市场去下载app
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + "com.wondersgroup.ismileStudent"));
            startActivity(intent);
        }

    }

    /**
     * 判断app是否已经安装到手机上面
     * @param context
     * @param packageName
     * @return
     */
    public boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }
}
