package com.example.openpackage.ui;

import com.example.openpackageapplication.R;
import com.google.android.youtube.player.internal.f;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Fragment;

/** 
 * Tab listener for mainufacturerUI activity
 * @author Nguyen Tuan Anh
 *
 */
public class TabListener implements ActionBar.TabListener{
	/** The fragment attribute*/
	android.app.Fragment fragment;
	
	/**
	 * Instantiate a new TabListener object
	 * @param fragment The Fragment
	 */
	public TabListener(Fragment fragment) {
		// TODO Auto-generated constructor stub
		this.fragment = fragment;
		
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.replace(R.id.pager2, fragment);
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.remove(fragment);
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	

}
