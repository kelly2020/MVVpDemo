package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import education.wondersgroup.com.mvvpdemo.R;

/**
 * Created by zhangwentao on 16/10/25.
 * Description :6.0 权限问题
 * Version :
 */
public class PermissionActivity extends AppCompatActivity {
    private Button openCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        openCamera = (Button) findViewById(R.id.open_camera);
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this,Manifest.permission.CAMERA)){
                        new AlertDialog.Builder(PermissionActivity.this).setMessage("申请相机权限").setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(PermissionActivity.this,
                                        new String []{Manifest.permission.CAMERA},1);
                            }
                        }).show();
                    } else {
                        ActivityCompat.requestPermissions(PermissionActivity.this, new String []{Manifest.permission.CAMERA},1);
                    }
                } else {
                    Toast.makeText(PermissionActivity.this,"已经有权限了",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(PermissionActivity.this,"可以打开相机了",Toast.LENGTH_SHORT).show();
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
                    Toast.makeText(PermissionActivity.this,"权限被禁止" ,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
