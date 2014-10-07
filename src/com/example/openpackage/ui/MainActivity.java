package com.example.openpackage.ui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.openpackageapplication.R;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager mViewPager;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ActionBar mActionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(this, this.getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		for (int i=0; i < mSectionsPagerAdapter.getCount();i++) {
			mActionBar.addTab(mActionBar.newTab()
					.setText(mSectionsPagerAdapter.getTitleTab(i))
					.setTabListener(this));
		}
		
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				mActionBar.setSelectedNavigationItem(position);
			}
		});
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}


	

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
			return new DoSurveyUI();

		case 1:
			return new ViewPersonalRecordUI();
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
			return mContext.getString(R.string.DoSurveyTab);
		case 1:
			return mContext.getString(R.string.PersonalRecordTab);
		}
		return null;
	}
	
}

