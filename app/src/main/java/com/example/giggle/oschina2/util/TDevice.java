package com.example.giggle.oschina2.util;

import android.content.pm.PackageManager;
import android.util.Log;

import com.example.giggle.oschina2.base.BaseApplication;

/**
 * Created by Administrator on 2015-10-25.
 */
public class TDevice {

    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = BaseApplication.context().
                    getPackageManager().
                    getPackageInfo(BaseApplication.context().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("package name: ");
        builder.append(BaseApplication.context().getPackageName());
        builder.append(" version code: ");
        try {
            builder.append(BaseApplication.context().getPackageManager().
                    getPackageInfo(BaseApplication.context().getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Log.d("package_info", builder.toString());

        return versionCode;
    }
}
