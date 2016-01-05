package com.example.giggle.oschina2.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.widget.MyFragmentTabHost;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressWarnings({"unused", "JavaDoc"})
public class MainActivity extends ActionBarActivity implements View.OnTouchListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(android.R.id.tabhost)
    MyFragmentTabHost mTabhost;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 //       if (AppContext.getNightModeSwitch()) {
//            setTheme(R.style.AppBaseTheme_Night);
 //       } else {
//            setTheme(R.style.AppBaseTheme_light);
  //      }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().
                findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id
                .drawer_layout));

        initTab();
    }

    private void initTab() {
        mTabhost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.realtabcontent);

        MainTab[] tabs = MainTab.values();
        final int length = tabs.length;
        for (int i = 0; i < length; i++) {
            MainTab tab = tabs[i];
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getNameId()));
            View indicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, null);

            TextView titleTextView = (TextView) indicator.findViewById(R.id.tab_title);
            titleTextView.setText(tab.getNameId());

            Drawable drawable = this.getResources().getDrawable(tab.getIconID());
            titleTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
            }
            tabSpec.setIndicator(indicator);

            mTabhost.addTab(tabSpec, tab.getClz(), null);
            mTabhost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouchEvent(event);
        return false;
    }
}
