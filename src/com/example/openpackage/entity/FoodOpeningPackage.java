package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

public interface FoodOpeningPackage {
	public String getTitle();
	
	public String getDescription();
	
	public String getvideoLink();
	
	public String getType();
	
	public ArrayList<Survey> getSurveyList() throws ParseException;
	
	public double getAverage() ;
	
	public String getID () ;
	
	public void addSurvey( Survey survey ) throws ParseException ;
	
	public ParseObject getParseObject();
}
