package com.example.openpackage.controller;

import com.example.openpackage.entity.Reminder;
import com.parse.ParseException;
import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class ReminderController {
	Context mContext;
	ArrayList<Reminder> ReminderList;
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
	
	public  ArrayList<Reminder> getReminderList(){
		return ReminderList;
	}
	

	@SuppressWarnings("deprecation")
	public String validateReminderForm(String name, String description,Date time, boolean active,boolean isNewReminder, String ID){
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
		if( time.getYear() == c.get(Calendar.YEAR) && time.getMonth() < curMonth)
			return "Wrong input month of Reminder";
		if( time.getDate() < curDay)
			return "Wrong input day of Reminder";
		if( time.getHours() < curHour)
			return "Wrong input hour of Reminder";
		if(time.getMinutes() < curMinute)
			return "Wrong input minute of Reminder";
		
		try{
			if(isNewReminder){
			Reminder newReminder = new Reminder(name,description,time,active) ;
			ReminderList.add(newReminder);
			}
			else{
				boolean found = false;
				for(Reminder reminder : ReminderList){
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
			return "There is some error with internet connection.";
		}
		return "Successfull Update Reminder";
	}
	
}
