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
	
	public Customer(String username, String password, String email, int age, boolean gender ) {
		mParseObject = new ParseObject(CLASSNAME);
		mParseObject.put("username", username);
		mParseObject.put("password",password);
		mParseObject.put("email",email);
		mParseObject.put("age",age);
		mParseObject.put("gender", gender);
		save();
	}
	
	
	
	public static ArrayList<Customer> listAll() {
		ArrayList<Customer> res = new ArrayList<Customer>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
		try {
			List<ParseObject> customers = query.find();
			for(ParseObject customer : customers) {
				Customer cur = new Customer(customer);
				res.add(cur);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void addSurvey( Survey survey ) {
		ParseRelation<ParseObject> relation = mParseObject.getRelation("surveyList");
		relation.add(survey.getParseObject());
		save();
	}
	
	public int getAge() {
		return mParseObject.getInt("age");
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
	
	public boolean isGender() {
		return mParseObject.getBoolean("gender");
	}

	@Override
	public String getId() {
		return mParseObject.getString("id");
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
	
	private void save() {
		mParseObject.saveInBackground();
	}
}
