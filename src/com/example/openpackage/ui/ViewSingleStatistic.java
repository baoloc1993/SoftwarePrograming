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
/**
 * Display the statistic report, included graph and analysis, of single food package 
 * @author Tran Vu Xuan Nhat
 *
 */
public class ViewSingleStatistic extends FragmentActivity implements ActionBar.TabListener{
	/**
	 * The ViewPager attribute of activity
	 */
	private ViewPager mViewPager;
	
	/**
	 * The TabsStatPageAdapter user to handle tab under this activity
	 */
	private TabsStatPageAdapter mTabsStatPagerAdapter;
	
	/**
	 * The Action Bar of activity 
	 */
	private ActionBar mActionBar;
	
	/**
	 * The screen's height
	 */
	private static int screenHeight;
	
	/**
	 * The screen's width
	 */
	private static int screenWidth;
	
	/**
	 * The ViewSingleStatistic reference
	 */
	private ViewSingleStatistic activity;
	
	/**
	 * The bundle store information transfer between activity and fragment
	 */
	public static Bundle bundle;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.manufacturer_main);
	    
	    //Get data from previous Intent
	    Intent i = getIntent();
	    bundle = i.getExtras();
	    
		// Initilization
        mViewPager = (ViewPager) findViewById(R.id.pager2);
        mActionBar = getActionBar();
        mTabsStatPagerAdapter = new TabsStatPageAdapter(getSupportFragmentManager());
 
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
	
	/**
	 * Gets the screen's height
	 * @return Returns the screen's height
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * Gets the screen's width
	 * @return Returns the screen's width
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Gets the standard size of screen
	 * @return Returns the standard size for rendering item
	 */
	public static int getStandardSize() {
		return Math.min(screenWidth, screenHeight);
	}
	
}
