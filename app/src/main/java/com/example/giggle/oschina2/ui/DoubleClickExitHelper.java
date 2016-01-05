package com.example.giggle.oschina2.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.giggle.oschina2.AppManager;

/**
 * 双击返回键退出应用
 *
 * Created by LeishiFang on 2015-10-29 13:02.
 */
@SuppressWarnings({"unused", "JavaDoc"})
public class DoubleClickExitHelper {

    private final Activity mActivity;
    private boolean isOnKeyBacking = false;
    private Handler mHandler;
    private Toast mToast;

    public DoubleClickExitHelper(Activity activity) {
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
    }

    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if (isOnKeyBacking) {
            AppManager.getAppManager().AppExit(mActivity);
            return true;
        } else {
            isOnKeyBacking = true;
            if (mToast == null) {
                mToast = Toast.makeText(mActivity, "再次点击退出开源中国", 2000);
            }
            mToast.show();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isOnKeyBacking = false;
                    if (mToast != null) {
                        mToast.cancel();
                    }
                }
            }, 2000);
            return true;
        }
    }
}
