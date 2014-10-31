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

public class CreateReminderActivity extends Activity {

	String nameStr;
	String description;
	Date time;
	boolean isActive;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.new_edit_reminder_layout);
		final EditText name = (EditText) findViewById(R.id.name_reminder_field);
		
		
		//EditText date = (EditText) findViewById(R.id.date_reminder_field);
//		date.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//					
//				    
//				
//			}
//		});
		final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
		final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		
		
		
		
		final EditText desc = (EditText) findViewById(R.id.desc_reminder_field);
		final CheckBox isActivate = (CheckBox) findViewById(R.id.is_activate);
		
		Button createReminder = (Button) findViewById(R.id.create_reminder_button);
		createReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nameStr = name.getText().toString();
				description = desc.getText().toString();
				if (isActivate.isChecked()){
					isActive = true;
				}else {
					isActive = false;
				}
				
				//GET DATE/TIME
				int year = datePicker.getYear();
				int month =  datePicker.getMonth();
				int day = datePicker.getDayOfMonth();
				int hour = timePicker.getCurrentHour();
				int minute = timePicker.getCurrentMinute();
				time = new Date(year,month,day,hour,minute);
				
				
				
				ReminderController reminderController = new ReminderController(getApplicationContext());
				String result = reminderController.validateReminderForm(nameStr, description, time, isActive, true, null);
				Toast.makeText(getApplicationContext(), 
						result,
						Toast.LENGTH_LONG).show();
				if(result == "Successfull Update Reminder" && isActive){
					Intent i = new Intent(getApplicationContext(),ReminderService.class);
					i.putExtra("name", nameStr);
					i.putExtra("description", description);
					i.putExtra("year", year);
					i.putExtra("month", month);
					i.putExtra("day", day);
					i.putExtra("hour",hour);
					i.putExtra("minute", minute);
						
					i.setAction("CREATE");
					startService(i);
				}
			}
		});
		
		Button backToList = (Button) findViewById(R.id.back_to_list_button);
		backToList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//GO BACK TO LIST REMINDER FRAGMENT
				Intent i = new Intent(getApplicationContext(), ManufacturerUI.class);
				startActivity(i);
				
			}
		});
	    // TODO Auto-generated method stub
	}

}
