package com.example.openpackage.ui;

import com.example.openpackageapplication.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/** 
 * The Class StatFragment
 * @author: Tran Vu Xuan Nhat
 * */
public class StatFragment extends Fragment{
	View rootView;
	Bundle bundle;
	
	/** 
	 * Instantiate a statistic summary fragment
	 * */
	public StatFragment(){
		bundle = ViewSingleStatistic.bundle;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		rootView = inflater.inflate(R.layout.singlestat_tab_stat, container,
				false);
		double mean = 0,mode = 0;
		double variance = 0;
		int numberOfSurvey = bundle.getInt("numberOfSurvey");
		double[] rateList = bundle.getDoubleArray("rateList");
		
		for(int i = 0; i < rateList.length; ++i){
			mean += (i+1)*rateList[i];
			if(rateList[i] > mode){
				mode = rateList[i];
			}
		}
		mean = mean/rateList.length;
		
		for(int i = 0; i < rateList.length; ++i){
			variance += (i+1)*(i+1)*rateList[i];
		}
		variance = variance - mean*mean;
		//set text View
		
		TextView txt_tab_stat = (TextView) rootView.findViewById(R.id.text_tab_stat);
		
		txt_tab_stat.setText("Variance Var(X): " + variance + "\n Mode: " + mode 
				+ "\nMean : " + mean + "\n Number of surveys: " + numberOfSurvey);
		
		return rootView;
	}
}
