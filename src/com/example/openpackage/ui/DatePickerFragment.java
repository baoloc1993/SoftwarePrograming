package com.example.openpackage.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.DatePicker;

/**
 * Display the Date Picker for user selection
 * @author Ngo Le Bao Loc
 *
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{


	int year;
	int month;
	int day;
	int hour;
	int min;
	
	 @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
	 
	@SuppressWarnings("deprecation")
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		Bundle bundle = this.getArguments();
		hour = bundle.getInt("HH");
		min = bundle.getInt("MM");
		//CreateReminderFragment.TIME = new Date(this.year, month, day, hour, min);
		
		
	}
	
	


}
