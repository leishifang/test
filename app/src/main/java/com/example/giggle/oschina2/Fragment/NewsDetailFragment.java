package com.example.giggle.oschina2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.api.ApiHttpClient;
import com.example.giggle.oschina2.base.BaseFragment;
import com.example.giggle.oschina2.bean.News;
import com.example.giggle.oschina2.bean.NewsDetail;
import com.example.giggle.oschina2.util.FontSizeUtils;
import com.example.giggle.oschina2.util.StringUtils;
import com.example.giggle.oschina2.util.ThemeSwitchUtils;
import com.example.giggle.oschina2.util.UIHelper;
import com.example.giggle.oschina2.util.XmlUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Giggle on 2015-12-08.
 */
public class NewsDetailFragment extends BaseFragment {

    @Bind(R.id.tv_title)
    protected TextView mTitleTextView;
    @Bind(R.id.tv_source)
    protected TextView mSourceTextView;
    @Bind(R.id.tv_publish_time)
    protected TextView mPubTextView;
    @Bind(R.id.wv)
    protected WebView mWebView;

    protected News mNews;
    protected int mNewsID;

    protected AsyncHttpResponseHandler mResponseHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onStart() {
        }

        @Override
        public void onSuccess(int i, Header[] headers, byte[] bytes) {
            mNews = XmlUtils.toBean(NewsDetail.class, new ByteArrayInputStream(bytes)).getNews();
            if (mNews != null) {
                mWebView.loadDataWithBaseURL("", getWebViewBody(mNews), "text/html", "UTF-8", "");
                // TODO: 2015-12-12 从配置文件获取WEB字体大小
                mWebView.loadUrl(FontSizeUtils.getFontSize(3));
            }
        }

        @Override
        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_detail, null);
        ButterKnife.bind(this, view);

        UIHelper.initWebView(mWebView);

        mNewsID = getActivity().getIntent().getIntExtra("id", 0);
        RequestParams params = new RequestParams("id", mNewsID);
        ApiHttpClient.get("action/api/news_detail", params, mResponseHandler);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private String getWebViewBody(News news) {
        StringBuffer body = new StringBuffer();
        body.append(UIHelper.WEB_STYLE).append(UIHelper.WEB_LOAD_IMAGES);
        body.append(ThemeSwitchUtils.getWebViewBodyString());
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", news.getTitle()));
        // 添加作者和时间
        String time = StringUtils.friendly_time(news.getPubDate());
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>",
                news.getAuthorId(), news.getAuthor());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持
        body.append(UIHelper.setHtmlCotentSupportImagePreview(news.getBody()));

        // 更多关于***软件的信息
        String softwareName = news.getSoftwareName();
        String softwareLink = news.getSoftwareLink();
        if (!StringUtils.isEmpty(softwareName)
                && !StringUtils.isEmpty(softwareLink))
            body.append(String
                    .format("<div class='oschina_software' style='margin-top:8px;font-weight:bold'>" +
                                    "更多关于:&nbsp;<a href='%s'>%s</a>&nbsp;的详细信息</div>",
                            softwareLink, softwareName));

        // 相关新闻
        if (news != null && news.getRelatives() != null
                && news.getRelatives().size() > 0) {
            String strRelative = "";
            for (News.Relative relative : news.getRelatives()) {
                strRelative += String.format(
                        "<li><a href='%s' style='text-decoration:none'>%s</a></li>",
                        relative.url, relative.title);
            }
            body.append("<p/><div style=\"height:1px;width:100%;background:#DADADA;margin-bottom:10px;\"/>"
                    + String.format("<br/> <b>相关资讯</b><ul class='about'>%s</ul>",
                    strRelative));
        }
        body.append("<br/>");
        // 封尾
        body.append("</div></body>");
        return body.toString();
    }
}
