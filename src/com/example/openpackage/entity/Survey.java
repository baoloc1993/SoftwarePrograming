package com.example.openpackage.entity;

import java.util.Date;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Survey {
	private static String CLASSNAME = "Survey";
	
//	private Customer user;
//	private Date date;
//	private String comment;
//	private int rate;
//	private FoodOpeningPackage type;
	
	private ParseObject mParseObject;
	
	public Survey( ParseObject mParseObject ){
		this.mParseObject = mParseObject;
	}
	
	public Survey( Customer user, Date date, String comment,int rate, FoodOpeningPackage type ) throws ParseException {
		mParseObject = new ParseObject(CLASSNAME); 
		mParseObject.put("user",user.getParseObject());
		mParseObject.put("date",date);
		mParseObject.put("comment", comment);
		mParseObject.put("rate", rate);
		mParseObject.put("type", type.getParseObject());
		save();
	}
	
	public Customer getUser() throws ParseException {
		ParseObject res = null;
		
		res = mParseObject.getParseObject("user").fetch();
		
		return new Customer(res);
	}
	
	public Date getDate() {
		return mParseObject.getDate("date");
	}
	
	public String getComment() {
		return mParseObject.getString("comment");
	}
	
	public int getRate() {
		return mParseObject.getInt("rate");
	}
	
	public String getID () {
		return mParseObject.getObjectId();
	}
	
	public ParseObject getParseObject() {
		return mParseObject;
	}
	
	public FoodOpeningPackage getFoodOpeningPackage() throws ParseException {
		ParseObject res = null;
		res = mParseObject.getParseObject("type").fetch();
		return new FoodOpeningPackage(res);
	}
	
	private void save() throws ParseException {
		mParseObject.save();
	}

	public static Survey findById(String id) throws ParseException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		return new Survey(query.get(id));
	}

	

}