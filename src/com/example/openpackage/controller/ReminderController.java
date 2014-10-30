package com.example.openpackage.controller;

import com.example.openpackage.entity.Reminder;
import com.parse.ParseException;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class ReminderController {
	Context mContext;
	//To hold the reminder list when applicaton is running
	private static ArrayList<Reminder> ReminderList;
	//to hold the alarm set by reminder 
	private static ArrayList<Intent> alarmIntent;
	
	
	public ReminderController(Context context){
		mContext = context;
		try{
			ReminderList =  Reminder.listAll();
		}
		catch(ParseException e){
			e.printStackTrace();
			ReminderList  = null;
		}
	}
	/*
	 * getReminderList(): get list of all reminder set by current account(manufacture)
	 * saveAlarm(): save information(Intent type) about alarm to list
	 * removeAlarm(): remove alarm when reminder is deactivated
	 * removeAllAlarm(): remove all alarm when user log out; 
	 */
	public  ArrayList<Reminder> getReminderList(){
		return ReminderList;
	}
	
	public void saveAlarm(Intent i){
		alarmIntent.add(i);
	}
	
	public void removeAlarm(Context mContext,String Name){
		Intent j = new Intent();
		for(Intent i : alarmIntent){
			if(i.getStringExtra("name").equals(Name)){
				j= i;
				break;
			}
		}
		
		j.setAction("CANCEL");
		mContext.startService(j);
	}
	
	public void removeAllAlarm(Context mContext){
		for(Intent i : alarmIntent){
			i.setAction("CANCEL");
			mContext.startActivity(i);
		}
		
	}
	@SuppressWarnings("deprecation")
	public String validateReminderForm(String name, String description,Date time, boolean active,boolean isNewReminder, String ID){
		//Log.d("DEBUG", "inside validate reminder");
		if(name==null || description == null || time == null){
			return "Error in validate form";
		}
		if( name.isEmpty()){
			return "You need to fill the name of Reminder";
		}
		if(description.isEmpty()){
			return "You need to fill the description of Reminder";
		}
		String dateTimeError = "Date/Time Error!!";
		Calendar c = Calendar.getInstance();

		int curDay = c.get(Calendar.DATE);
		int curMonth = c.get(Calendar.MONTH);
		int curYear = c.get(Calendar.YEAR);
		int curHour = c.get(Calendar.HOUR);
		int curMinute = c.get(Calendar.MINUTE);
		if( time.getYear() < curYear )
			return dateTimeError;
		if( time.getYear() == curYear){
			if(time.getMonth() ==  curMonth){
				 if(time.getDate() == curDay){
					 if(time.getHours() == curHour){
						 if(time.getMinutes() < curMinute){
							 return dateTimeError;
						 }
					 }
					 if(time.getHours() < curHour){
						 return dateTimeError;
					 }
						 
				 }
				 if(time.getDate() < curDay){
					 return dateTimeError;
				 }
				 
			}
			
			if(time.getMonth() <  curMonth){
				return dateTimeError;
			}
		}
		
		try{
			
			if(isNewReminder){
			//Log.d("DEBUG", "inside create new reminder");
			Reminder newReminder = new Reminder(name,description,time,active) ;
			ReminderList.add(newReminder);
			}
			else{
				boolean isFound = false;
				for(Reminder reminder : ReminderList){
					if(ID.compareTo(reminder.getID())==0){
						reminder.setActive(active);
						reminder.setDescription(description);
						reminder.setName(name);
						reminder.setTime(time);
						isFound = true;
						break;
					}
				}
				if(!isFound){
					return "Invalid ID of reminder!!! Need report";
				}
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
			return "There is some error with reminder";
		}
		
		return "Successfull Update Reminder";
	}
	
}
