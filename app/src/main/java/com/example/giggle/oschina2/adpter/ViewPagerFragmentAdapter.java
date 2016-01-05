package com.example.giggle.oschina2.adpter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.widget.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by LeishiFang on 2015-11-19 16:56.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;
    protected PagerSlidingTabStrip mPagerSlidingTabStrip;
    private final ViewPager mViewPager;
    private final ArrayList<ViewPagerInfo> mTabs = new ArrayList<ViewPagerInfo>();

    public ViewPagerFragmentAdapter(FragmentManager fragmentManager, PagerSlidingTabStrip
            pagerSlidingTabStrip, ViewPager viewPager) {
        super(fragmentManager);
        mContext = viewPager.getContext();
        mPagerSlidingTabStrip = pagerSlidingTabStrip;
        mViewPager = viewPager;
        mViewPager.setAdapter(this);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
    }

    public void addTab(String title, String tag, Class<?> clss, Bundle args) {
        ViewPagerInfo viewPagerInfo = new ViewPagerInfo(tag, title, clss, args);
        addFragment(viewPagerInfo);
    }

    private void addFragment(ViewPagerInfo viewPagerInfo) {
        if (viewPagerInfo == null) {
            return;
        }

        // 加入tab title
        View v = LayoutInflater.from(mContext).inflate(
                R.layout.base_viewpager_fragment_tab_item, null, false);
        TextView title = (TextView) v.findViewById(R.id.tab_title);
        title.setText(viewPagerInfo.title);
        mPagerSlidingTabStrip.addTab(v);
        mTabs.add(viewPagerInfo);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        ViewPagerInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.clss.getName(), info.args);
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }

    public void remove() {
        remove(0);
    }

    public void remove(int index) {
        if (mTabs.isEmpty()) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        if (index > mTabs.size() - 1) {
            index = mTabs.size() - 1;
        }
        mTabs.remove(index);
        mPagerSlidingTabStrip.removeViewAt(index);
        notifyDataSetChanged();
    }

    public void removeAll() {
        if (mTabs.isEmpty()) {
            return;
        }
        mPagerSlidingTabStrip.removeAllViews();
        mTabs.clear();
        notifyDataSetChanged();
    }
}
