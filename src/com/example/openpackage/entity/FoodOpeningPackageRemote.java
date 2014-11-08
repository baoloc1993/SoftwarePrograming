package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

public class FoodOpeningPackageRemote {
	private static String CLASSNAME = "FoodOpeningPackage";
	
//	private String title;
//	private String description;
//	private String videoLink;
//	private String type;
//	private ArrayList<Survey> surveyList;
//	private double average;
	
	private ParseObject mParseObject;
	
	public FoodOpeningPackageRemote(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	public FoodOpeningPackageRemote(String title,String description, String videoLink, String type) throws ParseException {
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
	
	public ArrayList<SurveyRemote> getSurveyList() throws ParseException {
		ArrayList<SurveyRemote> surveyList = new ArrayList<SurveyRemote>();
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");

		List<ParseObject> surveys = relation.getQuery().find();
		for(ParseObject survey : surveys ) {
			surveyList.add(new SurveyRemote(survey));
		}
	
		return surveyList;
	}
	
	public double getAverage() {
		return mParseObject.getDouble("average");
	}
	
	public String getID () {
		return mParseObject.getObjectId();
	}
	
	public static FoodOpeningPackageRemote findById( String Id ) throws ParseException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		return new FoodOpeningPackageRemote(query.get(Id));
	}
	
	
	public void addSurvey( SurveyRemote survey ) throws ParseException {
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");
		relation.add(survey.getParseObject());
		save();
		
		ArrayList<SurveyRemote> surveyList = getSurveyList();
		double ave = 0;
		int count = 0;
		for( SurveyRemote msurvey : surveyList ) {
			count++;
			ave += msurvey.getRate();
		}
		mParseObject.put("average", ave/count);
		save();
	}
	
	public static ArrayList<FoodOpeningPackageRemote> listAll() throws ParseException {
		ArrayList<FoodOpeningPackageRemote> res = new ArrayList<FoodOpeningPackageRemote>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		
		List<ParseObject> foodOpeningPackages = query.find();
		for(ParseObject foodOpeningPackage : foodOpeningPackages) {
			FoodOpeningPackageRemote cur = new FoodOpeningPackageRemote(foodOpeningPackage);
			res.add(cur);
		}
		
		return res;
	}
	

	
	public ParseObject getParseObject() {
		return mParseObject;
	}
	
	private void save() throws ParseException {
		mParseObject.save();
	}
}
