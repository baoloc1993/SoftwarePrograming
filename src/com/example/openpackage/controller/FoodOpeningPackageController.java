package com.example.openpackage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;

import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackage.entity.FoodOpeningPackageRemote;
import com.parse.ParseException;

public class FoodOpeningPackageController {
	Context mContext;
	public FoodOpeningPackageController(Context mContext) {
		this.mContext = mContext;
	}
	public ArrayList<FoodOpeningPackage> getFoodOpeningPacketList(String type) {
		ArrayList<FoodOpeningPackage> res = new ArrayList<FoodOpeningPackage>();
		try {
			ArrayList<FoodOpeningPackage> all = FoodOpeningPackageRemote.listAll();
			for(FoodOpeningPackage cur : all) {
				if (cur.getType().equals(type)) res.add(cur);
			}
			Collections.sort(res,new Comparator<FoodOpeningPackage> () {

				@Override
				public int compare(FoodOpeningPackage lhs,
						FoodOpeningPackage rhs) {
					return (int) ( rhs.getAverage()- lhs.getAverage() );
				}
				
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	public FoodOpeningPackage getById(String foodID) {
		try {
			return FoodOpeningPackageRemote.findById(foodID);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
