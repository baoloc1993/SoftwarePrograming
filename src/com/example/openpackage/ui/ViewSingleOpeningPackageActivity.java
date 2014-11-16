package com.example.openpackage.ui;

import java.text.DecimalFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.openpackage.controller.FoodOpeningPackageController;
import com.example.openpackage.controller.SurveyController;
import com.example.openpackage.controller.UserController;
import com.example.openpackage.entity.CustomerRemote;
import com.example.openpackage.entity.FacebookUIHelper;
import com.example.openpackage.entity.Factory;
import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackage.entity.Survey;
import com.example.openpackage.entity.UIHelper;
import com.example.openpackageapplication.R;

/**
 * Display the detail of the FoodOpeningPackage object
 * 1. Name of package
 * 2. Date Time when object is created
 * 3. Rate of object
 * 4. Type of object
 * 5. Description of object
 * 6. List of survey of this object
 * @author Nguyen Phan Huy
 *
 */
public class ViewSingleOpeningPackageActivity extends FragmentActivity implements SurveyForm.Callbacks{
	private static String TAG = "DoSurveyUI_2";
	private FoodOpeningPackage mFood;
	private FoodOpeningPackageController mFoodOpeningPackageController;
	private SurveyController mSurveyController;
	private Survey mSurvey;
	private TextView package_name,package_date, package_rate, package_type, package_description;
	private Button cancelButton;
	private CheckBox showListView;
	private ListView mListView;
	private UIHelper uiHelper;
	private Button shareFBButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.do_survey_2_layout);
		
		mFoodOpeningPackageController = new FoodOpeningPackageController(this);
		mSurveyController = new SurveyController(this);
		
		String FoodID = getIntent().getStringExtra("FoodOpeningPackageID");
		mFood = mFoodOpeningPackageController.getById(FoodID);
		
		package_name = (TextView) findViewById(R.id.package_name);
		package_date = (TextView) findViewById(R.id.package_date);
		package_rate = (TextView) findViewById(R.id.package_rate);
		package_type = (TextView) findViewById(R.id.package_type);
		package_description = (TextView) findViewById(R.id.description);
		
		
		mListView = (ListView) findViewById(R.id.listView1);
		mListView.setAdapter(new SurveyListAdapter(this,mSurveyController.getSurveyList(mFood), 0));
		mListView.setVisibility(View.INVISIBLE);
		
		showListView = (CheckBox) findViewById(R.id.checkBox1);
		
		/*
		 * Tick box if checked, display all the survey of this object
		 */
		showListView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) mListView.setVisibility(View.VISIBLE);
				else mListView.setVisibility(View.INVISIBLE);
			}
		});
		
		cancelButton = (Button) findViewById(R.id.Back);
		/*
		 * Back button
		 */
		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		String converDate = DateUtils.getRelativeTimeSpanString(
				mFood.getParseObject().getUpdatedAt().getTime(),
				new Date().getTime(), 
				DateUtils.SECOND_IN_MILLIS).toString();
		package_date.setText(converDate);
		package_name.setText(mFood.getTitle());
		package_rate.setText(new DecimalFormat("0.0").format(mFood.getAverage())  +"/5.0");
		package_type.setText("Type: " + mFood.getType());
		package_description.setText(mFood.getDescription());
		
		Bundle arguments = new Bundle();
		arguments.putString(SurveyForm.FOODOPENINGPACKAGE_ID, mFood.getID());
		
		SurveyForm fragment = new SurveyForm();
		fragment.setArguments(arguments);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.survey_container, fragment).commit();
		
		/*
		 * Display the video of youtube
		 */
		Fragment videoFragment = Factory.createVideoPlayer("Youtube", mFood.getvideoLink());
		getSupportFragmentManager().beginTransaction().replace(R.id.youtube_container, videoFragment).commit();
		
		uiHelper = Factory.createShareMedia("Facebook", this);
	    uiHelper.onCreate(savedInstanceState);
		
	    
	    /*
	     * ShareButton
	     */
		shareFBButton = (Button) findViewById(R.id.ShareFBButton);
		UserController mUserController = new UserController(this);
		if(mSurveyController.getSurvey(mFood,(CustomerRemote) mUserController.getCurrentUser()) == null)
		{
			shareFBButton.setVisibility(View.INVISIBLE);
		};
	}
	
	public void allowShare(Survey survey)
	{
		shareFBButton.setVisibility(View.VISIBLE);
		mSurvey = survey;
	}
	
	/**
	 * 
	 * @param v
	 */
	public void onClick(View v) {
		uiHelper.openDialog(mSurvey,mFood);
	}

	@Override
	public void onSubmitSurveySelected() {
		Bundle arguments = new Bundle();
		arguments.putString(SurveyForm.FOODOPENINGPACKAGE_ID, mFood.getID());
		
		SurveyForm fragment = new SurveyForm();
		fragment.setArguments(arguments);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.survey_container, fragment).commit();
		
		((SurveyListAdapter) mListView.getAdapter()).refill(mSurveyController.getSurveyList(mFood));
		package_rate.setText(new DecimalFormat("0.0").format(mFood.getAverage())  +"/5.0");
		
	}
	
	@Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      uiHelper.onActivityResult(requestCode, resultCode, data);
	  }
	
	@Override
	protected void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}
}

