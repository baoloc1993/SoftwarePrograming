package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

public class ManufacturerRemote extends User {
	private static String CLASSNAME = "Manufacturer";

	//private ArrayList<Reminder> reminderList;
	
	private ParseObject mParseObject;
	
	public ManufacturerRemote(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	public void addReminder( Reminder reminder ) throws ParseException {
		ParseRelation<ParseObject> relation = mParseObject.getRelation("reminderList");
		relation.add(reminder.getParseObject());
		save();
	}

	public static ArrayList<ManufacturerRemote> listAll() throws ParseException {
		ArrayList<ManufacturerRemote> res = new ArrayList<ManufacturerRemote>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);

		List<ParseObject> manufacturers = query.find();
		for(ParseObject manufacturer : manufacturers) {
			ManufacturerRemote cur = new ManufacturerRemote(manufacturer);
			res.add(cur);
		}
		
		return res;
	}
	
	public ArrayList<Reminder> getReminderList() throws ParseException {
		/*
		ArrayList<ReminderRemote> reminderList = new ArrayList<ReminderRemote>();
		ParseRelation<ParseObject> relation = mParseObject.getRelation("reminderList");
	
		List<ParseObject> reminders = relation.getQuery().find();
		for(ParseObject reminder : reminders ) {
			reminderList.add(new ReminderRemote(reminder));
		}
		
		return reminderList;
		*/
		return Factory.listAllReminder("RemoteDB");
	}
	
	@Override
	public void logIn() throws ParseException {
		mParseObject.pin();
	}
	
	@Override
	public void logOut() throws ParseException {
		mParseObject.unpin();
	}
	
	public static ManufacturerRemote getCurrentUser() throws ParseException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		query.fromLocalDatastore();
		List<ParseObject> manufacturers = query.find();
		if (manufacturers.isEmpty()) return null;
		return new ManufacturerRemote(manufacturers.get(0));
	}
	
	@Override
	public String getUsername() {
		return mParseObject.getString("username");
	}

	@Override
	public String getPassword() {
		return mParseObject.getString("password");
	}

	@Override
	public String getEmail() {
		return mParseObject.getString("email");
	}

	
	public ParseObject getParseObject() {
		return this.mParseObject;
	}
	
	private void save() throws ParseException {
		mParseObject.save();
	}

	
}
