package com.example.openpackage.ui;


import java.util.Date;

import com.example.openpackage.controller.ReminderController;
import com.example.openpackage.controller.ReminderService;
import com.example.openpackageapplication.R;
import com.example.openpackageapplication.R.id;
import com.example.openpackageapplication.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
/**
 * EditReminderActivity
 * 
 * - Edit Reminder with:
 * 	+ Name of reminder
 * 	+ Description of reminder
 * 	+ Date of remidner
 *  + Status of reminder
 *  
 *  @author Ngo Le Bao Loc
 */
public class EditReminderActivity extends ControlReminder {


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
//	    
	  //Receive Data from previous Fragment
	    
	  		Bundle bundle = getIntent().getExtras();
	  		String Id = bundle.getString("ID");
	  		String nameStr = bundle.getString("Name");
	  		String description = bundle.getString("Description");
	  		Date time = new Date(
	  				bundle.getInt("Year"), 
	  				bundle.getInt("Month"),
	  				bundle.getInt("Day"),
	  				bundle.getInt("Hour"),
	  				bundle.getInt("Minute"));
	  		boolean isActive = bundle.getBoolean("Active");
	  		// Toast.makeText(getApplicationContext(),time.toLocaleString(), Toast.LENGTH_LONG).show();
	  		super.getData(Id,nameStr, description, time, isActive,false);

	}

}
