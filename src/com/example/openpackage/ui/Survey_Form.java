package com.example.openpackage.ui;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.openpackage.controller.FoodOpeningPackageController;
import com.example.openpackage.controller.SurveyController;
import com.example.openpackage.controller.UserController;
import com.example.openpackage.entity.Customer;
import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackage.entity.Survey;
import com.example.openpackageapplication.R;

public class Survey_Form extends Fragment{
	public final static String FOODOPENINGPACKAGE_ID = "FoodOpeningPackageId";
	private final static String TAG = "Survey_Form";
	private Survey mSurvey;
	private SurveyController mSurveyController;
	private UserController mUserController;
	private FoodOpeningPackageController mFoodOpeningPackageController;
	private Customer user;
	private FoodOpeningPackage mFood;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mSurveyController = new SurveyController(getActivity());
		mUserController = new UserController(getActivity());
		mFoodOpeningPackageController = new FoodOpeningPackageController(getActivity());
		
		String foodId = getArguments().getString(FOODOPENINGPACKAGE_ID);
		mFood = mFoodOpeningPackageController.getById(foodId);
		
		user = mUserController.getCurrentUser();
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
								Toast.makeText(getActivity(), "Your survey is submited", Toast.LENGTH_SHORT).show();
							}
							
				        }
			        })
			        .setNegativeButton("No", null)
			        .show();
					
				}
			});
			
		} else {
			//rootView = inflater.inflate(R.layout.survey_view, container, false);
		}
		return rootView;
	}
}
