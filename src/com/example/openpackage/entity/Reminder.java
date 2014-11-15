package com.example.openpackage.entity;

import java.util.Date;

import com.parse.ParseException;
import com.parse.ParseObject;
/**
 * Interface of Reminder. Store basical function to control Reminder
 * @author Ngo Le Bao Loc
 * 
 */
public interface Reminder {
	
	/**
	 * Set the name of Reminder
	 * @param name : name of reminder
	 * @throws ParseException
	 */
	public void setName(String name) throws ParseException;
	
	/**
	 * Set the descritpion of reminder
	 * @param description : description of reminder
	 * @throws ParseException
	 */
	public void setDescription(String description) throws ParseException;
	
	/**
	 * Set Date and Time want to be alarmed of reminder
	 * @param time : time want to be alarmed
	 * @throws ParseException
	 */
	public void setTime(Date time) throws ParseException;
	
	/**
	 * Set the status of reminder
	 * @param active : true if active, false if inactive
	 * @throws ParseException
	 */
	public void setActive(boolean active) throws ParseException;
	
	/**
	 * Get the name of Reminder
	 * @return name of reminder
	 */
	public String getName();
	
	/**
	 * Get the description of reminder
	 * 
	 * @return description of reminder
	 */
	public String getDescription();
	
	/**
	 * Get Date Time which want to be alarmed of reminder. 
	 * @return Date Time of reminder
	 */
	public Date getTime();
	
	/**
	 * Get status of Reminder
	 * @return true if active, false if deactive
	 */
	public boolean getActive();
	
	/**
	 * Get ID of reminder
	 * @return id of reminder
	 */
	public String getID();
	
	/**
	 * Get Reminder object
	 * @return Reminder object 
	 */
	public ParseObject getParseObject();
	
	/**
	 * Delete Reminder
	 */
	public void delete();
}
