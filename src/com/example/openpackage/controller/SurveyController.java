package com.example.openpackage.controller;

import java.util.ArrayList;

import android.content.Context;

import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackage.entity.Survey;
import com.parse.ParseException;

public class SurveyController {
	
	Context mContext;
	
	public SurveyController(Context mContext) {
		this.mContext = mContext;
	}
	
	public ArrayList<Survey> getSurveyList(FoodOpeningPackage cur) {
		try {
			return cur.getSurveyList();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
