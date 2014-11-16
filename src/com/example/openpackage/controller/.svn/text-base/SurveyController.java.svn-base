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

public class SurveyController {
	
	private final static String TAG  = "SurveyController";
	
	Context mContext;
	UserController mUserController;
	
	public SurveyController(Context mContext) {
		this.mContext = mContext;
		mUserController = new UserController(mContext);
	}
	
	private void Sort(ArrayList<Survey> cur) {
		Collections.sort(cur, new Comparator<Survey>() {

			@Override
			public int compare(Survey lhs, Survey rhs) {
				
				return (int) (rhs.getDate().getTime() - lhs.getDate().getTime());
			}
		});
	}
	
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

	public Survey getById(String id) {
		try {
			return SurveyRemote.findById(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
