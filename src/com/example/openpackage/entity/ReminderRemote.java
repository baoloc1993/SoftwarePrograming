package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Store reminder data . Get data from online database
 * @author NGO LE BAO LOC
 *
 */
public class ReminderRemote implements Reminder {
	private static String CLASSNAME = "Reminder";
	
	ParseObject mParseObject;
	
	/**
	 * Contructor of Reminder
	 * @param mParseObject Reminder object
	 */
	public ReminderRemote(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	/**
	 * Contructor of Reminder
	 * @param name : name of reminder
	 * @param description : description of reminder
	 * @param time : Time of reminder
	 * @param active : status of reminder. True if active, false if deactive
	 * @throws ParseException
	 */
	public ReminderRemote( String name, String description, Date time, boolean active ) throws ParseException {
		time.setYear(time.getYear() - 1900);
		
		mParseObject = new ParseObject(CLASSNAME);
		mParseObject.put("name", name);
		mParseObject.put("description", description);
		mParseObject.put("time", time);
		mParseObject.put("active", active);
		save();
	}
	
	@Override
	public void setName(String name) throws ParseException {
		mParseObject.put("name", name);
		save();
	}
	
	@Override
	public void setDescription(String description) throws ParseException {
		mParseObject.put("description", description);
		save();
	}
	
	@Override
	public void setTime(Date time) throws ParseException {
		if (time.getYear() > 1900){
			time.setYear(time.getYear() - 1900);
		}
		
		mParseObject.put("time", time);
		save();
	}
	
	@Override
	public void setActive(boolean active) throws ParseException {
		mParseObject.put("active", active);
		save();
	}
	
	@Override
	public String getName() {
		return mParseObject.getString("name");
	}
	
	@Override
	public String getDescription() {
		return mParseObject.getString("description");
	}
	
	@Override
	public Date getTime() {
		return mParseObject.getDate("time");
	}
	
	@Override
	public boolean getActive() {
		return mParseObject.getBoolean("active");
	}
	
	@Override
	public String getID(){
		return mParseObject.getObjectId();
	}
	
	/**
	 * list all reminders database have
	 * @return ArrayList of all reminder which exist in database
	 * @throws ParseException
	 */
	public ArrayList<Reminder> listAll() throws ParseException {
		ArrayList<Reminder> res = new ArrayList<Reminder>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		
			List<ParseObject> reminders = query.find();
			for(ParseObject reminder : reminders) {
				ReminderRemote cur = new ReminderRemote(reminder);
				res.add(cur);
			}
		
		return res;
	}
	
	/**
	 * Save the Reminder. Put data of reminder to online databse
	 * @throws ParseException
	 */
	private void save() throws ParseException {
		mParseObject.save();
	}
	
	@Override
	public ParseObject getParseObject() {
		return mParseObject;
	}
	
	@Override
	public void delete(){
		try {
			mParseObject.delete();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
