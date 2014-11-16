package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

/**
 * Interface that store some basic function a a FoodOpeningPackage
 * @author Tran Phuong Thao
 *
 */
public interface FoodOpeningPackage {
	
	/**
	 * get Title of object
	 * @return title of object
	 */
	public String getTitle();
	
	/**
	 * Get description of Object
	 * @return descritpion of Object
	 */
	public String getDescription();
	
	/**
	 * Get video link of object
	 * @return video link of object
	 */
	public String getvideoLink();
	
	/**
	 * Get type of packet object
	 * @return type of object
	 */
	public String getType();
	
	/**
	 * Get the list of survey of this food object
	 * @return
	 * @throws ParseException
	 */
	public ArrayList<Survey> getSurveyList() throws ParseException;
	
	/**
	 * Get the average rate of object
	 * @return average rate of object
	 */
	public double getAverage() ;
	
	/**
	 * get the ID of object
	 * @return ID of object
	 */
	public String getID () ;
	
	/**
	 * Add a survey to this object
	 * @param survey : survey want to be added to this object
	 * @throws ParseException
	 */
	public void addSurvey( Survey survey ) throws ParseException ;
	
	/**
	 * Get this packet object
	 * @return this packet object
	 */
	public ParseObject getParseObject();
}
