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

public class CreateReminderFragment extends Fragment {

	String nameStr;
	String description;
	Date time;
	boolean isActive;
	
	public CreateReminderFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.new_edit_reminder_layout, container,
				false);
		
		final EditText name = (EditText) rootView.findViewById(R.id.name_reminder_field);
		
		
		//EditText date = (EditText) rootView.findViewById(R.id.date_reminder_field);
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
		final TimePicker timePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
		final DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.datePicker);
		
		
		
		
		final EditText desc = (EditText) rootView.findViewById(R.id.desc_reminder_field);
		final CheckBox isActivate = (CheckBox) rootView.findViewById(R.id.is_activate);
		
		Button createReminder = (Button) rootView.findViewById(R.id.create_reminder_button);
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
				
				
				
				ReminderController reminderController = new ReminderController(getActivity());
				Toast.makeText(getActivity(), 
						reminderController.validateReminderForm(nameStr, description, time, isActive, true, null),
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


