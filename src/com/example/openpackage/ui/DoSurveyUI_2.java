package com.example.openpackage.ui;

import java.text.DecimalFormat;
import java.util.Date;

import android.os.Bundle;
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
import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackageapplication.R;

public class DoSurveyUI_2 extends FragmentActivity implements Survey_Form.Callbacks{
	private static String TAG = "DoSurveyUI_2";
	private FoodOpeningPackage mFood;
	private FoodOpeningPackageController mFoodOpeningPackageController;
	private SurveyController mSurveyController;
	
	private TextView package_name,package_date, package_rate, package_type, package_description;
	private Button cancelButton;
	private CheckBox showListView;
	private ListView mListView;
	
	
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
		showListView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) mListView.setVisibility(View.VISIBLE);
				else mListView.setVisibility(View.INVISIBLE);
			}
		});
		
		cancelButton = (Button) findViewById(R.id.Back);
		
		
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
		arguments.putString(Survey_Form.FOODOPENINGPACKAGE_ID, mFood.getID());
		
		Survey_Form fragment = new Survey_Form();
		fragment.setArguments(arguments);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.survey_container, fragment).commit();
		YoutubeFragment myFragment = YoutubeFragment.newInstance("Ok7tnT3aL8M");
		getSupportFragmentManager().beginTransaction().replace(R.id.youtube_container, myFragment).commit();
	}

	@Override
	public void onSubmitSurveySelected() {
		Bundle arguments = new Bundle();
		arguments.putString(Survey_Form.FOODOPENINGPACKAGE_ID, mFood.getID());
		
		Survey_Form fragment = new Survey_Form();
		fragment.setArguments(arguments);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.survey_container, fragment).commit();
		
		((SurveyListAdapter) mListView.getAdapter()).refill(mSurveyController.getSurveyList(mFood));
		package_rate.setText(new DecimalFormat("0.0").format(mFood.getAverage())  +"/5.0");
	}
}
