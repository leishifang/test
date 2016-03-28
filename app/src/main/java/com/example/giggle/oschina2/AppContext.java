package com.example.giggle.oschina2;

import android.content.Context;

import com.example.giggle.oschina2.api.ApiHttpClient;
import com.example.giggle.oschina2.base.BaseApplication;
import com.example.giggle.oschina2.bean.User;
import com.loopj.android.http.AsyncHttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

@SuppressWarnings({"unused", "JavaDoc"})

/**
 *
 * Created by Administrator on 2015-10-27.
 */
public class AppContext extends BaseApplication {

    public static final int PAGE_SIZE = 20; //分页加载大小

    private static AppContext instance;
    private int loginUid;
    private boolean login;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        initLogin();
        // TODO: 2015-10-29  设置应用默认异常处理
        // TODO: 2015-10-29  send broadcast to UIhelper
    }

    public static AppContext getInstance() {
        return instance;
    }

    // TODO: 2015-10-29 初始化登陆状态
    private void initLogin() {
    }

    private void init() {
        // 初始化网络请求
        AsyncHttpClient client = new AsyncHttpClient();
//        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
//        client.setCookieStore(myCookieStore);
        ApiHttpClient.setHttpClient(client);
        ApiHttpClient.getHttpClient().setResponseTimeout(5000);
//        ApiHttpClient.setCookie(ApiHttpClient.getCookie(this));

        // TODO: 2015-11-27 初始化Log控制器

    }

    // TODO: 2015-10-29 保存登陆信息
    public void saveUserInfo(User user) {
        FileOutputStream fileOutputStream;
        try {
            File file = new File("config");
            fileOutputStream = this.openFileOutput("config", Context.MODE_PRIVATE);

            Properties properties = new Properties();
            properties.put("user.id", user.getUid());
            properties.put("user.name", user.getName());
            properties.put("user.account", user.getAccount());
            properties.put("user.pwd", user.getPassword());
            properties.put("user.face", user.getPortrait());

            Properties preProperties = getPreProperties();
            preProperties.putAll(properties);

            preProperties.store(fileOutputStream, null);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties getPreProperties() {

        Properties preProperties = new Properties();
        try {
            FileInputStream fileInputStream = this.openFileInput("config");
            preProperties.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return preProperties;
    }
    // TODO: 2015-10-29 更新用户信息
    // TODO: 2015-10-29 获取用户登陆信息
    // TODO: 2015-10-29 清除用户登陆信息
    // TODO: 2015-10-29 注销用户
    // TODO: 2015-10-29 getTweetDraft
    // TODO: 2015-10-29 setTweetDraft
    // TODO: 2015-10-29  getNoteDraft
    // TODO: 2015-10-29  setNoteDraft
    // TODO: 2015-10-29 清除APP缓存
}
