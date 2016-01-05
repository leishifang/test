package com.example.giggle.oschina2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.giggle.oschina2.api.OSChinaAPI;
import com.example.giggle.oschina2.util.StringUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.kymjs.kjframe.utils.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogUploadService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final File logFile = FileUtils.getSaveFile("OSChina2", "OSCLog.log");
        String logData = null;
        try {
            logData = StringUtils.toConvertString(new FileInputStream(logFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(logData)) {
            OSChinaAPI.uploadLog(logData, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                    logFile.delete();
                    LogUploadService.this.stopSelf();
                }

                @Override
                public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {

                }
            });
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
