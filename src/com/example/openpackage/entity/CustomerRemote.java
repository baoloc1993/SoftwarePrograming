package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
/**
 * Store the information of Customer
 * @author Huynh ba Dat
 *
 */
public class CustomerRemote extends User {
	/** The static String CLASSNAME */
	private static String CLASSNAME = "Customer";
	
	/** The ParseObject attribute*/
	private ParseObject mParseObject;
	//private int age;
	//private ArrayList<Survey> surveyList;
	//private boolean gender;  // true : male , false : female
	
	/**
	 * Instantiate a new CustomerRemote Object with given ParseObject
	 * @param mParseObject the ParseObject contains customer's info
	 */
	public CustomerRemote(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	/**
	 * Instantiate a new CustomerRemote object with given username, password
	 * email, age, gender
	 * @param username The username of customer
	 * @param password The password of customer
	 * @param email The email of customer
	 * @param age The age of customer
	 * @param gender the gender of customer
	 * @throws ParseException
	 */
	public CustomerRemote(String username, String password, String email, int age, boolean gender ) throws ParseException {
		mParseObject = new ParseObject(CLASSNAME);
		mParseObject.put("username", username);
		mParseObject.put("password",password);
		mParseObject.put("email",email);
		mParseObject.put("age",age);
		mParseObject.put("gender", gender);
		save();
	}
	
	@Override
	public void logIn() throws ParseException {
		mParseObject.pin();
	}
	
	@Override
	public void logOut() throws ParseException {
		mParseObject.unpin();
	}
	
	/**
	 * Get a CustomerRemote object stored information about current Customer
	 * @return Returns a CustomerRemote object stored information about current Customer
	 * @throws ParseException
	 */
	public static CustomerRemote getCurrentUser() throws ParseException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		query.fromLocalDatastore();
		List<ParseObject> customers = query.find();
		if (customers.isEmpty()) return null;
		return new CustomerRemote(customers.get(0));
	}
	
	/**
	 * Gets an list of All customer
	 * @return Returns an ArrayList of Customer
	 * @throws ParseException
	 */
	public static ArrayList<User> listAll() throws ParseException {
		ArrayList<User> res = new ArrayList<User>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		
		List<ParseObject> customers = query.find();
		for(ParseObject customer : customers) {
			CustomerRemote cur = new CustomerRemote(customer);
			res.add(cur);
		}
		
		return res;
	}
	
	/**
	 * Add a survey done by current customer to his history
	 * @param survey The survey is done by customer
	 * @throws ParseException
	 */
	public void addSurvey( Survey survey ) throws ParseException {
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");
		relation.add(survey.getParseObject());
		save();
	}
	
	/**Gets the age of Customer 
	 * @return Returns the age of Customer*/
	public int getAge() {
		return mParseObject.getInt("age");
	}
	
	/**
	 * Gets the list of survey done by this customer
	 * @return Returns the ArrayList of Survey of Customer
	 * @throws ParseException
	 */
	public ArrayList<Survey> getSurveyList() throws ParseException {
		ArrayList<Survey> surveyList = new ArrayList<Survey>();
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");
		
		List<ParseObject> surveys = relation.getQuery().find();
		for(ParseObject survey : surveys ) {
			surveyList.add(new SurveyRemote(survey));
		}
		
		return surveyList;
	}
	
	/**
	 * Gets the gender of Customer
	 * @return Returns true if Customer is female
	 */
	public boolean getGender() {
		return mParseObject.getBoolean("gender");
	}
	
	/**
	 * Gets the Id of Customer
	 * @return Returns the String ID of Customer
	 */
	public String getId() {
		return mParseObject.getObjectId();
	}
	
	@Override
	public String getUsername() {
		return mParseObject.getString("username");
	}

	@Override
	public String getPassword() {
		return mParseObject.getString("password");
	}

	@Override
	public String getEmail() {
		return mParseObject.getString("email");
	}

	/**
	 * Gets the ParseObject that contains Customer's information 
	 * @return Returns a ParseObject object that contains Customer's information 
	 */
	public ParseObject getParseObject() {
		return this.mParseObject;
	}
	
	/**
	 * Save the information of Customer to Database
	 * @throws ParseException
	 */
	private void save() throws ParseException {
		mParseObject.save();
	}
}
