package com.example.openpackage.ui;

import com.example.openpackageapplication.R;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsStatPageAdapter  extends FragmentPagerAdapter{
	public Intent graphInfo;
	public TabsStatPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub

		switch (arg0) {
		case 0:
			GraphFragment graphFragment = new GraphFragment();
			graphFragment.graphInfo = graphInfo;
			return graphFragment;
			
		case 1:
			StatFragment statFragment = new StatFragment();
			statFragment.graphInfo = graphInfo;
			return statFragment;
		
		default:
			break;
		}
		GraphFragment graphFragment = new GraphFragment();
		graphFragment.graphInfo = graphInfo;
		return graphFragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
