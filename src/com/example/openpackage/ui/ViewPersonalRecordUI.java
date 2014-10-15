package com.example.openpackage.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.openpackage.controller.SurveyController;
import com.example.openpackage.controller.UserController;
import com.example.openpackage.entity.Customer;
import com.example.openpackage.entity.Survey;
import com.example.openpackageapplication.R;
import com.parse.ParseException;

public class ViewPersonalRecordUI extends Fragment {
	
	private TextView mUsername, mGenderAge, mNumSurvey;
	private UserController mUserController;
	private SurveyController mSurveyController;
	private ListView mListView;
	
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
		
		Customer user = (Customer) mUserController.getCurrentUser();
		
		mUsername.setText(user.getUsername());
		mGenderAge.setText( (user.getGender() ? "Male"  : "Female") + " - " + user.getAge() + " years old"  );
		
		final ArrayList<Survey> mSurvey = mSurveyController.getSurveyList(user);
		
		mNumSurvey.setText( "Number of surveys: " + (mSurvey==null ? 0 : mSurvey.size() ) );

		mListView.setAdapter(new SurveyListAdapter(getActivity(), mSurvey, 1));
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), DoSurveyUI_2.class);
				try {
					intent.putExtra("FoodOpeningPackageID", mSurvey.get(position).getFoodOpeningPackage().getID());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				startActivity(intent);
				
			}
		});
		
		return rootView;
	}
}