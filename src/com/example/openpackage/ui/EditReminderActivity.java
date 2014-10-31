package com.example.openpackage.ui;

/**
 * CreateReminderActivity
 * 
 * - Create Reminder with:
 * 	+ Name
 * 	+ Description
 * 	+ Date
 *  + Active
 */
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
	  		Date time = new Date(bundle.getLong("Date"));
	  		boolean isActive = bundle.getBoolean("Active");
	  		
	  		super.getData(Id,nameStr, description, time, isActive);

	}

}
