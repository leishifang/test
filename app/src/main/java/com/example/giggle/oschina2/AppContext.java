package com.example.giggle.oschina2;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.giggle.oschina2.api.ApiHttpClient;
import com.example.giggle.oschina2.base.BaseApplication;
import com.example.giggle.oschina2.util.StringUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import org.kymjs.kjframe.bitmap.BitmapConfig;

import java.util.Properties;
import java.util.UUID;

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

        // Bitmap缓存地址
//        BitmapConfig.CACHEPATH = "OSChina2/imagecache";
    }
//
//    public boolean containsProperty(String key) {
//        Properties properties = getProperties();
//        return properties.containsKey(key);
//    }
//
//    public Properties getProperties() {
//        return AppConfig.getAppConfig(this).get();
//    }
//
//    public void setProperty(String key, String value) {
//        AppConfig.getAppConfig(this).set(key, value);
//    }
//
//    public void setProperties(Properties properties) {
//        AppConfig.getAppConfig(this).set(properties);
//    }
//
//    /**
//     * 获取cookie时传AppConfig.CONF_COOKIE
//     *
//     * @param key
//     * @return
//     */
//    public String getProperty(String key) {
//        return AppConfig.getAppConfig(this).get(key);
//    }
//
//    public void removeProperty(String... key) {
//        AppConfig.getAppConfig(this).remove(key);
//    }
//
//    /**
//     * 获取应用程序唯一标识
//     *
//     * @return
//     */
//    public String getAppId() {
//        String UID = getProperty(AppConfig.CONF_APP_UNIQUEID);
//        if (StringUtils.isEmpty(UID)) {
//            UID = UUID.randomUUID().toString();
//            setProperty(AppConfig.CONF_APP_UNIQUEID, UID);
//        }
//        return UID;
//    }
//
//    public PackageInfo getPackageInfo() {
//        PackageInfo info = null;
//        try {
//            info = getPackageManager().getPackageInfo(getPackageName(), 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (info == null) {
//            info = new PackageInfo();
//        }
//        return info;
//    }

    // TODO: 2015-10-29 保存登陆信息
    // TODO: 2015-10-29 更新用户信息
    // TODO: 2015-10-29 获取用户登陆信息
    // TODO: 2015-10-29 清除用户登陆信息
    // TODO: 2015-10-29 注销用户
    // TODO: 2015-10-29 getTweetDraft
    // TODO: 2015-10-29 setTweetDraft
    // TODO: 2015-10-29  getNoteDraft
    // TODO: 2015-10-29  setNoteDraft
    // TODO: 2015-10-29 清除APP缓存

//    /**
//     * 清除cookie
//     */
//    public void cleanCookie() {
//        removeProperty(AppConfig.CONF_COOKIE);
//    }
//
//    public static void setLoadImage(boolean flag) {
//        set(AppConfig.KEY_LOAD_IMAGE, flag);
//    }
//
//    /**
//     * 判断当前版本是否兼容目标版本的方法
//     *
//     * @param VersionCode
//     * @return
//     */
//    public static boolean isMethodsCompat(int VersionCode) {
//        int currentVersion = android.os.Build.VERSION.SDK_INT;
//        return currentVersion >= VersionCode;
//    }
//
//    public static boolean isFirstStart() {
//        return getPreferences().getBoolean(AppConfig.KEY_FRITST_START, true);
//    }
//
//    public static void setFirstStart(boolean frist) {
//        set(AppConfig.KEY_FRITST_START, frist);
//    }
//
//    /**
//     * @return true:当前保存的是夜间模式 false:当前保存的是白天模式
//     */
//    public static boolean getNightModeSwitch() {
//        return getPreferences().getBoolean(AppConfig.KEY_NIGHT_MODE_SWITCH, false);
//    }
//
//    // 设置夜间模式
//    public static void setNightModeSwitch(boolean on) {
//        set(AppConfig.KEY_NIGHT_MODE_SWITCH, on);
//    }
}
