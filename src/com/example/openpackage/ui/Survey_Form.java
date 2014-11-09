package com.example.openpackage.ui;


import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openpackage.controller.FoodOpeningPackageController;
import com.example.openpackage.controller.SurveyController;
import com.example.openpackage.controller.UserController;

import com.example.openpackage.entity.CustomerRemote;

import com.example.openpackage.entity.FoodOpeningPackageRemote;

import com.example.openpackage.entity.SurveyRemote;
import com.example.openpackageapplication.R;

public class Survey_Form extends Fragment{
	public final static String FOODOPENINGPACKAGE_ID = "FoodOpeningPackageId";
	private final static String TAG = "Survey_Form";
	private SurveyRemote mSurvey;
	private SurveyController mSurveyController;
	private UserController mUserController;
	private FoodOpeningPackageController mFoodOpeningPackageController;
	private CustomerRemote user;
	private FoodOpeningPackageRemote mFood;	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mSurveyController = new SurveyController(getActivity());
		mUserController = new UserController(getActivity());
		mFoodOpeningPackageController = new FoodOpeningPackageController(getActivity());
		
		String foodId = getArguments().getString(FOODOPENINGPACKAGE_ID);
		mFood = mFoodOpeningPackageController.getById(foodId);
		
		user = (CustomerRemote) mUserController.getCurrentUser();
		if ( user == null ) Log.i(TAG, "No User");
		else Log.i(TAG, user.getUsername());
		
		mSurvey = mSurveyController.getSurvey(mFood, user);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = null;
		if (mSurvey==null) {
			rootView = inflater.inflate(R.layout.survey_form, container, false);
			
			Button submitButton = (Button) rootView.findViewById(R.id.submit_button);
			final RatingBar rating = (RatingBar) rootView.findViewById(R.id.ratingBar1);
			final EditText comment = (EditText) rootView.findViewById(R.id.editText1);
			submitButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					new AlertDialog.Builder(getActivity())
			        .setIcon(android.R.drawable.ic_dialog_alert)
			        .setTitle("Submit Survey")
			        .setMessage("Are you sure you want to submit this survey?")
			        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
				        @Override
				        public void onClick(DialogInterface dialog, int which) {
				        	
				        	String cmt = comment.getText().toString();
							int rate = (int) rating.getRating();
							//Log.i(TAG, comment.getText().toString() );
							//Log.i(TAG, rating.getRating()+"");
							boolean create = mSurveyController.validateCreateData(cmt, rate, mFood);
							if (!create) {
								Toast.makeText(getActivity(), "Your survey is invalid", Toast.LENGTH_SHORT).show();
							} else {
								mCallbacks.onSubmitSurveySelected();
								Toast.makeText(getActivity(), "Your survey is submited", Toast.LENGTH_LONG).show();
							}
							
				        }
			        })
			        .setNegativeButton("No", null)
			        .show();
					
				}
			});
			
		} else {
			Log.i(TAG, "LOAD FORM");
			rootView = inflater.inflate(R.layout.survey_view, container, false);
			
			TextView username = (TextView) rootView.findViewById(R.id.username);
			RatingBar rate = (RatingBar) rootView.findViewById(R.id.ratingBar1);
			TextView comment = (TextView) rootView.findViewById(R.id.comment);
			TextView date = (TextView) rootView.findViewById(R.id.date);
			RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.container);
			
			layout.setBackgroundColor(getActivity().getResources().getColor(R.color.lighter_gray));
			
			Log.i(TAG, "STILL OKAY");
			
			username.setText("Your Survey");
			rate.setRating( (float) mSurvey.getRate());
			comment.setText(mSurvey.getComment());
			
			String converDate = DateUtils.getRelativeTimeSpanString(
					mSurvey.getParseObject().getUpdatedAt().getTime(),
					new Date().getTime(), 
					DateUtils.SECOND_IN_MILLIS).toString();
			
			date.setText(converDate);
			
			Log.i(TAG, "STILL OKAY2");
		}
		return rootView;
	}
	
	public interface Callbacks {
		public void onSubmitSurveySelected();
	}
	
	private Callbacks mCallbacks; 
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}
		mCallbacks = (Callbacks) activity;
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}
}
