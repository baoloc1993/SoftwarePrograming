package com.example.openpackage.ui;

import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.openpackage.controller.ReminderController;
import com.example.openpackageapplication.R;

public class EditReminderFragment extends Fragment {

	String nameStr;
	String Id;
	String description;
	Date time;
	boolean isActive;
	
	public EditReminderFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.new_edit_reminder_layout, container,
				false);
		
		//Receive Data from previous Fragment
		Bundle bundle = this.getArguments();
		Id = bundle.getString("ID");
		nameStr = bundle.getString("Name");
		description = bundle.getString("Description");
		time = new Date(bundle.getLong("Date"));
		isActive = bundle.getBoolean("Active");
		
		
		//Display the old information
		final EditText name = (EditText) rootView.findViewById(R.id.name_reminder_field);
		name.setText(nameStr);
		
		final TimePicker timePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
		final DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.datePicker);
		timePicker.setCurrentHour(time.getHours());
		timePicker.setCurrentMinute(time.getMinutes());
		datePicker.updateDate(time.getYear(), time.getMonth(), time.getDay());
		
//		EditText date = (EditText) rootView.findViewById(R.id.date_reminder_field);
//		date.setText(new Date(bundle.getLong("Date")).toString());
//		date.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//					
//				    new TimePickerFragment().show(getFragmentManager(), "timePicker");
//				    time = CreateReminderFragment.TIME;
//			}
//		});
		
		
		EditText desc = (EditText) rootView.findViewById(R.id.desc_reminder_field);
		desc.setText(description);
		
		final CheckBox isActivate = (CheckBox) rootView.findViewById(R.id.is_activate);
		isActivate.setActivated(isActive);
		
	
		
		Button createReminder = (Button) rootView.findViewById(R.id.create_reminder_button);
		createReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ReminderController reminderController = new ReminderController(getActivity());
				nameStr = name.getText().toString();
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
				
				Toast.makeText(getActivity(), 
						reminderController.validateReminderForm(nameStr, description, time, isActive, false, Id),
						Toast.LENGTH_LONG);
			}
		});
		
		Button backToList = (Button) rootView.findViewById(R.id.back_to_list_button);
		backToList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//GO BACK TO LIST REMINDER FRAGMENT
				FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

				ListReminderFragment listReminderFragment = new ListReminderFragment();
				
				fragmentManager.beginTransaction()
						.replace(((ViewGroup)getView().getParent()).getId(), listReminderFragment)
						.commit();
				
			}
		});
		
		return rootView;
		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		return;
	}
}
