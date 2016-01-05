package com.example.giggle.oschina2.Fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.giggle.oschina2.adpter.NewsAdapter;
import com.example.giggle.oschina2.api.OSChinaAPI;
import com.example.giggle.oschina2.base.BaseListFragment;
import com.example.giggle.oschina2.bean.News;
import com.example.giggle.oschina2.bean.NewsList;
import com.example.giggle.oschina2.util.UIHelper;
import com.example.giggle.oschina2.util.XmlUtils;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Giggle on 2015-11-23.
 */
public class NewsFragment extends BaseListFragment<News> {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UIHelper.showNewsRedirect(view.getContext(), mListAdapter.getItem(position));
    }

    @Override
    protected void sendRequestData() {
        OSChinaAPI.getNewsList(mCatalog, mCurrentPage, mResponseHandler);
    }

    @Override
    protected void executeOnLoadDataSuccess(List<News> data) {
        super.executeOnLoadDataSuccess(data);
    }

    @Override
    protected NewsList parserList(InputStream is) {
        NewsList newsList;
        try {
            newsList = XmlUtils.toBean(NewsList.class, is);
        } catch (Exception e) {
            newsList = new NewsList();
        }
        return newsList;
    }

    @Override
    protected NewsAdapter getListAdapter() {
        return new NewsAdapter();
    }
}
