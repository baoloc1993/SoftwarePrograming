package com.example.openpackage.ui;

import java.util.TimerTask;

import com.example.openpackageapplication.R;

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

	public static ListView listReminders;
	ViewGroup viewGroup;
	
	public ListReminderFragment(){
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_reminder_layout, container,
				false);

		

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

		listReminders = (ListView) rootView.findViewById(R.id.list_reminders);
		listReminders.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				EditReminderFragment editReminderFragment = new EditReminderFragment();

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
//		Button createReminder = (Button)rootView.findViewById(R.id.create_reminder_button);
//		createReminder.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				FragmentManager fragmentManager = getActivity().getFragmentManager();
//				CreateReminderFragment createReminderFragment = new CreateReminderFragment();
//
//				//e.setArguments(args);
//
//				// Go to DisplayFullNewsFragment
//				//displaySwipeViewNewsFragment.setHasOptionsMenu(true);
//				fragmentManager.beginTransaction()
//						.replace(((ViewGroup)getView().getParent()).getId(), createReminderFragment)
//						.commit();
//			}
//		});
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
