package com.example.openpackage.ui;

import com.example.openpackageapplication.R;
import com.google.android.youtube.player.internal.f;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Fragment;

/**
 * The Class TabListenerStat
 * @author Tran Vu Xuan Nhat
 *
 */
public class TabListenerStat implements ActionBar.TabListener{
	android.app.Fragment fragment;
	
	/** 
	 * Instantiate a tab listener for ViewSingleStatisticUI activity
	 * @param fragment The fragment under tab selected 
	 * 
	 * */
	public TabListenerStat(Fragment fragment){
		 this.fragment = fragment;
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.replace(R.id.pager_stat, fragment);
		
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
