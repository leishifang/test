package com.example.giggle.oschina2.api;

import com.example.giggle.oschina2.AppContext;
import com.example.giggle.oschina2.bean.NewsList;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.kymjs.kjframe.http.HttpParams;

/**
 * Created by Administrator on 2015-10-24.
 */
public class OSChinaAPI {

    private static void uploadLog(String data, String report, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("app", 1);
        params.put("report", report);
        params.put("msg", data);
        ApiHttpClient.post("action/api/user_report_to_admin", params, responseHandler);
    }

    /**
     * 日志提交
     *
     * @param data
     * @param responseHandler
     */
    public static void uploadLog(String data, AsyncHttpResponseHandler responseHandler) {
        uploadLog(data, "1", responseHandler);
    }

    /**
     * 获取新闻列表
     *
     * @param catalog             类别: 1,2,3
     * @param pageIndex           第几页
     * @param httpResponseHandler
     */
    public static void getNewsList(int catalog, int pageIndex, AsyncHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", AppContext.PAGE_SIZE);
        if (catalog == NewsList.CATALOG_WEEK) {
            params.put("show", "week");
        } else if (catalog == NewsList.CATALOG_MONTH) {
            params.put("show", "month");
        }
        ApiHttpClient.get("action/api/news_list", params, httpResponseHandler);
    }

    public static void getCommentList(int catalog,int id,int page,AsyncHttpResponseHandler handler){
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("id", id);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        params.put("clientType", "android");
        ApiHttpClient.get("action/api/comment_list",params,handler);
    }
}
