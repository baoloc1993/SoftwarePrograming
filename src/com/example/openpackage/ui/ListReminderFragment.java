package com.example.openpackage.ui;

/**
 * Display the list of list reminder
 */
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.openpackage.controller.UserController;
import com.example.openpackage.entity.ManufacturerRemote;
import com.example.openpackage.entity.Reminder;
import com.example.openpackage.entity.ReminderRemote;
import com.example.openpackageapplication.R;
import com.parse.ParseException;
import com.parse.ParseObject;

/**
 *  The Fragment display:
 * - List of reminder, each reminder have : ID, Date, Name, Description, Status (active or not)
 * - Create Reminder Button
 * 
 * When user click to each reminder on the list, user will go to EditReminderFragment to edit Reminder
 * @author Ngo Le Bao Loc
 * 
 *
 */
public class ListReminderFragment extends Fragment {

	public static ListView listRemindersView;
	ViewGroup viewGroup;
	private UserController mUserController;
	
	public ListReminderFragment(){
		mUserController = new UserController(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.list_reminder_layout, container,
				false);

		//Temporary create new Manufacturer Object
		//In reality, received the Manufacter Object from other Activity
		
		ManufacturerRemote tempManufacturer = (ManufacturerRemote) mUserController.getCurrentUser();
		
		ArrayList<Reminder> listReminders = null;
		try {
			listReminders = tempManufacturer.getReminderList();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final ArrayList<Reminder> newlist = listReminders;

		
		// selecting single ListView item
		// Launching new screen on Selecting Single ListItem
		/*
		 * LIST REMINDER
		 */
		ListReminderAdapter adapter = new ListReminderAdapter(
				getActivity(),
				R.layout.list_reminder_layout,
				listReminders);

		listRemindersView = (ListView) rootView.findViewById(R.id.list_reminders);
		listRemindersView.setAdapter(adapter);
		/*
		 * Go to Edit Reminder Acvitity
		 */
		listRemindersView.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				//Get Information of Reminder
				Reminder reminder = newlist.get(position);
				String reminderId = reminder.getID();
				String reminderName = reminder.getName();
				int reminderDay = reminder.getTime().getDate();
				int reminderMonth = reminder.getTime().getMonth();
				int reminderYear = reminder.getTime().getYear();
				int reminderHour = reminder.getTime().getHours();
				int reminderMinute = reminder.getTime().getMinutes();
				String reminderDesc = reminder.getDescription();
				boolean isActive = reminder.getActive();
				
				//Transfer Data to other Fragment
				Bundle bundle = new Bundle();
				bundle.putString("ID", reminderId);
				bundle.putString("Name", reminderName);
				bundle.putInt("Day", reminderDay);
				bundle.putInt("Month", reminderMonth);
				bundle.putInt("Year", reminderYear);
				bundle.putInt("Hour", reminderHour);
				bundle.putInt("Minute", reminderMinute);
				bundle.putString("Description", reminderDesc);
				bundle.putBoolean("Active", isActive);
				
				//Go to EditReminder Activity
				Intent i = new Intent(getActivity(), EditReminderActivity.class);
				i.putExtras(bundle);
				startActivity(i);

				
			}
			
		});
		
		
		/*
		 * CREATE REMINDER
		 */
		ImageView createReminder = (ImageView) rootView.findViewById(R.id.new_reminder_button);
		createReminder.setImageResource(R.drawable.add_icon);
		int size = (int) (MainUIManufacturer.getStandardSize() * 0.1);
		createReminder.getLayoutParams().width = size;
		createReminder.getLayoutParams().height = size;
		
		/*
		 * Go to Create Reminder Acvitivy
		 */
		createReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent i = new Intent(getActivity(), CreateReminderActivity.class);
				startActivity(i);

			}
		});
		return rootView;
		
		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
}
