package com.example.openpackage.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import com.example.openpackage.controller.ReminderController;
import com.example.openpackageapplication.R;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CreateReminderFragment extends Fragment {

	String nameStr;
	String description;
	public static Date TIME;
	boolean isActive;
	
	public CreateReminderFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.new_edit_reminder_layout, container,
				false);
		
		final EditText name = (EditText) rootView.findViewById(R.id.name_reminder_field);
		
		
		EditText date = (EditText) rootView.findViewById(R.id.date_reminder_field);
		date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					
				    new TimePickerFragment().show(getFragmentManager(), "timePicker");
				
			}
		});
		
		
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
				
				
				ReminderController reminderController = new ReminderController(getActivity());
				Toast.makeText(getActivity(), 
						reminderController.validateReminderForm(nameStr, description, TIME, isActive, true, null),
						Toast.LENGTH_LONG);
			}
		});
		
		Button backToList = (Button) rootView.findViewById(R.id.back_to_list_button);
		backToList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//GO BACK TO LIST REMINDER FRAGMENT
				FragmentManager fragmentManager = getActivity().getFragmentManager();

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


