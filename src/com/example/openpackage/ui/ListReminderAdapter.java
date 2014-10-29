package com.example.openpackage.ui;
/**
 * ListReminderAdapter
 * 
 * - Display the list of Reminder by using an ArrayList of Reminder class
 */
import java.util.ArrayList;
import java.util.List;







import com.example.openpackage.entity.Reminder;
import com.example.openpackageapplication.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListReminderAdapter extends ArrayAdapter<Reminder> {

	public ListReminderAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<Reminder> listReminders;
	
	/*
	 * here we must override the constructor for ArrayAdapter the only variable
	 * we care about now is ArrayList<Item> objects, because it is the list of
	 * objects we want to display.
	 */
	public ListReminderAdapter(Context context, int textViewResourceId,
			ArrayList<Reminder> listReminders) {
		super(context, textViewResourceId, listReminders);
		this.listReminders = listReminders;
	}
	
	
	/*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
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
			
			ImageView status = (ImageView) v.findViewById(R.id.status_reminder);
			

			// check to see if each individual textview is null.
			// if not, assign some text!
			// Config parameter of each textview and imageview(Resize)

			if (order != null){
				order.setText("stt");
			}
			
			if (nameInfo != null) {
				nameInfo.setText(i.getDescription());
			}
			if (dateInfo != null) {
				dateInfo.setText(i.getTime().toString());
			}
			
			if (status != null){
				if (i.getActive()){
					status.setImageResource(R.drawable.ic_launcher);
				}else{
					status.setImageResource(R.drawable.ic_launcher);
				}
			}

		}

		// the view must be returned to our activity
		return v;

	}

}
