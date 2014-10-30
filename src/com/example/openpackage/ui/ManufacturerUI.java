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

import com.example.openpackage.controller.UserController;
import com.example.openpackage.entity.Manufacturer;
import com.example.openpackageapplication.R;

public class ManufacturerUI extends FragmentActivity implements ActionBar.TabListener{

	private Context mContext;
	private ViewPager mViewPager;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ActionBar mActionBar;
	private UserController mUserController;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.manufacturer_main);
		mContext = this;
		mViewPager = (ViewPager) findViewById(R.id.pager2);
		mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
		
		mUserController = new UserController(this);
		
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {		
			@Override
			public void onPageSelected(int position) {
				mActionBar.setSelectedNavigationItem(position);
			}
		
		});
		
		for (int i=0; i < mSectionsPagerAdapter.getCount();i++) {
			mActionBar.addTab(mActionBar.newTab()
					.setText(mSectionsPagerAdapter.getTitleTab(i))
					.setTabListener(this));
		}
		
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
	
	class SectionsPagerAdapter extends FragmentPagerAdapter {
		Context mContext;
		public SectionsPagerAdapter(Context context,FragmentManager fragmentManager) {
			super(fragmentManager);
			mContext = context;	
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				FragmentManager ft = getSupportFragmentManager();
				ft.popBackStack();
				return new ListReminderFragment();

			case 1:
				FragmentManager ft2 = getSupportFragmentManager();
				ft2.popBackStack();
				return new ViewStatisticsUI();
			case 2: 
				return new ListReminderFragment();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 2;
		}
		
		public String getTitleTab(int position) {
			switch (position) {
			case 0:
				return mContext.getString(R.string.ReminderTab);
			case 1:
				return mContext.getString(R.string.StatsTab);
			case 2: 
				return mContext.getString(R.string.ReminderTab);
			}
			return null;
		}
		
	}

}
