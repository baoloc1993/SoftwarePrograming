package com.example.openpackage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import android.content.Context;
import android.util.Log;

import com.example.openpackage.entity.CustomerRemote;
import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackage.entity.Survey;
import com.example.openpackage.entity.SurveyRemote;
import com.parse.ParseException;

/**
 * Store all function to control Survey
 * @author Nguyen Phan Huy
 *
 */
public class SurveyController {
	
	private final static String TAG  = "SurveyController";
	
	Context mContext;
	UserController mUserController;
	
	/**
	 * Contructor of class
	 * @param mContext current context of application
	 */
	public SurveyController(Context mContext) {
		this.mContext = mContext;
		mUserController = new UserController(mContext);
	}
	
	/**
	 * Sort the list of survey. The oldest first
	 * @param cur ArrayList of Survey
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
	 * Get the list of survey of a FoodPackage. The list is sorted
	 * @param cur The FoodOpeningPackage object want to be get the surveys
	 * @return The list of Surveys
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
	 * Get the list of survey of a Customer. The list is sorted
	 * @param cur The Customer object want to be get the surveys
	 * @return The list of surveys
	 */
	public ArrayList<Survey> getSurveyList(CustomerRemote cur) {
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
	 * Get Survey by its ID
	 * @param id Id of survey 
	 * @return Survey object. Null if id is invalid or cannot get the survey
	 */
	public Survey getById(String id) {
		try {
			return SurveyRemote.findById(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Validate the Survey which is created. Check all the input of user 
	 * @param cmt : Comment of survey
	 * @param rate : Rate of survey (From 1 to 5)
	 * @param food : FoodOpeningPackage object which is rated
	 * @return true if the information are all correct
	 */
	public boolean validateCreateData(String cmt, int rate, FoodOpeningPackage food) {
		cmt = cmt.trim();
		if (rate == 0 || cmt.isEmpty()) 
			return false;
		CustomerRemote user = (CustomerRemote) mUserController.getCurrentUser();
		try {
			Survey survey = new SurveyRemote(user, new Date(), cmt, rate, food);
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
	 * get a survey which is rated by particular user to particular object
	 * @param mFood FoodOpeningPackage object which is rated
	 * @param user Customer object which make the survey
	 * @return the survey. Null if cannot find the suitable survey
	 */
	public Survey getSurvey(FoodOpeningPackage mFood, CustomerRemote user) {
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
	
}
