package com.example.openpackage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;

import com.example.openpackage.entity.FoodOpeningPackageRemote;
import com.parse.ParseException;

public class FoodOpeningPackageController {
	Context mContext;
	public FoodOpeningPackageController(Context mContext) {
		this.mContext = mContext;
	}
	public ArrayList<FoodOpeningPackageRemote> getFoodOpeningPacketList(String type) {
		ArrayList<FoodOpeningPackageRemote> res = new ArrayList<FoodOpeningPackageRemote>();
		try {
			ArrayList<FoodOpeningPackageRemote> all = FoodOpeningPackageRemote.listAll();
			for(FoodOpeningPackageRemote cur : all) {
				if (cur.getType().equals(type)) res.add(cur);
			}
			Collections.sort(res,new Comparator<FoodOpeningPackageRemote> () {

				@Override
				public int compare(FoodOpeningPackageRemote lhs,
						FoodOpeningPackageRemote rhs) {
					return (int) ( rhs.getAverage()- lhs.getAverage() );
				}
				
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	public FoodOpeningPackageRemote getById(String foodID) {
		try {
			return FoodOpeningPackageRemote.findById(foodID);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
