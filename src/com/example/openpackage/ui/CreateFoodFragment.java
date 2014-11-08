package com.example.openpackage.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackageapplication.R;
import com.parse.ParseException;

public class CreateFoodFragment extends Fragment{
	
	private Spinner mSpinner;
	private EditText mTitle,mDescriotion,mVideoLink;
	private Button mButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.create_food_layout, container,
				false);
		
		mSpinner = (Spinner) rootView.findViewById(R.id.EditTypeSpinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.title_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		mSpinner.setAdapter(adapter);
		
		mTitle = (EditText) rootView.findViewById(R.id.editTitle);
		
		mDescriotion = (EditText) rootView.findViewById(R.id.editDescription);
		
		mVideoLink = (EditText) rootView.findViewById(R.id.editVideoLink);
		
		mButton = (Button) rootView.findViewById(R.id.Submit);
		
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(getActivity())
		        .setIcon(android.R.drawable.ic_dialog_alert)
		        .setTitle("Create New Food Openning Package")
		        .setMessage("Are you sure you want to create new food openning package?")
		        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			        	
			        	String title = mTitle.getText().toString();
			        	String description = mDescriotion.getText().toString();
			        	String videoLink = mVideoLink.getText().toString();
			        	String type = mSpinner.getSelectedItem().toString();
			        	
			        	try {
							FoodOpeningPackage mFood = new FoodOpeningPackage(title,description,videoLink,type);
						} catch (ParseException e) {
							Toast.makeText(getActivity(), "Unsuccessful created new Food Opening Package!!! Please try again!!!", Toast.LENGTH_LONG).show();
							e.printStackTrace();
						}
			        	Toast.makeText(getActivity(), "Successful created new Food Opening Package", Toast.LENGTH_LONG).show();
						
			        }
		        })
		        .setNegativeButton("No", null)
		        .show();
			}
		});
		
		return rootView;
	}
}
