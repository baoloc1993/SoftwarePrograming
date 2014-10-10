package com.example.openpackage.ui;

import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.openpackage.controller.FoodOpeningPackageController;
import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackageapplication.R;

public class DoSurveyUI_2 extends FragmentActivity implements Survey_Form.Callbacks{
	private static String TAG = "DoSurveyUI_2";
	private FoodOpeningPackage mFood;
	private FoodOpeningPackageController mFoodOpeningPackageController;
	
	private TextView package_name,package_date, package_rate, package_type;
	private Button cancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.do_survey_2_layout);
		
		mFoodOpeningPackageController = new FoodOpeningPackageController(this);
		
		String FoodID = getIntent().getStringExtra("FoodOpeningPackageID");
		mFood = mFoodOpeningPackageController.getById(FoodID);
		
		package_name = (TextView) findViewById(R.id.package_name);
		package_date = (TextView) findViewById(R.id.package_date);
		package_rate = (TextView) findViewById(R.id.package_rate);
		package_type = (TextView) findViewById(R.id.package_type);
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
		package_rate.setText(mFood.getAverage()+"/5.0");
		package_type.setText("Type: " + mFood.getType());
		
		Bundle arguments = new Bundle();
		arguments.putString(Survey_Form.FOODOPENINGPACKAGE_ID, mFood.getID());
		
		Survey_Form fragment = new Survey_Form();
		fragment.setArguments(arguments);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.survey_container, fragment).commit();
	}

	@Override
	public void onSubmitSurveySelected() {
		Bundle arguments = new Bundle();
		arguments.putString(Survey_Form.FOODOPENINGPACKAGE_ID, mFood.getID());
		
		Survey_Form fragment = new Survey_Form();
		fragment.setArguments(arguments);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.survey_container, fragment).commit();
		
	}
}
