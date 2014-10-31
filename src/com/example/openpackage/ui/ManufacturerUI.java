package com.example.openpackage.ui;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.openpackage.controller.UserController;
import com.example.openpackage.entity.Manufacturer;
import com.example.openpackageapplication.R;

public class ManufacturerUI extends FragmentActivity implements ActionBar.TabListener{

	private Context mContext;
	private ViewPager mViewPager;
	private TabsPagerAdapter mTabsPagerAdapter;
	private ActionBar mActionBar;
	private UserController mUserController;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.manufacturer_main);
		// Initilization
        mViewPager = (ViewPager) findViewById(R.id.pager2);
        mActionBar = getActionBar();
        mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        mViewPager.setAdapter(mTabsPagerAdapter);
        mActionBar.setHomeButtonEnabled(false);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        
        mActionBar.addTab(mActionBar.newTab().setText("Reminder")
                    .setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText("Statistic")
                .setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText("Create Survey")
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
        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.manufacturer_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case R.id.action_logout:
			new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Log out")
	        .setMessage("Are you sure you want to log out?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	Manufacturer user = (Manufacturer) mUserController.getCurrentUser();
		        	mUserController.logOut(user);
		        	Intent intent = new Intent(mContext, LoginFormActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
					finish();
		        }
	        })
	        .setNegativeButton("No", null)
	        .show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	public void onTabSelected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
		
	}


	@Override
	public void onTabUnselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		
	}


	@Override
	public void onTabReselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) {
		
	}
	


}
