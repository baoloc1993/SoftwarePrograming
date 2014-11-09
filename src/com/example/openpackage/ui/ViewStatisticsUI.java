package com.example.openpackage.ui;

import java.util.ArrayList;

import com.example.openpackage.controller.FoodOpeningPackageController;
import com.example.openpackage.controller.SurveyController;

import com.example.openpackage.entity.FoodOpeningPackageRemote;

import com.example.openpackage.entity.SurveyRemote;
import com.example.openpackageapplication.R;
import com.parse.ParseException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ViewStatisticsUI extends Fragment implements OnItemSelectedListener{
	private FoodOpeningPackageController mFoodOpeningPackageController;
	ViewGroup viewGroup;
	public static ListView mListPackageStat;
	private ArrayList<FoodOpeningPackageRemote> mList;
	View rootView;
	public ViewStatisticsUI(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.list_package_statistics, container,
				false);
		viewGroup = container;
		mFoodOpeningPackageController = new FoodOpeningPackageController(getActivity());
		String[] listType = getResources().getStringArray(R.array.title_array);
		Spinner mSpinner = (Spinner) rootView.findViewById(R.id.spinner_stat);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,listType);
		mSpinner.setOnItemSelectedListener(this);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		mSpinner.setAdapter(adapter);
		mSpinner.setOnItemSelectedListener(this);
		
		return rootView;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {		
		
		mList = mFoodOpeningPackageController.getFoodOpeningPacketList(parent
		.getItemAtPosition(position).toString());
		Log.d("dadaa", mList.get(0).getTitle().toString());
		ListFoodPackageAdapter adapter = new ListFoodPackageAdapter(getActivity(),R.layout.list_package_statistics,mList);
		mListPackageStat = (ListView) rootView.findViewById(R.id.list_package_stat);
		
		mListPackageStat.setAdapter(adapter);
		TextView txtStat = (TextView) rootView.findViewById(R.id.txt_stat);
		int maxPos = 0, minPos = 0;
		double max = -1,min = 5.1;
		for(int i = 0; i < mList.size(); ++i){
			if(mList.get(i).getAverage() >max){
				maxPos = i;
			}
			if(mList.get(i).getAverage() < min){
				minPos = i;
			}
		}
		txtStat.setText("In type " + parent
		.getItemAtPosition(position).toString() + ":\n The package that have maximum average point: " + mList.get(maxPos).getTitle() 
		+ "\n The package that have minimum average point: " + mList.get(minPos).getTitle());
		mListPackageStat.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getActivity().getApplicationContext(),ViewSingleStatistic.class);
				FoodOpeningPackageRemote curPackage = mList.get(position);
				SurveyController mSurveyController = new SurveyController(getActivity().getApplicationContext());
				ArrayList<SurveyRemote> mSurvey = mSurveyController.getSurveyList(curPackage);
				double[] rate_list = {0,0,0,0,0};
				
				for(SurveyRemote cur : mSurvey){
					rate_list[cur.getRate()-1]++;
				}
				int rank = 1;
				for(int j = 0; j < mList.size(); ++j){
					if(curPackage.getAverage() > mList.get(j).getAverage()){
						++rank;
					}
				}
				i.putExtra("name",curPackage.getTitle());
				i.putExtra("type", curPackage.getType());
				i.putExtra("rateList", rate_list);
				i.putExtra("rank", rank);
				i.putExtra("size", mList.size());
				try {
					i.putExtra("numberOfSurvey", curPackage.getSurveyList().size());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				startActivity(i);
				
				
			}
			
		});
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}
