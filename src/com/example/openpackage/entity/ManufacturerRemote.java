package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
/**
 * Store the information of Manufacturer
 * @author Nguyen Tuan Anh
 *
 */
public class ManufacturerRemote extends User {
	/** The static constant String TABLENAME*/
	private static String TABLENAME = "Manufacturer";

	/** The ParseObect attribute*/
	private ParseObject mParseObject;
	
	/**
	 * Instantiate a new ManufacturerRemote
	 * @param mParseObject The ParseObject
	 */
	public ManufacturerRemote(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	/**
	 * Add a reminder to Manufacturer reminder list
	 * @param reminder The reminder
	 * @throws ParseException
	 */
	public void addReminder( Reminder reminder ) throws ParseException {
		ParseRelation<ParseObject> relation = mParseObject.getRelation("reminderList");
		relation.add(reminder.getParseObject());
		save();
	}

	
	/**
	 * Gets a static list of Manufacturer Remote object
	 * @return Returns an ArrayList of ManufacturerRemote
	 * @throws ParseException
	 */
	public static ArrayList<ManufacturerRemote> listAll() throws ParseException {
		ArrayList<ManufacturerRemote> res = new ArrayList<ManufacturerRemote>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLENAME);

		List<ParseObject> manufacturers = query.find();
		for(ParseObject manufacturer : manufacturers) {
			ManufacturerRemote cur = new ManufacturerRemote(manufacturer);
			res.add(cur);
		}
		
		return res;
	}
	
	/**
	 * Gets a list of reminder set up by Manufacturer
	 * @return Returns an ArrayList of reminder
	 * @throws ParseException
	 */
	public ArrayList<Reminder> getReminderList() throws ParseException {
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
		ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLENAME);
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

	/**
	 * Gets the ParseObject
	 * @return Returns the ParseObect 
	 */
	public ParseObject getParseObject() {
		return this.mParseObject;
	}
	
	/**
	 * Save information of Manufacturer to Database
	 * @throws ParseException
	 */
	private void save() throws ParseException {
		mParseObject.save();
	}

	
}
