package com.example.giggle.oschina2.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.util.StringUtils;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.joanzapata.iconify.fonts.MeteoconsModule;
import com.joanzapata.iconify.fonts.SimpleLineIconsModule;
import com.joanzapata.iconify.fonts.TypiconsModule;
import com.joanzapata.iconify.fonts.WeathericonsModule;

/**
 * Created by Administrator on 2015-10-25.
 */
public class BaseApplication extends Application {
    private static String PRE_NAME = "creativelocker.pref";
    private static String LAST_REFRESH_TIME = "last_refresh_time.pref";
    static Context _context;
    static Resources _resource;
    private static String lastToast = "";
    private static long lastToastTime = 0;
    private static boolean sIsAtLeastGB;

    static {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            sIsAtLeastGB = true;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        _resource = _context.getResources();
        Iconify
                .with(new FontAwesomeModule())
                .with(new EntypoModule())
                .with(new TypiconsModule())
                .with(new MaterialModule())
                .with(new MaterialCommunityModule())
                .with(new MeteoconsModule())
                .with(new WeathericonsModule())
                .with(new SimpleLineIconsModule())
                .with(new IoniconsModule());
    }

    // TODO: 3 2015-10-27 为什么要加锁? 是否可以改用ThreadLocal()
    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    /**
     * 放入已读文章的列表中
     *
     * @param prefFileName
     * @param key
     * @param value
     */
    // TODO: 3 2015-10-27 方法名拼写有错误
    public static void putReadedPostList(String prefFileName, String key, String value) {
        SharedPreferences sharedpreferences = getPreferences(prefFileName);
        int size = sharedpreferences.getAll().size();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (size > 100) {
            editor.clear();//清除文件中的数据
        }
        editor.putString(key, value);
        apply(editor);
    }

    /**
     * 读取是否是已读的文章列表
     *
     * @param prefFileName
     * @param key
     * @return
     */
    // TODO: 2015-10-27 方法名拼写错误
    public static boolean isOnReadedPostList(String prefFileName, String key) {
        return getPreferences(prefFileName).contains(key);
    }

    /**
     * 记录列表上次刷新的时间
     *
     * @param prefFileName
     * @param key
     * @param value
     */
    public static void putToLastRefreshTime(String prefFileName, String key, String value) {
        SharedPreferences preferences = getPreferences(LAST_REFRESH_TIME);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        apply(editor);
    }

    /**
     * 获取列表上次刷新的时间
     *
     * @param key
     * @return
     */
    public static String getLastRefreshTime(String key) {
        return getPreferences(LAST_REFRESH_TIME).getString(key, StringUtils.getCurTimeStr());
    }

    public static void apply(SharedPreferences.Editor editor) {
        if (sIsAtLeastGB) { //判断设备版本时候是2.3(API9)以上  GB:gingerbread
            editor.apply(); //API 9 之后的方法
        } else {
            editor.commit();
        }
    }

    public static void set(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static void set(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        apply(editor);
    }

    public static void set(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static boolean get(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static SharedPreferences getPreferences() {
        return context().getSharedPreferences(PRE_NAME, Context.MODE_MULTI_PROCESS);
    }

    public static SharedPreferences getPreferences(String prefFileName) {
        return context().getSharedPreferences(prefFileName, Context.MODE_MULTI_PROCESS);
    }

    /**
     * 保存显示屏像素
     *
     * @param activity
     */
    public static void saveDisplaySize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt("screen_width", displayMetrics.widthPixels);
        editor.putInt("screen_height", displayMetrics.heightPixels);
        editor.putFloat("density", displayMetrics.density);
        apply(editor);
    }

    /**
     * 从配置文件获取屏幕长宽
     *
     * @return
     */
    public static int[] getDisplaySize() {
        return new int[]{getPreferences().getInt("screen_width", 480),
                getPreferences().getInt("screen_height", 854)};
    }

    public static String string(int id) {
        return _resource.getString(id);
    }

    public static String string(int id, Object... args) {
        return _resource.getString(id, args);
    }

    public static void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon);
    }

    public static void showToastShort(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastShort(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
    }

    public static void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity) {
        showToast(context().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity, Object... args) {
        showToast(context().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon, int gravity) {
        if (message == null || StringUtils.isEmpty(message)) return;

        Long curTime = System.currentTimeMillis();
        if (Math.abs(curTime - lastToastTime) > 2000 || !message.equals(lastToast)) {
            View toastView = LayoutInflater.from(context()).inflate(R.layout.view_toast, null);
            ((TextView) toastView.findViewById(R.id.title_tv)).setText(message);
            if (icon != 0) {
                ((ImageView) toastView.findViewById(R.id.icon_iv)).setImageResource(icon);
                ((ImageView) toastView.findViewById(R.id.icon_iv)).setVisibility(View.VISIBLE);
            }
            Toast toast = new Toast(context());
            toast.setView(toastView);
            if (gravity == Gravity.CENTER) {
                toast.setGravity(gravity, 0, 0);
            } else {
                toast.setGravity(gravity, 0, 35);
            }
            toast.setDuration(duration);
            toast.show();
            lastToastTime = System.currentTimeMillis();
            lastToast = message;
        }
    }
}
