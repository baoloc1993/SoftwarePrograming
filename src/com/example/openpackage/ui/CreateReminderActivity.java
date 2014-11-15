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
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.example.openpackage.controller.ReminderController;
import com.example.openpackage.controller.ReminderService;
import com.example.openpackageapplication.R;
import com.example.openpackageapplication.R.id;
import com.example.openpackageapplication.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
 * 
 * @author NGO LE BAO LOC
 * Create the reminder activity
 * Extends from ControlReminder
 */
public class CreateReminderActivity extends ControlReminder {

	String nameStr;
	String description;
	Date time;
	boolean isActive;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);    
	    Date date = Calendar.getInstance(TimeZone.getDefault()).getTime();
	    date.setYear(date.getYear() );
	    date.setMonth(date.getMonth());
	    date.setDate(date.getDate());
	    //Toast.d("DEBUG", date.toLocaleString());
	   
	    
		super.getData(null, "", "", date, false,true);

	}

}
