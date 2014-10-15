package com.example.openpackage.ui;

import java.util.ArrayList;
import java.util.TimerTask;

import com.example.openpackage.entity.Manufacturer;
import com.example.openpackage.entity.Reminder;
import com.example.openpackageapplication.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.sun.mail.iap.Argument;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class ListReminderFragment extends Fragment {

	public static ListView listRemindersView;
	ViewGroup viewGroup;
	
	public ListReminderFragment(){
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_reminder_layout, container,
				false);

		//Temporary create new Manufacturer Object
		//In reality, received the Manufacter Object from other Activity
		Manufacturer tempManufacturer = new Manufacturer(new ParseObject("test"));
		
		ArrayList<Reminder> listReminders = new ArrayList<Reminder>();
		try {
			listReminders = tempManufacturer.getReminderList();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final ArrayList<Reminder> newlist = listReminders;

		/**
		 * Calling a backgroung thread will loads recent articles of a website
		 *
		 * @param rss
		 *            url of website
		 * */
		//new LoadRSSFeedItems(listNews, mPullToRefreshLayout).execute();

		// selecting single ListView item
		// Launching new screen on Selecting Single ListItem
		/**
		 * LIST REMINDER
		 */
		ListReminderAdapter adapter = new ListReminderAdapter(
				getActivity(),
				R.layout.list_reminder_layout,
				listReminders);

		listRemindersView = (ListView) rootView.findViewById(R.id.list_reminders);
		listRemindersView.setAdapter(adapter);
		listRemindersView.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Reminder reminder = newlist.get(position);
				String reminderId = reminder.getParseObject().getObjectId();
				String reminderName = reminder.getName();
				long reminderDate = reminder.getTime().getTime();
				String reminderDesc = reminder.getDescription();
				boolean isActive = reminder.getActive();
				
				//Transfer Data to other Fragment
				Bundle bundle = new Bundle();
				bundle.putString("ID", reminderId);
				bundle.putString("Name", reminderName);
				bundle.putLong("Date", reminderDate);
				bundle.putString("Description", reminderDesc);
				bundle.putBoolean("Active", isActive);
				
				
				
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				EditReminderFragment editReminderFragment = new EditReminderFragment();
				editReminderFragment.setArguments(bundle);
				editReminderFragment.setHasOptionsMenu(true);

				//displaySwipeViewNewsFragment.setHasOptionsMenu(true);
				fragmentManager.beginTransaction()
						.replace(((ViewGroup)getView().getParent()).getId(), editReminderFragment)
						.commit();
				
				
			}
			//return onItemClickListener;
		});
		
		
		/**
		 * CREATE REMINDER
		 */
		Button createReminder = (Button)rootView.findViewById(R.id.create_reminder_button);
		createReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				CreateReminderFragment createReminderFragment = new CreateReminderFragment();

				//e.setArguments(args);

				// Go to DisplayFullNewsFragment
				//displaySwipeViewNewsFragment.setHasOptionsMenu(true);
				fragmentManager.beginTransaction()
						.replace(((ViewGroup)getView().getParent()).getId(), createReminderFragment)
						.commit();
			}
		});
		return rootView;
		
		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

//		listNews = (ListView) view.findViewById(R.id.listNews);
//
//		// We need to create a PullToRefreshLayout manually
//		mPullToRefreshLayout = new PullToRefreshLayout(view.getContext());
//		TimerTask timerTask = new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				new LoadRSSFeedItems(listNews, mPullToRefreshLayout).execute();
//			}
//		};
//		// We can now setup the PullToRefreshLayout
//		BasicFunctions.IniPullToRefresh(MainActivity.activity, (ViewGroup) view,
//				(View) listNews, timerTask, mPullToRefreshLayout);
	}
}
