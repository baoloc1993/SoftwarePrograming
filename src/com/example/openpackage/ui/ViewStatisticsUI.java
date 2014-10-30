package com.example.openpackage.ui;

import java.util.ArrayList;

import com.example.openpackage.controller.FoodOpeningPackageController;
import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackageapplication.R;

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
import android.widget.Toast;


public class ViewStatisticsUI extends Fragment implements OnItemSelectedListener{
	private FoodOpeningPackageController mFoodOpeningPackageController;
	ViewGroup viewGroup;
	public static ListView mListPackageStat;
	private ArrayList<FoodOpeningPackage> mList;
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
		Toast.makeText(getActivity(),
				parent.getItemAtPosition(position).toString() + " " + position,
				Toast.LENGTH_SHORT).show();
		//
		
		mList = mFoodOpeningPackageController.getFoodOpeningPacketList(parent
		.getItemAtPosition(position).toString());
		Log.d("dadaa", mList.get(0).getTitle().toString());
		ListFoodPackageAdapter adapter = new ListFoodPackageAdapter(getActivity(),R.layout.list_package_statistics,mList);
		mListPackageStat = (ListView) rootView.findViewById(R.id.list_package_stat);
		
		mListPackageStat.setAdapter(adapter);
		
		mListPackageStat.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle bundle = new Bundle();
				bundle.putString("name",mList.get(position).getTitle());
				FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				ViewSingleStaticticsFragment viewSingleStatFragment = new ViewSingleStaticticsFragment();
				viewSingleStatFragment.setArguments(bundle);
				viewSingleStatFragment.setHasOptionsMenu(true);
				if(viewGroup.getId()==((ViewGroup)getView().getParent()).getId()){
					Log.d("idfalse","false");
				}
				Log.d("dddddd",String.valueOf(((ViewGroup)(getView().getParent()).getParent()).getId()));
				fragmentManager.beginTransaction()
				.replace(((ViewGroup)getView().getParent()).getId(), viewSingleStatFragment)
				.addToBackStack(null).commit();
			}
			
		});
		// Log.i(TAG, mList.get(0).getTitle());
		/*if (mListPackageStat.getAdapter() == null) {
			FoodOpenPackageListAdapter adapter = new FoodOpenPackageListAdapter(
					getActivity(), mList);
			mListPackageStat.setAdapter(adapter);
		} else {
			((FoodOpenPackageListAdapter) mListPackageStat.getAdapter()).refill(mList);
		}*/
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}
