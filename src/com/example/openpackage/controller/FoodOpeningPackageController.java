package com.example.openpackage.controller;

import java.util.ArrayList;

import android.content.Context;

import com.example.openpackage.entity.FoodOpeningPackage;
import com.parse.ParseException;

public class FoodOpeningPackageController {
	Context mContext;
	public FoodOpeningPackageController(Context mContext) {
		this.mContext = mContext;
	}
	public ArrayList<FoodOpeningPackage> getFoodOpeningPacketList(String type) {
		ArrayList<FoodOpeningPackage> res = new ArrayList<FoodOpeningPackage>();
		try {
			ArrayList<FoodOpeningPackage> all = FoodOpeningPackage.listAll();
			for(FoodOpeningPackage cur : all) {
				if (cur.getType().equals(type)) res.add(cur);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	public FoodOpeningPackage getById(String foodID) {
		try {
			return FoodOpeningPackage.findById(foodID);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
