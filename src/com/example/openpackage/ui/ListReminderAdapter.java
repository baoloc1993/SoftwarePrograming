package com.example.openpackage.ui;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.openpackage.entity.Reminder;
import com.example.openpackage.entity.ReminderRemote;
import com.example.openpackageapplication.R;

/**
 * ListReminderAdapter
 * - Display and Control the list of Reminder by using an ArrayList of Reminder class
 * @author NGO LE BAO LOC
 * 
 *
 */
public class ListReminderAdapter extends ArrayAdapter<Reminder> {

	
	/**
	 * Contructor of Adapter
	 * 
	 * @param context context of current Application
	 * 
	 */
	public ListReminderAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<Reminder> listReminders;
	
	/**
	 * here we must override the constructor for ArrayAdapter the only variable
	 * we care about now is ArrayList<Reminder> objects, because it is the list of
	 * objects we want to display.
	 * 
	 * @param context  current context of Application
	 * @param textViewResourceId 
	 * @param listReminders List of Reminder we want to control
	 */
	
	public ListReminderAdapter(Context context, int textViewResourceId,
			ArrayList<Reminder> listReminders) {
		super(context, textViewResourceId, listReminders);
		this.listReminders = listReminders;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater
					.inflate(R.layout.singleview_reminder_layout, null);

		}

		/*
		 * Recall that the variable position is sent in as an argument to this
		 * method. The variable simply refers to the position of the current
		 * object in the list. (The ArrayAdapter iterates through the list we
		 * sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		Reminder i = listReminders.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			TextView order = (TextView) v.findViewById(R.id.order_single_reminder);
			TextView nameInfo = (TextView) v.findViewById(R.id.name_info_single_reminder);
			TextView dateInfo = (TextView) v.findViewById(R.id.date_info_single_reminder);
			TextView descInfo = (TextView) v.findViewById(R.id.desc_info_single_reminder);
			ImageView status = (ImageView) v.findViewById(R.id.status_reminder);
			

			// check to see if each individual textview is null.
			// if not, assign some text!
			// Config parameter of each textview and imageview(Resize)

			if (order != null){
				order.setText(String.valueOf(position + 1));
				order.setTypeface(null, Typeface.BOLD);
			}
			
			if (nameInfo != null) {
				nameInfo.setText(i.getName());
			}
			if (dateInfo != null) {
				
				Date date = i.getTime();
				int year = date.getYear() + 1900;
				int month =  date.getMonth() + 1 ;
				int day = date.getDate();
				int hour = date.getHours();
				int minute = date.getMinutes();
				
				
				String result = String.format("%2d : %2d   %2d/%2d/%4d", hour,minute,day,month,year);
				dateInfo.setText(result);
			}
			
			if (status != null){
				int size = (int) (ManufacturerUI.getStandardSize() * 0.1);
				status.getLayoutParams().width = size;
				status.getLayoutParams().height = size;
				if (i.getActive()){
					status.setImageResource(R.drawable.active);
				}else{
					status.setImageResource(R.drawable.inactive);
				}
			}
			
			if (descInfo != null){
				descInfo.setText(i.getDescription());
			}

		}

		// the view must be returned to our activity
		return v;

	}

}
