package com.example.openpacket.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

public class FoodOpeningPackage {
	private static String CLASSNAME = "FoodOpeningPackage";
	
//	private String title;
//	private String description;
//	private String videoLink;
//	private String type;
//	private ArrayList<Survey> surveyList;
//	private double average;
	
	private ParseObject mParseObject;
	
	public FoodOpeningPackage(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	public FoodOpeningPackage(String title,String description, String videoLink, String type) {
		mParseObject = new ParseObject(CLASSNAME);
		mParseObject.put("title", title);
		mParseObject.put("description", description);
		mParseObject.put("videoLink", videoLink);
		mParseObject.put("type", type);
		mParseObject.put("average",0);
		save();
	}

	public String getTitle() {
		return mParseObject.getString("title");
	}
	
	public String getDescription() {
		return mParseObject.getString("description");
	}
	
	public String getvideoLink() {
		return mParseObject.getString("videoLink");
	}
	
	public String getType() {
		return mParseObject.getString("type");
	}
	
	public ArrayList<Survey> getSurveyList() {
		ArrayList<Survey> surveyList = new ArrayList<Survey>();
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");
		try {
			List<ParseObject> surveys = relation.getQuery().find();
			for(ParseObject survey : surveys ) {
				surveyList.add(new Survey(survey));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return surveyList;
	}
	
	public double getAverage() {
		return mParseObject.getDouble("average");
	}
	
	public void addSurvey( Survey survey ) {
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");
		relation.add(survey.getParseObject());
		save();
		
		ArrayList<Survey> surveyList = getSurveyList();
		double ave = 0;
		int count = 0;
		for( Survey msurvey : surveyList ) {
			count++;
			ave += msurvey.getRate();
		}
		mParseObject.put("average", ave/count);
		save();
	}
	
	public static ArrayList<FoodOpeningPackage> listAll() {
		ArrayList<FoodOpeningPackage> res = new ArrayList<FoodOpeningPackage>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		try {
			List<ParseObject> foodOpeningPackages = query.find();
			for(ParseObject foodOpeningPackage : foodOpeningPackages) {
				FoodOpeningPackage cur = new FoodOpeningPackage(foodOpeningPackage);
				res.add(cur);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	

	
	public ParseObject getParseObject() {
		return mParseObject;
	}
	
	private void save() {
		mParseObject.saveInBackground();
	}
}
