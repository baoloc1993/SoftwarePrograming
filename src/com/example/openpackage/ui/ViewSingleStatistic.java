package com.example.openpackage.ui;

import com.example.openpackageapplication.R;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;

public class ViewSingleStatistic extends FragmentActivity implements ActionBar.TabListener{
	private ViewPager mViewPager;
	private TabsStatPageAdapter mTabsStatPagerAdapter;
	private ActionBar mActionBar;
	private static int screenHeight;
	private static int screenWidth;
	private ViewSingleStatistic activity;
	Intent graphInfo = getIntent();
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.graph_layout);
	    mViewPager = (ViewPager)findViewById(R.id.pager_stat);
	    mActionBar = getActionBar();
	    mTabsStatPagerAdapter = new TabsStatPageAdapter(getSupportFragmentManager());
	    mTabsStatPagerAdapter.graphInfo = graphInfo;
	    mViewPager.setAdapter(mTabsStatPagerAdapter);
        mActionBar.setHomeButtonEnabled(false);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); 
        
     // Adding Tabs
        mActionBar.addTab(mActionBar.newTab().setText("Graph")
                .setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText("Stat")
            .setTabListener(this));
        
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
       	 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                mActionBar.setSelectedNavigationItem(position);
            }
         
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        
        DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		screenHeight = displayMetrics.heightPixels;
		screenWidth = displayMetrics.widthPixels;
		activity = this;
		
	   
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * @return the standard size for rendering item
	 */
	public static int getStandardSize() {
		return Math.min(screenWidth, screenHeight);
	}
	
}
