package com.example.openpacket.entity;

import java.util.Date;

import com.parse.ParseException;
import com.parse.ParseObject;

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
	
	public Survey( Customer user, Date date, String comment,int rate, FoodOpeningPackage type ) {
		mParseObject = new ParseObject(CLASSNAME); 
		mParseObject.put("user",user.getParseObject());
		mParseObject.put("date",date);
		mParseObject.put("comment", comment);
		mParseObject.put("rate", rate);
		mParseObject.put("type", type.getParseObject());
		save();
	}
	
	public Customer getUser() {
		ParseObject res = null;
		try {
			 res = mParseObject.getParseObject("user").fetch();
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
	

	public ParseObject getParseObject() {
		return mParseObject;
	}
	
	private void save() {
		mParseObject.saveInBackground();
	}
}
