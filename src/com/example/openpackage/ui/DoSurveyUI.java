package com.example.openpackage.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.openpackage.controller.FoodOpeningPackageController;
import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackageapplication.R;

/**
 * A Fragment display the list of FoodOpeningPackage object.
 * Display when user click to tab Do Survey
 * 
 * When user click to each FoodOpeningPackage, it will go to display single FoodOpenningPackage object
 * and their detail
 * @author Nguyen Phan Huy
 *
 */
public class DoSurveyUI extends Fragment implements OnItemSelectedListener {
	private static String TAG = "DoSurveyUI";
	private Spinner mSpinner;
	private ListView mListView;
	private FoodOpeningPackageController mFoodOpeningPacketController;
	private ArrayList<FoodOpeningPackage> mList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.do_survey_layout, container,
				false);

		mSpinner = (Spinner) rootView.findViewById(R.id.spinner);
		mListView = (ListView) rootView.findViewById(R.id.listView1);
		mFoodOpeningPacketController = new FoodOpeningPackageController(
				getActivity());
		
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.title_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		mSpinner.setAdapter(adapter);
		
		mSpinner.setOnItemSelectedListener(this);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				Intent intent = new Intent(getActivity(), ViewSingleOpeningPackageActivity.class);
		
				intent.putExtra("FoodOpeningPackageID", mList.get(position).getID());
				startActivity(intent);
			}
			
		});
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mList = mFoodOpeningPacketController.getFoodOpeningPacketList(mSpinner.getSelectedItem().toString());
		if (mListView.getAdapter() == null) {
			FoodOpenPackageListAdapter adapter = new FoodOpenPackageListAdapter(
					getActivity(), mList);
			mListView.setAdapter(adapter);
		} else {
			((FoodOpenPackageListAdapter) mListView.getAdapter()).refill(mList);
		}
	}
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(getActivity(),
				parent.getItemAtPosition(position).toString() + " " + position,
				Toast.LENGTH_SHORT).show();
		mList = mFoodOpeningPacketController.getFoodOpeningPacketList(parent
				.getItemAtPosition(position).toString());
		
		if (mListView.getAdapter() == null) {
			FoodOpenPackageListAdapter adapter = new FoodOpenPackageListAdapter(
					getActivity(), mList);
			mListView.setAdapter(adapter);
		} else {
			((FoodOpenPackageListAdapter) mListView.getAdapter()).refill(mList);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
