package com.example.giggle.oschina2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.adpter.ViewPagerFragmentAdapter;
import com.example.giggle.oschina2.widget.PagerSlidingTabStrip;

/**
 * Created by LeishiFang on 2015-11-19 15:32.
 */
public class BaseViewPagerFragment extends BaseFragment {

    protected PagerSlidingTabStrip mPagerSlidingTabStrip;
    protected ViewPager mViewPager;
    protected ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpager_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);

        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(),
                mPagerSlidingTabStrip, mViewPager);
        setScreenPagerLimit(mViewPager);
        onSetupTabAdapter(mViewPagerFragmentAdapter);
    }

    protected void onSetupTabAdapter(ViewPagerFragmentAdapter adapter) {
    }

    protected void setScreenPagerLimit(ViewPager viewPager) {
    }
}
