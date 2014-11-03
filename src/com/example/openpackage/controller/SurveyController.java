package com.example.openpackage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.openpackage.entity.Customer;
import com.example.openpackage.entity.FacebookUIHelper;
import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackage.entity.Survey;
import com.example.openpackage.entity.UIHelper;
import com.parse.ParseException;

public class SurveyController {
	
	private final static String TAG  = "SurveyController";
	
	Context mContext;
	UserController mUserController;
	
	
	public SurveyController(Context mContext) {
		this.mContext = mContext;
		mUserController = new UserController(mContext);
	}
	
	/**
	 * Sort the list of surrvey according to time (The latest first) 
	 * @param cur : Array of the survey which will be sorted
	 */

	private void Sort(ArrayList<Survey> cur) {
		Collections.sort(cur, new Comparator<Survey>() {

			@Override
			public int compare(Survey lhs, Survey rhs) {
				
				return (int) (rhs.getDate().getTime() - lhs.getDate().getTime());
			}
		});
	}
	
	/**
	 * Get list of survey of a package which is sorted (the latest first)
	 * @param cur : a FoodOpeningPackage object
	 * @return : sorted Array
	 */
	
	public ArrayList<Survey> getSurveyList(FoodOpeningPackage cur) {
		try {
			ArrayList<Survey> res = cur.getSurveyList();
			Sort(res);
			return res;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get list of survey of a customer which is sorted (the latest first) 
	 * @param cur : a Customer bbject	
	 * @return : sorted Array
	 */
	
	
	public ArrayList<Survey> getSurveyList(Customer cur) {
		try {
			ArrayList<Survey> res = cur.getSurveyList();
			Sort(res);
			return res;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get Survey by ID
	 * @param id : ID of survey
	 * @return : Survey object. null if cannot found
	 */
	public Survey getById(String id) {
		try {
			return Survey.findById(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Validate Data of Survey when creating data 
	 * @param cmt : comment of survey 
	 * @param rate : rate of survey (from 1 to 5) 
	 * @param food : object who will be rated
	 * @return : false if rate = 0, comment is empty or object is not in data
	 */
 
	public boolean validateCreateData(String cmt, int rate, FoodOpeningPackage food) {
		cmt = cmt.trim();
		if (rate == 0 || cmt.isEmpty()) 
			return false;
		Customer user = (Customer) mUserController.getCurrentUser();
		try {
			Survey survey = new Survey(user, new Date(), cmt, rate, food);
			user.addSurvey(survey);
			food.addSurvey(survey);
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Get the paticular survey 
	 * @param mFood :Object to be surveyed
	 * @param user : Customer who did survey 
	 * @return
	 */

	public Survey getSurvey(FoodOpeningPackage mFood, Customer user) {
		ArrayList<Survey>surveys = getSurveyList(mFood);
		if (surveys == null) Log.i(TAG, "ArraySurvey is NULL");
		for(Survey survey : surveys)
			try {
				if (survey.getUser().getId().equals(user.getId())) {
					return survey;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return null;
	}
	public UIHelper connectToSocialNetwork(String choice)
	{
		if(choice.equals("Facebook"))
			return new FacebookUIHelper((Activity)mContext,null);
		return null;
	}

}
