package com.example.openpackage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import android.content.Context;
import android.util.Log;

import com.example.openpackage.entity.CustomerRemote;
import com.example.openpackage.entity.FoodOpeningPackageRemote;
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
	
	private void Sort(ArrayList<SurveyRemote> cur) {
		Collections.sort(cur, new Comparator<SurveyRemote>() {

			@Override
			public int compare(SurveyRemote lhs, SurveyRemote rhs) {
				
				return (int) (rhs.getDate().getTime() - lhs.getDate().getTime());
			}
		});
	}
	
	public ArrayList<SurveyRemote> getSurveyList(FoodOpeningPackageRemote cur) {
		try {
			ArrayList<SurveyRemote> res = cur.getSurveyList();
			Sort(res);
			return res;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<SurveyRemote> getSurveyList(CustomerRemote cur) {
		try {
			ArrayList<SurveyRemote> res = cur.getSurveyList();
			Sort(res);
			return res;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SurveyRemote getById(String id) {
		try {
			return SurveyRemote.findById(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validateCreateData(String cmt, int rate, FoodOpeningPackageRemote food) {
		cmt = cmt.trim();
		if (rate == 0 || cmt.isEmpty()) 
			return false;
		CustomerRemote user = (CustomerRemote) mUserController.getCurrentUser();
		try {
			SurveyRemote survey = new SurveyRemote(user, new Date(), cmt, rate, food);
			user.addSurvey(survey);
			food.addSurvey(survey);
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public SurveyRemote getSurvey(FoodOpeningPackageRemote mFood, CustomerRemote user) {
		ArrayList<SurveyRemote>surveys = getSurveyList(mFood);
		if (surveys == null) Log.i(TAG, "ArraySurvey is NULL");
		for(SurveyRemote survey : surveys)
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
