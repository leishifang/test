package com.example.giggle.oschina2.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.giggle.oschina2.AppConfig;
import com.example.giggle.oschina2.AppContext;
import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.api.OSChinaAPI;
import com.example.giggle.oschina2.base.BaseActivity;
import com.example.giggle.oschina2.bean.LoginResult;
import com.example.giggle.oschina2.util.TLog;
import com.example.giggle.oschina2.util.XmlUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by leishifang on 2016/3/22 15:39.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.tv_user_name)
    AutoCompleteTextView mTvUserName;
    @Bind(R.id.tv_password)
    EditText mTvPassword;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.iv_qq)
    ImageView mIvQq;
    @Bind(R.id.iv_wechat)
    ImageView mIvWechat;
    @Bind(R.id.iv_sina)
    ImageView mIvSina;

    AsyncHttpResponseHandler mResponseHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int i, Header[] headers, byte[] bytes) {

            LoginResult loginresult = XmlUtils.toBean(LoginResult.class, new ByteArrayInputStream(bytes));

            // TODO: 2016/3/26 对密码加密
            loginresult.getUser().setAccount(mTvUserName.getText().toString());
            loginresult.getUser().setPassword(mTvPassword.getText().toString());

            AppContext.getInstance().saveUserInfo(loginresult.getUser());
        }

        @Override
        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        }
    };

    @Override
    protected int getActionbarTitleID() {
        return R.string.login;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    // TODO: 2016/3/23 完成第三方登陆功能
    @OnClick({R.id.btn_login, R.id.iv_qq, R.id.iv_wechat, R.id.iv_sina})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginNormal();
                break;
            case R.id.iv_qq:
                Tencent tencent = Tencent.createInstance(AppConfig.QQ_APP_ID, this);
                tencent.login(this, "all", new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                    }

                    @Override
                    public void onError(UiError uiError) {
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
        }
    }

    private void loginNormal() {
        Bundle bundle = new Bundle();
        bundle.putString("username", mTvUserName.getText().toString().trim());
        bundle.putString("pwd", mTvPassword.getText().toString().trim());
        OSChinaAPI.login(bundle, mResponseHandler);
    }
}
