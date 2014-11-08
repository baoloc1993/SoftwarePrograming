package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.Date;

import com.parse.ParseObject;

public interface Reminder {
	public abstract void setName(String name) throws Exception;
	
	public abstract void setDescription(String description) throws Exception;
	
	public abstract void setTime(Date time) throws Exception;
	
	public abstract void setActive(boolean active) throws Exception;
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	public abstract Date getTime();
	
	public abstract boolean getActive();
	
	public abstract String getID();
	
	public abstract ParseObject getParseObject();
	
	public abstract void delete();
}
