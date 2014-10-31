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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public abstract class ControlReminder extends Activity {
	
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
	
	protected void getData(final String id,final String nameStr, String description, final Date time, final boolean isActive){
		//GET DATE/TIME
		final EditText name = (EditText) findViewById(R.id.name_reminder_field);
		name.setText(nameStr);
		

		final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
		final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		timePicker.setCurrentHour(time.getHours());
		timePicker.setCurrentMinute(time.getMinutes());
		datePicker.updateDate(time.getYear(), time.getMonth(), time.getDay());
		
		final EditText desc = (EditText) findViewById(R.id.desc_reminder_field);
		desc.setText(description);
		
		final CheckBox isActivate = (CheckBox) findViewById(R.id.is_activate);
		isActivate.setActivated(isActive);
		
		Button createReminder = (Button) findViewById(R.id.create_reminder_button);
		createReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nameString = name.getText().toString();
				descriptionStr = desc.getText().toString();
				if (isActivate.isChecked()){
					isActive2 = true;
				}else {
					isActive2 = false;
				}
				
				//GET DATE/TIME
				int year = datePicker.getYear();
				int month =  datePicker.getMonth();
				int day = datePicker.getDayOfMonth();
				int hour = timePicker.getCurrentHour();
				int minute = timePicker.getCurrentMinute();
				time2 = new Date(year,month,day,hour,minute);
				
				
				
				ReminderController reminderController = new ReminderController(getApplicationContext());
				String result = reminderController.validateReminderForm(nameStr, descriptionStr, time, isActive, true, id);
				Toast.makeText(getApplicationContext(), 
						result,
						Toast.LENGTH_LONG).show();
				if(result == "Successfull Update Reminder" && isActive){
					Intent i = new Intent(getApplicationContext(),ReminderService.class);
					i.putExtra("name", nameString);
					i.putExtra("description", descriptionStr);
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
	}

}
