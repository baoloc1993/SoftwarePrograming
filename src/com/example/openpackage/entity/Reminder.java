package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Reminder {
	private static String CLASSNAME = "Reminder";
	
//	private Date time;
//	private String description;
//	private String name;
//	private boolean active;
	
	ParseObject mParseObject;
	
	public Reminder(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	public Reminder( String name, String description, Date time, boolean active ) throws ParseException {
		mParseObject = new ParseObject(CLASSNAME);
		mParseObject.put("name", name);
		mParseObject.put("description", description);
		mParseObject.put("time", time);
		mParseObject.put("active", active);
		save();
	}
	
	public void setName(String name) throws ParseException {
		mParseObject.put("name", name);
		save();
	}
	
	public void setDescription(String description) throws ParseException {
		mParseObject.put("description", description);
		save();
	}
	
	public void setTime(Date time) throws ParseException {
		mParseObject.put("time", time);
		save();
	}
	
	public void setActive(boolean active) throws ParseException {
		mParseObject.put("active", active);
		save();
	}
	
	public String getName() {
		return mParseObject.getString("name");
	}
	
	public String getDescription() {
		return mParseObject.getString("description");
	}
	
	public Date getTime() {
		return mParseObject.getDate("time");
	}
	
	public boolean getActive() {
		return mParseObject.getBoolean("active");
	}
	
	public String getID(){
		return mParseObject.getObjectId();
	}
	public static ArrayList<Reminder> listAll() throws ParseException {
		ArrayList<Reminder> res = new ArrayList<Reminder>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		
			List<ParseObject> reminders = query.find();
			for(ParseObject reminder : reminders) {
				Reminder cur = new Reminder(reminder);
				res.add(cur);
			}
		
		return res;
	}
	
	private void save() throws ParseException {
		mParseObject.save();
	}
	
	public ParseObject getParseObject() {
		return mParseObject;
	}
	
	public void delete(){
		try {
			mParseObject.delete();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
