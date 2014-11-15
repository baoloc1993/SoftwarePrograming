package com.example.openpackage.entity;

import java.util.Date;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Store all data of Survey. Get data from online database
 * @author Nguyen Phan Huy
 *
 */
public class SurveyRemote implements Survey{
	private static String CLASSNAME = "Survey";
	
	private ParseObject mParseObject;
	
	/**
	 * Contructor of class
	 * @param mParseObject a Survey Object
	 */
	public SurveyRemote( ParseObject mParseObject ){
		this.mParseObject = mParseObject;
	}
	
	/**
	 * Contructor of class
	 * @param user : User which rate this survey
	 * @param date : The date/time when survey is created
	 * @param comment : Comment of the survey
	 * @param rate : Rate of the survey (from 1 to 5)
	 * @param type : FoodOpeningPackage object which is rated
	 * @throws ParseException
	 */
	public SurveyRemote( CustomerRemote user, Date date, String comment,int rate, FoodOpeningPackage type ) throws ParseException {
		mParseObject = new ParseObject(CLASSNAME); 
		mParseObject.put("user",user.getParseObject());
		mParseObject.put("date",date);
		mParseObject.put("comment", comment);
		mParseObject.put("rate", rate);
		mParseObject.put("type", type.getParseObject());
		save();
	}
	
	@Override
	public CustomerRemote getUser() throws ParseException {
		ParseObject res = null;
		
		res = mParseObject.getParseObject("user").fetch();
		
		return new CustomerRemote(res);
	}
	
	@Override
	public Date getDate() {
		return mParseObject.getDate("date");
	}
	
	@Override
	public String getComment() {
		return mParseObject.getString("comment");
	}
	
	@Override
	public int getRate() {
		return mParseObject.getInt("rate");
	}
	
	@Override
	public String getID () {
		return mParseObject.getObjectId();
	}
	
	@Override
	public ParseObject getParseObject() {
		return mParseObject;
	}
	
	@Override
	public FoodOpeningPackage getFoodOpeningPackage() throws ParseException {
		ParseObject res = null;
		res = mParseObject.getParseObject("type").fetch();
		return new FoodOpeningPackageRemote(res);
	}
	
	/**
	 * Save all data of survey and push to online database
	 * @throws ParseException
	 */
	private void save() throws ParseException {
		mParseObject.save();
	}

	/**
	 * get the Survey by its ID
	 * @param id : id of survey want to get
	 * @return : a result survey. Null if cannot get any survey
	 * @throws ParseException
	 */
	public static Survey findById(String id) throws ParseException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		return new SurveyRemote(query.get(id));
	}

	

}
