package com.example.openpackage.ui;

import com.example.openpackageapplication.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Tab Adapter for MainufacturerUI activity
 * @author Nguyen Tuan Anh
 *
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
	/**
	 * Instantiate a new TabsPagerAdapter object
	 * @param fragmentManager The fragment manager
	 */
	public TabsPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0) {
		case 0:
			return new ListReminderFragment();
			
		case 1:
			return new StatisticsUI();
		
		case 2:
			return new CreateFoodOpeningPackageUI();
		default:
			break;
		}
		return new ListReminderFragment();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	

}
