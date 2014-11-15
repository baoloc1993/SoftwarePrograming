package com.example.openpackage.ui;

import java.util.Calendar;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;
/**
 * Display the time picker for user select
 * @author Ngo Le Bao Loc
 *
 */
public class TimePickerFragment extends DialogFragment 
								implements TimePickerDialog.OnTimeSetListener {

	int hour;
	int min;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
	// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,DateFormat.is24HourFormat(getActivity()));
	}
	
	
	/**
	 * Get the hour, minute which user input
	 */
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	// Do something with the time chosen by the user

		
		Bundle bundle = new Bundle();
		bundle.putInt("HH", hourOfDay);
		bundle.putInt("MM", minute);
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		datePickerFragment.setArguments(bundle);
		datePickerFragment.show(getFragmentManager(), "datePicker");
		
		
		
	}

	
}