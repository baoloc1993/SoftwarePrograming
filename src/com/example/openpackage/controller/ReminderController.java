package com.example.openpackage.controller;

import com.example.openpackage.entity.Factory;
import com.example.openpackage.entity.ManufacturerRemote;
import com.example.openpackage.entity.ReminderRemote;
import com.example.openpackage.entity.Reminder;
import com.example.openpackage.entity.User;
import com.example.openpackage.ui.MainUI;
import com.parse.ParseException;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
/**
 * Control all data of reminder
 * @author Ngo Le Bao Loc
 *
 */
public class ReminderController {
	Context mContext;
	/**
	 * List of reminder
	 */
	private static ArrayList<Reminder> reminderList;
	
	/**
	 * List of Alarm Serivce
	 */
	private static ArrayList<Intent> alarmIntent;
	
	/**
	 * Contructor of class
	 * @param context current context of application
	 */
	public ReminderController(Context context){
		mContext = context;
		try{
			reminderList =  Factory.listAllReminder("RemoteDB");
		}
		catch(ParseException e){
			e.printStackTrace();
			reminderList  = null;
		}
		
		alarmIntent = new ArrayList<Intent>();
	}
	
	/**
	 * Load all data of reminder of current user after user login to the database
	 * Start all reminder which has status is ACTIVE
	 */
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
	
	/**
	 * Get the list of reminder
	 * @return list of reminder
	 */
	public  ArrayList<Reminder> getReminderList(){
		return reminderList;
	}
	
	/**
	 * Add new alarm background service
	 * @param context current context of application
	 * @param i service want to be run in background
	 */
	public void addNewAlarm(Context context,Intent i){
		alarmIntent.add(i);
		i.setAction("CREATE");
		context.startService(i);
		
	}
	
	/**
	 * Stop a alarm service by its name
	 * @param name Name of alarm service which will be canceled 
	 */
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
	
	/**
	 * Remove all alarm background services
	 */
	public void removeAllAlarms(){
		Log.d("REMOVE","REMOVE ALL ALLARM");
		for(int i = 0; i < alarmIntent.size();++i){
			Intent j = alarmIntent.get(i);
			j.setAction("CANCEL");
			alarmIntent.remove(i);
			mContext.startService(j);
				
		}
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * Validate all data of reminder when create or edit the reminder item
	 * @param name Name of reminder
	 * @param description description of reminder
	 * @param time time of reminder
	 * @param active status of reminder. True is ACTIVE, False is DEACTIVE
	 * @param isNewReminder true when creating new Reminder, false when edit current Reminder
	 * @param ID ID of reminder. Null if create new Reminder
	 * @return Result Message.
	 */
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
		if( time.getYear() + 1900 < curYear )
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
				Log.d("INSIDE CONTROLLER", String.valueOf(time.getYear()));
				Reminder newReminder = Factory.createReminderObject("RemoteDB", name, description, time, active);
			
				reminderList.add(newReminder);
				try {
					ManufacturerRemote tmp = ManufacturerRemote.getCurrentUser();
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
						try {
							reminder.setActive(active);
							reminder.setDescription(description);
							reminder.setName(name);
							
							reminder.setTime(time);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
	
	/**
	 * Stop a Reminder service by its id
	 * @param id ID of reminder Serivece
	 * @return result message
	 */
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
