package com.example.openpackage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;

import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackage.entity.FoodOpeningPackageRemote;
import com.parse.ParseException;

/**
 * Control data of the FoodOpeningPackage Entity
 * @author Tran Phuong Thao
 *
 */
public class FoodOpeningPackageController {
	
	/**
	 * Current context of Application
	 */
	Context mContext;
	
	/**
	 * Contructor of class
	 * @param mContext  current context of application
	 */
	public FoodOpeningPackageController(Context mContext) {
		this.mContext = mContext;
	}
	
	/**
	 * Get the list of FoodOpeningPacket which are the same type
	 * @param type : Type of FoodOpeningPacket
	 * @return List of FoodOpeningPacket object
	 */
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
	/**
	 * get the FoodOpeningPackage object by its ID
	 * @param foodID : ID of object 
	 * @return FoodOpeningPackage object. null if cannot get any object
	 */
	public FoodOpeningPackage getFoodOpeningPackageById(String foodID) {
		try {
			return FoodOpeningPackageRemote.findById(foodID);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
