package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

/**
 * A class store all data of FoodOpeningPacket. Data is get from online dabase
 * @author Tran Phuong Thao
 *
 */
public class FoodOpeningPackageRemote implements FoodOpeningPackage{
	
	/**
	 * Name of table in online database
	 */
	private static String TABLE_NAME = "FoodOpeningPackage";
	
	/**
	 * Object of this class
	 */
	private ParseObject mParseObject;
	
	/**
	 * Contructor of class 
	 * @param mParseObject : FoodOpeningPackageRemote object
	 */
	public FoodOpeningPackageRemote(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	/**
	 * Contructor of class
	 * @param title : Title of object
	 * @param description : description of object
	 * @param videoLink : link of video of object
	 * @param type : type of object
	 * @throws ParseException
	 */
	public FoodOpeningPackageRemote(String title,String description, String videoLink, String type) throws ParseException {
		mParseObject = new ParseObject(TABLE_NAME);
		mParseObject.put("title", title);
		mParseObject.put("description", description);
		mParseObject.put("videoLink", videoLink);
		mParseObject.put("type", type);
		mParseObject.put("average",0);
		save();
	}

	@Override
	public String getTitle() {
		return mParseObject.getString("title");
	}
	
	@Override
	public String getDescription() {
		return mParseObject.getString("description");
	}
	
	@Override
	public String getvideoLink() {
		return mParseObject.getString("videoLink");
	}
	
	@Override
	public String getType() {
		return mParseObject.getString("type");
	}
	
	@Override
	public ArrayList<Survey> getSurveyList() throws ParseException {
		ArrayList<Survey> surveyList = new ArrayList<Survey>();
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");

		List<ParseObject> surveys = relation.getQuery().find();
		for(ParseObject survey : surveys ) {
			surveyList.add(new SurveyRemote(survey));
		}
	
		return surveyList;
	}
	
	@Override
	public double getAverage() {
		return mParseObject.getDouble("average");
	}
	
	@Override
	public String getID () {
		return mParseObject.getObjectId();
	}
	
	/**
	 * Find a FoodOpeningPacakge object by its ID
	 * @param Id : ID of object
	 * @return : FoodOpeningPacket object. Null if cannot find any object
	 * @throws ParseException
	 */
	public static FoodOpeningPackage findById( String Id ) throws ParseException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
		return new FoodOpeningPackageRemote(query.get(Id));
	}
	
	@Override
	public void addSurvey( Survey survey ) throws ParseException {
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
	
	/**
	 * List all FoodOpeningPackage object in database
	 * @return list of FoodOpeningPackage in database
	 * @throws ParseException
	 */
	public static ArrayList<FoodOpeningPackage> listAll() throws ParseException {
		ArrayList<FoodOpeningPackage> res = new ArrayList<FoodOpeningPackage>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
		
		List<ParseObject> foodOpeningPackages = query.find();
		for(ParseObject foodOpeningPackage : foodOpeningPackages) {
			FoodOpeningPackage cur = new FoodOpeningPackageRemote(foodOpeningPackage);
			res.add(cur);
		}
		
		return res;
	}
	

	@Override
	public ParseObject getParseObject() {
		return mParseObject;
	}
	
	/**
	 * Save data of FoodOpeningPackage and push to online database
	 * @throws ParseException when cannot save and push the data to online database
	 */
	private void save() throws ParseException {
		mParseObject.save();
	}
}
