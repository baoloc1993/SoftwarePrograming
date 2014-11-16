package com.example.openpackage.ui;

import com.example.openpackageapplication.R;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class TabsStatPageAdapter  extends FragmentPagerAdapter{
	public Intent graphInfo;
	public TabsStatPageAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0) {
		case 0:
			return new GraphFragment();
			
		case 1:
			return new StatFragment();
		
	
		default:
			break;
		}
		return new GraphFragment();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
