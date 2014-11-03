
package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

public class Customer extends User {
	private static String CLASSNAME = "Customer";
	
	private ParseObject mParseObject;
	//private int age;
	//private ArrayList<Survey> surveyList;
	//private boolean gender;  // true : male , false : female
	
	
	public Customer(ParseObject mParseObject) {
		this.mParseObject = mParseObject;
	}
	
	public Customer(String username, String password, String email, int age, boolean gender ) throws ParseException {
		mParseObject = new ParseObject(CLASSNAME);
		mParseObject.put("username", username);
		mParseObject.put("password",password);
		mParseObject.put("email",email);
		mParseObject.put("age",age);
		mParseObject.put("gender", gender);
		save();
	}
	
	/**
	 * Login function (Inherit from User class)
	 */
	@Override
	public void logIn() throws ParseException {
		mParseObject.pin();
	}
	

	/**
	 * Logout function (Inherit from User class)
	 */
	@Override
	public void logOut() throws ParseException {
		mParseObject.unpin();
	}
	
	/**
	 * Get current User
	 * @return : current customer
	 * @throws ParseException
	 */
	public static Customer getCurrentUser() throws ParseException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		query.fromLocalDatastore();
		List<ParseObject> customers = query.find();
		if (customers.isEmpty()) return null;
		return new Customer(customers.get(0));
	}
	
	/**
	 * Get list  of all customers
	 * @return : list of customers
	 * @throws ParseException
	 */
	public static ArrayList<Customer> listAll() throws ParseException {
		ArrayList<Customer> res = new ArrayList<Customer>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		
		List<ParseObject> customers = query.find();
		for(ParseObject customer : customers) {
			Customer cur = new Customer(customer);
			res.add(cur);
		}
		
		return res;
	}
	
	/**
	 * Match customer with the survey he has done
	 * @param survey : a survey he/she has done 
	 * @throws ParseException
	 */
	public void addSurvey( Survey survey ) throws ParseException {
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");
		relation.add(survey.getParseObject());
		save();
	}
	
	public int getAge() {
		return mParseObject.getInt("age");
	}
	
	/**
	 * Get list of survey that customers has done
	 * @return : list of survey
	 * @throws ParseException
	 */
	public ArrayList<Survey> getSurveyList() throws ParseException {
		ArrayList<Survey> surveyList = new ArrayList<Survey>();
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");
		
		List<ParseObject> surveys = relation.getQuery().find();
		for(ParseObject survey : surveys ) {
			surveyList.add(new Survey(survey));
		}
		
		return surveyList;
	}
	
	public boolean getGender() {
		return mParseObject.getBoolean("gender");
	}
	
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

	
	public ParseObject getParseObject() {
		return this.mParseObject;
	}
	
	private void save() throws ParseException {
		mParseObject.save();
	}
}
