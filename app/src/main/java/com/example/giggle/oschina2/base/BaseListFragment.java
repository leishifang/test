package com.example.giggle.oschina2.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.adpter.BaseListAdapter;
import com.example.giggle.oschina2.bean.Entity;
import com.example.giggle.oschina2.bean.EntityList;
import com.example.giggle.oschina2.bean.NewsList;
import com.example.giggle.oschina2.bean.Result;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Giggle on 2015-11-22.
 */
@SuppressWarnings({"JavaDoc", "unused"})
public abstract class BaseListFragment<T extends Entity> extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private static final String TAG = BaseListFragment.class.getSimpleName();

    public static final String BUNDLE_KEY_CATALOG = "BUNDLE_KEY_CATALOG";

    protected int mStoreEmptyState = -1;
    protected int mCurrentPage = 0; //以页数为单位获取/展示信息
    protected int mCatalog = 1;
    protected Result mResult;
    private ParserTask mParserTask;
    protected BaseListAdapter<T> mListAdapter;

    @Bind(R.id.listview)
    ListView listview;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCatalog = getArguments().getInt(BUNDLE_KEY_CATALOG, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initView(View view) {
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置进度条的颜色交替
        swipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        // TODO: 2015-11-26 errorLayout

        if (mListAdapter == null) {
            mListAdapter = getListAdapter();
        }
        listview.setAdapter(mListAdapter);
        listview.setOnScrollListener(this);
        if (requestDataOnCreated()) {
            mState = STATE_NONE;
            sendRequestData();
        }
        listview.setOnItemClickListener(this);
    }

    protected abstract BaseListAdapter<T> getListAdapter();

    /**
     * 页面创建后是否自动获取数据 默认为true
     *
     * @return
     */
    protected boolean requestDataOnCreated() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listview_pulltorefresh;
    }

    @Override
    public void onRefresh() {
        if (mState == STATE_REFRESH) {
            return;
        }
        mState = STATE_REFRESH;
        mCurrentPage = 0;
        setRefreshLayoutLoadingState();
        sendRequestData();
    }

    /**
     * 设置进度条正在刷新状态
     */
    private void setRefreshLayoutLoadingState() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
            //防止多次刷新
            swipeRefreshLayout.setEnabled(false);
        }
    }

    /**
     * 设置进度条刷新完毕状态
     */
    private void setRefreshLayoutLoadedState() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
            swipeRefreshLayout.setEnabled(true);
        }
    }

    protected void sendRequestData() {
    }

    protected AsyncHttpResponseHandler mResponseHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int i, Header[] headers, byte[] bytes) {
            if (isAdded()) {
                cancelParserTask();
                mParserTask = new ParserTask(bytes);
                mParserTask.execute();
            }
        }

        @Override
        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

        }
    };

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //是否滚动到列表底端 并且不是热点等内容列表有限的栏目
        if (mListAdapter.getCount() == view.getLastVisiblePosition() + 1 &&
                mCatalog != NewsList.CATALOG_WEEK && mCatalog != NewsList.CATALOG_MONTH) {
            mCurrentPage++;//获取下一页的内容列表
            sendRequestData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    protected class ParserTask extends AsyncTask<Void, Void, String> {

        private final byte[] responseData;
        private List<T> dataList;

        public ParserTask(byte[] bytes) {
            this.responseData = bytes;
        }

        @Override
        protected String doInBackground(Void... params) {
            EntityList<T> entityList = parserList(new ByteArrayInputStream(responseData));
            dataList = entityList.getList();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            executeOnLoadDataSuccess(dataList);
            executeOnLoadFinish();
        }
    }

    protected EntityList<T> parserList(InputStream is) {
        return null;
    }

    private void cancelParserTask() {
        if (mParserTask != null) {
            mParserTask.cancel(true);
            mParserTask = null;
        }
    }

    private void executeOnLoadFinish() {
        setRefreshLayoutLoadedState();
        mState = STATE_NONE;
    }

    protected void executeOnLoadDataSuccess(List<T> data) {
        //如果是下拉刷新,将列表清空后重新填充第一页的内容
        if (mCurrentPage == 0) {
            mListAdapter.clear();
        }
        mListAdapter.addData(data);
    }
}
