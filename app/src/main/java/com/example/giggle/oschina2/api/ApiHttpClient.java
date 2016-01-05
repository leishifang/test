package com.example.giggle.oschina2.api;

import android.util.Log;

import com.example.giggle.oschina2.AppContext;
import com.example.giggle.oschina2.util.TLog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.client.params.ClientPNames;

import java.util.Locale;

/**
 * Created by Administrator on 2015-10-25.
 */
public class ApiHttpClient {

    public static final String DELETE = "DELETE";
    public static final String HOST = "www.oschina.net";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static AsyncHttpClient client;

    private static String API_URL = "http://www.oschina.net/%s";
    private static String appCookie;

    public ApiHttpClient() {
    }

    public static void post(String urlPath, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteApiUrl(urlPath), params, responseHandler);
    }

    public static String getAbsoluteApiUrl(String UrlPath) {
        String url = String.format(API_URL, UrlPath);

        return url;
    }


    public static void get(String partUrl, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);
        TLog.log(new StringBuilder("GET ").append(getAbsoluteApiUrl(partUrl)).toString());
    }

    public static void get(String partUrl, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteApiUrl(partUrl), params, responseHandler);
        TLog.log(new StringBuilder("GET ").append(getAbsoluteApiUrl(partUrl)).toString());
    }

    public static void setHttpClient(AsyncHttpClient c) {
        client = c;
        client.addHeader("Accept-Language", Locale.getDefault().toString());
        client.addHeader("Host", HOST);
        client.addHeader("Connection", "Keep-Alive");
        client.getHttpClient().getParams()
                .setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
    }

    public static AsyncHttpClient getHttpClient() {
        return client;
    }
//
//    public static void setCookie(String cookie) {
//        client.addHeader("Cookie", cookie);
//    }
//
//    public static void cleanCookie() {
//        appCookie = "";
//    }
//
//    public static String getCookie(AppContext appContext) {
//        if (appCookie == null || appCookie == "") {
//            appCookie = appContext.getProperty("cookie");
//        }
//        return appCookie;
//    }
}
