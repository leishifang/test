package com.example.giggle.oschina2.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.giggle.oschina2.Fragment.CommentFragment;
import com.example.giggle.oschina2.bean.News;
import com.example.giggle.oschina2.ui.DetailActivity;

/**
 * Created by Giggle on 2015-11-30.
 */
public class UIHelper {

    public static final String WEB_STYLE =
            "<script type=\"text/javascript\" src=\"file:///android_asset/shCore.js\"></script>"
                    + "<script type=\"text/javascript\" src=\"file:///android_asset/brush.js\"></script>"
                    + "<script type=\"text/javascript\" src=\"file:///android_asset/client.js\"></script>"
                    + "<script type=\"text/javascript\" src=\"file:///android_asset/detail_page.js\"></script>"
                    + "<script type=\"text/javascript\">SyntaxHighlighter.all();</script>"
                    + "<script type=\"text/javascript\">function showImagePreview(var url){window.location.url= url;}</script>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shThemeDefault.css\">"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shCore.css\">"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/css/common.css\">";

    public static final String WEB_LOAD_IMAGES = "<script type=\"text/javascript\"> var allImgUrls = " +
            "getAllImgSrc(document.body.innerHTML);</script>";

    public static void showNewsRedirect(Context context, News news) {

        int newsType = news.getNewType().getType();
        String objID = news.getNewType().getAttachment();

        if (news != null) {
            switch (newsType) {
                case News.NEWSTYPE_NEWS:
                    showNewsDetail(context, news.getId(), news.getCommentCount());
                    break;
                case News.NEWSTYPE_SOFTWARE:
                    showSoftWareDetail(context, objID);
                    break;
                case News.NEWSTYPE_POST:
                    showPostDetail(context, objID, news.getCommentCount());
                    break;
                case News.NEWSTYPE_BLOG:
                    showBlogDetail(context, objID, news.getCommentCount());
                    break;
            }
        }
    }

    public static void showBlogDetail(Context context, String objID, int commentCount) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", StringUtils.toInt(objID));
        intent.putExtra("comment_count", commentCount);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE, DetailActivity.DISPLAY_BLOG);
        context.startActivity(intent);
    }

    public static void showPostDetail(Context context, String postId, int commentCount) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", StringUtils.toInt(postId));
        intent.putExtra("comment_count", commentCount);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE, DetailActivity.DISPLAY_POST);
        context.startActivity(intent);
    }

    public static void showSoftWareDetail(Context context, String objID) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("ident", objID);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE, DetailActivity.DISPLAY_SOFTWARE);
        context.startActivity(intent);
    }

    public static void showNewsDetail(Context context, int newsId, int commentCount) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", newsId);
        intent.putExtra("comment_count", commentCount);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE, DetailActivity.DISPLAY_NEWS);
        context.startActivity(intent);
    }

    /**
     * 展示评论列表
     *
     * @param context
     * @param displayType
     */
    public static void showComment(Context context, int id,int catalog) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(CommentFragment.BUNDLE_KEY_ID, id);
        intent.putExtra(CommentFragment.BUNDLE_KEY_CATALOG, catalog);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_COMMENT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    // TODO: 2015-12-12 读取配置文件 是否加载图片
    public static String setHtmlCotentSupportImagePreview(String body) {

        // 过滤掉 img标签的width,height属性
        body = body.replaceAll("(<img[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
        body = body.replaceAll("(<img[^>]*?)\\s+height\\s*=\\s*\\S+", "$1");
        // 添加点击图片放大支持
        // 添加点击图片放大支持
        body = body.replaceAll("(<img[^>]+src=\")(\\S+)\"",
                "$1$2\" onClick=\"showImagePreview('$2')\"");

        return body;
    }

    public static void initWebView(final WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setDefaultFontSize(15);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setWebViewClient(getWebViewClient());
    }

    @NonNull
    private static WebViewClient getWebViewClient() {

        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
    }
}
