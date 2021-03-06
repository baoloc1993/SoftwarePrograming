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
import android.widget.ListView;
import android.widget.TextView;

import com.example.openpackage.controller.SurveyController;
import com.example.openpackage.controller.UserController;
import com.example.openpackage.entity.CustomerRemote;
import com.example.openpackage.entity.Survey;
import com.example.openpackageapplication.R;
import com.parse.ParseException;
/**
 * Display the Personal information of current user
 * @author Huynh Ba Dat
 *
 */
public class ViewPersonalRecordUI extends Fragment {
	/** The constant static String TAG*/
	private final static String TAG = "ViewPersonalRecordUI";
	
	/** The TextView that views username*/
	private TextView mUsername;
	
	/** The TextView that views gender and age*/
	private TextView mGenderAge;
	
	/** The TextView that views the number of survey*/
	private TextView mNumSurvey;
	
	/** The User Controller attribute*/
	private UserController mUserController;
	
	/** The Survey Controller attribute*/
	private SurveyController mSurveyController;
	
	/** The list view attribute*/
	private ListView mListView;
	
	/** The list of survey*/
	private ArrayList<Survey> mSurvey;
	
	/** The current user reference*/
	private CustomerRemote user;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.personal_record_layout, container, false);
		
		mUsername = (TextView) rootView.findViewById(R.id.textView1);
		mGenderAge = (TextView) rootView.findViewById(R.id.textView2);
		mNumSurvey = (TextView) rootView.findViewById(R.id.textView3);
		mListView = (ListView) rootView.findViewById(R.id.listView1);
		
		mSurveyController = new SurveyController(getActivity());
		
		mUserController = new UserController(getActivity());
		
		user = (CustomerRemote) mUserController.getCurrentUser();
		
		mUsername.setText(user.getUsername());
		mGenderAge.setText( (user.getGender() ? "Male"  : "Female") + " - " + user.getAge() + " years old"  );
		
		

		
		return rootView;
	}
	@Override
	public void onResume() {
		super.onResume();
		mSurvey = mSurveyController.getSurveyList(user);
		mNumSurvey.setText( "Number of surveys: " + (mSurvey==null ? 0 : mSurvey.size() ) );
		mListView.setAdapter(new SurveyListAdapter(getActivity(), mSurvey, 1));
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), ViewSingleOpeningPackageActivity.class);
				try {
					intent.putExtra("FoodOpeningPackageID", mSurvey.get(position).getFoodOpeningPackage().getID());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				startActivity(intent);
				
			}
		});
	}
}
