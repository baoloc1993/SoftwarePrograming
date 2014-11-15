package com.example.openpackage.entity;

import java.util.Date;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Interface of Survey. Store all basic function related to survey
 * @author Nguyen Phan Huy
 *
 */
public interface Survey {
	/**
	 * get the user which make the survey
	 * @return the user make the survey
	 * @throws ParseException
	 */
	public CustomerRemote getUser() throws ParseException;
	
	/**
	 * Get the date and time created of survey
	 * @return
	 */
	public Date getDate();
	
	/**
	 * Get the comment of the survey
	 * @return  comment of the survey
	 */
	public String getComment();
	
	/**
	 * get Rate of survey. From 1 to 5
	 * @return rate of survey
	 */
	public int getRate();
	
	/**
	 * get ID of survey
	 * @return ID of survey
	 */
	public String getID ();
	
	/**
	 * Get the survey object
	 * @return Survey object
	 */
	public ParseObject getParseObject();
	
	/**
	 * Get the FoodOpeningPackage which is rated
	 * @return the FoodOpeningPackage object
	 * @throws ParseException
	 */
	public FoodOpeningPackage getFoodOpeningPackage() throws ParseException ;
	
}
