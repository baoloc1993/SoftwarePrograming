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
	
	public Reminder( String name, String description, Date time, boolean active ) {
		mParseObject = new ParseObject(CLASSNAME);
		mParseObject.put("name", name);
		mParseObject.put("description", description);
		mParseObject.put("time", time);
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
	
	public static ArrayList<Reminder> listAll() {
		ArrayList<Reminder> res = new ArrayList<Reminder>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		try {
			List<ParseObject> reminders = query.find();
			for(ParseObject reminder : reminders) {
				Reminder cur = new Reminder(reminder);
				res.add(cur);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private void save() {
		mParseObject.saveInBackground();
	}
	
	public ParseObject getParseObject() {
		return mParseObject;
	}
	
}
