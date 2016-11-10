package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import education.wondersgroup.com.mvvpdemo.R;

/**
 * Created by zhangwentao on 16/9/22.
 * Description :qq 登陆
 * Version :1.0
 */
public class QQLoginActivity extends AppCompatActivity {
    private static final String APPID = "1105525670";

    private Tencent mTencent; //qq主操作对象
    private IUiListener loginListener; //授权登录监听器
    private IUiListener userInfoListener; //获取用户信息监听器
    private String scope; //获取信息的范围参数
    private UserInfo userInfo; //qq用户信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_login);
        setupViews();
        initData();
    }

    @Override
    protected void onDestroy() {
        if (mTencent != null) {
            //注销登录
            mTencent.logout(this);
        }
        super.onDestroy();
    }

    private void setupViews() {
//        et1 = (EditText) findViewById(R.id.editText1);
        Button btn = (Button) findViewById(R.id.qq_login_btn);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("你点击了使用qq登录按钮");
                login();
            }
        });

//        findViewById(R.id.button3).setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                System.out.println("开始获取用户信息");
//                userInfo = new UserInfo(MainActivity.this, mTencent.getQQToken());
//                userInfo.getUserInfo(userInfoListener);
//            }
//        });
    }

    private void initData() {
        //初始化qq主操作对象
        mTencent = Tencent.createInstance(APPID, this);
        //要所有权限，不然会再次申请增量权限，这里不要设置成get_user_info,add_t
        scope = "all";

        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                // TODO Auto-generated method stub

            }

            /**
             * 返回json数据样例
             *
             * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
             * "pf":"desktop_m_qq-10000144-android-2002-",
             * "query_authority_cost":448,
             * "authority_cost":-136792089,
             * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
             * "expires_in":7776000,
             * "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
             * "msg":"",
             * "access_token":"A2455F491478233529D0106D2CE6EB45",
             * "login_cost":499}
             */
            @Override
            public void onComplete(Object value) {
                // TODO Auto-generated method stub

                System.out.println("有数据返回..");
                if (value == null) {
                    return;
                }

                try {
                    JSONObject jo = (JSONObject) value;

                    int ret = jo.getInt("ret");

                    System.out.println("json=" + String.valueOf(jo));

                    if (ret == 0) {
                        Toast.makeText(QQLoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();

                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");

                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);

//                        https://graph.qq.com/oauth2.0/me?access_token=ACCESSTOKEN&unionid=1
                        userInfo = new UserInfo(QQLoginActivity.this, mTencent.getQQToken());
                        userInfo.getUserInfo(userInfoListener);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        };

        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                // TODO Auto-generated method stub

            }

            /**
             * 返回用户信息样例
             *
             * {"is_yellow_year_vip":"0","ret":0,
             * "figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/40",
             * "figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
             * "nickname":"攀爬←蜗牛","yellow_vip_level":"0","is_lost":0,"msg":"",
             * "city":"黄冈","
             * figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/50",
             * "vip":"0","level":"0",
             * "figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
             * "province":"湖北",
             * "is_yellow_vip":"0","gender":"男",
             * "figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/30"}
             */
            @Override
            public void onComplete(Object arg0) {
                // TODO Auto-generated method stub
                if (arg0 == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) arg0;
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");

                    Toast.makeText(QQLoginActivity.this, "你好，" + nickName, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    // TODO: handle exception
                }


            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        };
    }

    private void login() {
        //如果session无效，就开始登录
        if (null != null && !mTencent.isSessionValid()) {
            //开始qq授权登录
            mTencent.login(QQLoginActivity.this, scope, loginListener);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_API) {
//            if (resultCode == Constants.RESULT_LOGIN) {
            Tencent.handleResultData(data, loginListener);
//            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
