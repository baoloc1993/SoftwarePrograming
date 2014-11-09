/**
 * ControlRemider
 * 
 * Control the layout and function of layout like getText, get Date, get String
 * 
 * CreateRemiderActivity and EditReminderActivity extends this Class
 */
package com.example.openpackage.ui;

import java.util.Date;

import com.example.openpackage.controller.ReminderController;
import com.example.openpackage.controller.ReminderService;
import com.example.openpackageapplication.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public abstract class ControlReminder extends Activity {
	
	EditText name;
	TimePicker timePicker;
	DatePicker datePicker;
	EditText desc;
	CheckBox isActivate;
	protected String nameString;
	protected String descriptionStr;
	protected Date time2;
	protected boolean isActive2;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.new_edit_reminder_layout);
	    
	}
	/**
	 * IF NEW REMINDER
	 * @param id = null
	 * @param nameStr = null
	 * @param description = null
	 * @param time = current Date
	 * @param isActive = false
	 */
	
	protected void getData(final String id,final String nameStr, String description, final Date time, final boolean isActive){
		//GET DATE/TIME
		name = (EditText) findViewById(R.id.name_reminder_field);
		name.setText(nameStr);
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				nameString = name.getText().toString();
				//Toast.makeText(getApplicationContext(), nameString, Toast.LENGTH_SHORT).show();
				
			};
		});

		timePicker = (TimePicker) findViewById(R.id.timePicker);
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		timePicker.setCurrentHour(time.getHours());
		timePicker.setCurrentMinute(time.getDay());

		datePicker.updateDate(time.getYear(), time.getMonth(), time.getDay());
		//datePicker.updateDate(year, month, dayOfMonth);
		
		desc = (EditText) findViewById(R.id.desc_reminder_field);
		desc.setText(description);
		
		isActivate = (CheckBox) findViewById(R.id.is_activate);
		isActivate.setChecked(isActive);
		
		Button createReminder = (Button) findViewById(R.id.create_reminder_button);
		if (id != null){
			createReminder.setText ("Edit Reminder");
		}else {
			createReminder.setText ("Create Reminder");
		}
		createReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//nameString = name.getText().toString();
				
				descriptionStr = desc.getText().toString();
				if (isActivate.isChecked()){
					isActive2 = true;
				}else {
					isActive2 = false;
				}
				
				//GET DATE/TIME
				int year = datePicker.getYear();
				
				//Toast.makeText(getApplicationContext(),  String.valueOf(year), Toast.LENGTH_LONG);
				int month =  datePicker.getMonth() ;
				int day = datePicker.getDayOfMonth();
				int hour = timePicker.getCurrentHour();
				int minute = timePicker.getCurrentMinute();
				Log.d("DEBUG 123", String.valueOf(nameString));
				time2 = new Date(year,month,day,hour,minute);
				
				
				
				ReminderController reminderController = new ReminderController(getApplicationContext());
				//String result = "";
				String result = reminderController.validateReminderForm(nameString, descriptionStr, time, isActive, true, id);
				Toast.makeText(getApplicationContext(), 
						result,
						Toast.LENGTH_LONG).show();
				if (result == "Successfull Update Reminder"){
					//GO BACK TO MANUFACTURERUI
					Intent in = new Intent(getApplicationContext(), ManufacturerUI.class);
					startActivity(in);
				}
				if(result == "Successfull Update Reminder" && isActive){
					Intent i = new Intent(getApplicationContext(),ReminderService.class);
					//Toast.makeText(getApplicationContext(), nameString, Toast.LENGTH_LONG);
					i.putExtra("name", nameString);
					i.putExtra("description", descriptionStr);
					i.putExtra("year", year);
					i.putExtra("month", month);
					i.putExtra("day", day);
					i.putExtra("hour",hour);
					i.putExtra("minute", minute);
					reminderController.addNewAlarm(getApplicationContext(),i);	
					
				}
				
				
			}
		});
		
		//BACK TO LIST BUTTON
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
		
		//CANCEL REMINDER
		Button cancelReminder = (Button) findViewById(R.id.cancel_reminder);
		if (id != null){
			cancelReminder.setVisibility(View.VISIBLE);
		}
		
		cancelReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ReminderController reminderController= new ReminderController(getApplicationContext());
				Toast.makeText(getApplicationContext(), reminderController.removeReminder(id), Toast.LENGTH_LONG).show();
				//GO BACK TO MANUFACTURERUI
				Intent in = new Intent(getApplicationContext(), ManufacturerUI.class);
				startActivity(in);
			}
		});
	}

}
