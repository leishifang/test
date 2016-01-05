package com.example.giggle.oschina2;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * activity stacker
 *
 * 单例模式 确保多线程时的安全 get方法可以为static 因为get方法不会引起危险行为
 * 但是比如finishactivity()不能为static 如果为static就可能出现线程安全问题
 *
 * Created by LeishiFang on 2015-10-28 17:13.
 */
@SuppressWarnings({"unused", "JavaDoc"})
public class AppManager {

    private static Stack<Activity> mActivityStack;
    private static AppManager instance;

    private AppManager() {}

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取当前activity
     *
     * @return
     */
    public Activity currentActivity() {
        return mActivityStack.lastElement();
    }

    /**
     * 结束当前activity
     */
    public void finishActivity() {
        finishActivity(mActivityStack.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    public void finishAllActivity() {
        for (Activity activity : mActivityStack) {
            finishActivity(activity);
        }
    }

    /**
     * 根据类名获取指定activity
     *
     * @param cls
     * @return
     */
    public static Activity getActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    // TODO: 2015-10-29 Context参数是非多余
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid()); //杀死应用进程
            System.exit(0); //Use 0 to signal success to the calling process and 1 to signal failure.
        } catch (Exception e) {
        }
    }
}
