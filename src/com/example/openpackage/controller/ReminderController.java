package com.example.openpackage.controller;

import com.example.openpackage.entity.Manufacturer;
import com.example.openpackage.entity.Reminder;
import com.example.openpackage.entity.User;
import com.parse.ParseException;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class ReminderController {
	Context mContext;
	private static ArrayList<Reminder> reminderList;
	private static ArrayList<Intent> alarmIntent;
	public ReminderController(Context context){
		mContext = context;
		try{
			reminderList =  Reminder.listAll();
		}
		catch(ParseException e){
			e.printStackTrace();
			reminderList  = null;
		}
		
		alarmIntent = new ArrayList<Intent>();
	}
	
	public void firstUpdateAlarmAfterLogIn(){
		for(Reminder r : reminderList){
			if(r.getActive()){
				Intent i = new Intent(mContext,ReminderService.class);
				i.putExtra("name", r.getName());
				i.putExtra("description", r.getDescription());
				i.putExtra("year", r.getTime().getYear());
				i.putExtra("month", r.getTime().getMonth());
				i.putExtra("day", r.getTime().getDay());
				i.putExtra("hour",r.getTime().getHours());
				i.putExtra("minute", r.getTime().getMinutes());
				i.setAction("CREATE");
				alarmIntent.add(i);
				mContext.startService(i);
			}
		}
	}
	public  ArrayList<Reminder> getReminderList(){
		return reminderList;
	}
	
	public void addNewAlarm(Context context,Intent i){
		alarmIntent.add(i);
		i.setAction("CREATE");
		context.startService(i);
		
	}
	
	private void removeAlarm(String name){
		Intent j = new Intent(mContext,ReminderService.class);
		for(int i = 0; i < alarmIntent.size();++i){
			j = alarmIntent.get(i);
			if(j.getStringExtra("name").compareTo(name) == 0){
				j.setAction("CANCEL");
				alarmIntent.remove(i);
				break;
				
			}
		}
		mContext.startService(j);
	}
	public void removeAllAlarms(){
		for(int i = 0; i < alarmIntent.size();++i){
			Intent j = alarmIntent.get(i);
			j.setAction("CANCEL");
			alarmIntent.remove(i);
			mContext.startService(j);
				
		}
	}
	@SuppressWarnings("deprecation")
	public String validateReminderForm(String name, String description,Date time, boolean active,boolean isNewReminder, String ID){
		Log.d("DEBUG", "inside validate reminder");
		if( name.isEmpty()){
			return "You need to fill the name of Reminder";
		}
		if(description.isEmpty()){
			return "You need to fill the description of Reminder";
		}
		Calendar c = Calendar.getInstance();
		int curDay = c.get(Calendar.DATE);
		int curMonth = c.get(Calendar.MONTH);
		int curYear = c.get(Calendar.YEAR);
		int curHour = c.get(Calendar.HOUR);
		int curMinute = c.get(Calendar.MINUTE);
		if( time.getYear() < curYear )
			return "Wrong input year of Reminder";
		if( time.getYear() == curYear){
			if(time.getMonth() ==  curMonth){
				 if(time.getDate() == curDay){
					 if(time.getHours() == curHour){
						 if(time.getMinutes() < curMinute){
							 return "Wrong input minute of Reminder";
						 }
					 }
					 if(time.getHours() < curHour){
						 return "Wrong input hour of Reminder";
					 }
						 
				 }
				 if(time.getDate() < curDay){
					 return "Wrong input day of Reminder";
				 }
				 
			}
			
			if(time.getMonth() <  curMonth){
				return "Wrong input month of Reminder";
			}
		}
		
		try{
			if(isNewReminder){
				//Log.d("DEBUG", "inside create new reminder");
				Reminder newReminder = new Reminder(name,description,time,active) ;
			
				reminderList.add(newReminder);
				try {
					Manufacturer tmp = Manufacturer.getCurrentUser();
					tmp.addReminder(newReminder);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				boolean found = false;
				for(Reminder reminder : reminderList){
					if(ID.compareTo(reminder.getID())==0){
						reminder.setActive(active);
						reminder.setDescription(description);
						reminder.setName(name);
						reminder.setTime(time);
						found = true;
						break;
					}
				}
				if(!found){
					return "Invalid ID of reminder!!! Need report";
				}
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
			return "Some errors happen in database.";
		}
		
		return "Successfull Update Reminder";
	}
	
	//REMOVE REMINDER
	public String removeReminder (String id){
		for (Reminder reminder : reminderList){
			if (id.compareTo(reminder.getID()) == 0){
				removeAlarm(reminder.getName());
				reminder.delete();
				return ("Remove Successfully");
				
			}
		}
		return "Remove Fail";
		
	}
	
}
