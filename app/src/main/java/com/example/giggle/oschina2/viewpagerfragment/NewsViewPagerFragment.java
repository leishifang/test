package com.example.giggle.oschina2.viewpagerfragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.giggle.oschina2.Fragment.NewsFragment;
import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.adpter.ViewPagerFragmentAdapter;
import com.example.giggle.oschina2.base.BaseListFragment;
import com.example.giggle.oschina2.base.BaseViewPagerFragment;
import com.example.giggle.oschina2.bean.NewsList;

/**
 * 综合viewpager
 *
 * Created by LeishiFang on 2015-11-19 23:20.
 */
public class NewsViewPagerFragment extends BaseViewPagerFragment {

    @Override
    protected void onSetupTabAdapter(ViewPagerFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(
                R.array.news_viewpager_arrays);
        adapter.addTab(title[0], "news", NewsFragment.class,//咨询
                getBundle(NewsList.CATALOG_ALL));
        adapter.addTab(title[1], "news_week", NewsFragment.class, //热点
                getBundle(NewsList.CATALOG_WEEK));
        adapter.addTab(title[2], "latest_blog",  BaseViewPagerFragment.class,//博客
                null);
        adapter.addTab(title[3], "recommend_blog",BaseViewPagerFragment.class,//推荐
                null);
    }

    private Bundle getBundle(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, type);
        return bundle;
    }

    @Override
    protected void setScreenPagerLimit(ViewPager viewPager) {
        viewPager.setOffscreenPageLimit(3);
    }
}
