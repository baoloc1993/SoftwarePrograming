package com.example.openpackage.controller;

import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.widget.Toast;

public class ReminderService extends IntentService{
	/*
	 * Using putAction() to set action to intent that send to reminder service
	 * - CREATE to create background service for reminder
	 * - CANCEL to kill service
	 * String name in constructor refer to id of the reminder
	 * Intent must put ("year","month","day","hour","minute") by using putIntExtra
	 */
	public final static String CREATE = "CREATE";
	public final static String CANCEL = "CANCEL";
	private final static int RQS_1 = 1;
	private IntentFilter matcher;
	public ReminderService() {
		super("Tag");
		matcher = new IntentFilter();
		matcher.addAction(CREATE);
		matcher.addAction(CANCEL);
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onHandleIntent(Intent i) {
		// TODO Auto-generated method stub
		String action = i.getAction();
		//Set up the time for alarm
		Calendar calSet = Calendar.getInstance();
		//Intent getIntent = new  Intent();
		Bundle bundle = i.getExtras();
		int year = bundle.getInt("year");
		
		//calSet.set(i.getIntExtra("year", time.getYear()), i.getIntExtra("month", time.getMonth()+1), i.getIntExtra("day", time.getDay()));
		calSet.set(Calendar.YEAR, year);
		calSet.set(Calendar.MONTH, bundle.getInt("month"));
		calSet.set(Calendar.DAY_OF_MONTH, bundle.getInt("day"));
		calSet.set(Calendar.HOUR_OF_DAY, i.getIntExtra("hour", 0));
		calSet.set(Calendar.MINUTE, i.getIntExtra("minute", 0));
		calSet.set(Calendar.SECOND, 0);
		calSet.set(Calendar.MILLISECOND, 0);
		
		
		Log.d("YEAR REMINDER", String.valueOf(calSet.getTimeInMillis()));
		//Create the alarm service using alarm manager
		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent ni = new Intent(this,AlarmReceiver.class);
		ni.putExtra("description", i.getStringExtra("description"));
		ni.putExtra("name", i.getStringExtra("name"));
		PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), RQS_1, ni, 0);
		if(matcher.matchAction(action)){
			Log.d("Calendar Alarm Match" , calSet.getTime().toLocaleString());
			if(CREATE.equals(action)){
				//Log.d("DEBUG","Inside create alarm");
				am.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), pi);
			}
			else{
				am.cancel(pi);
			}
		}
	}
}
