package com.example.openpackage.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackageapplication.R;

/**
 * Adapter control the statistic of all FoodOpeningPackage object
 * @author Tran Vu Xuan Nhat
 *
 */
public class ListStatisticFoodPackageAdapter extends ArrayAdapter<FoodOpeningPackage>{

	/**
	 * Contructor of class
	 * @param context Current context of Application
	 * @param resource
	 */
	public ListStatisticFoodPackageAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<FoodOpeningPackage> listPackage;
	
	/**
	 * Contructor of class
	 * @param context current context of application
	 * @param textViewResourceId 
	 * @param listPackage list of FoodOpeningPackage
	 */
	
	public ListStatisticFoodPackageAdapter(Context context, int textViewResourceId,
			ArrayList<FoodOpeningPackage> listPackage){
		super(context, textViewResourceId, listPackage);
		this.listPackage = listPackage;
	}
	
	/*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// assign the view we are converting to a local variable
		View v = convertView;
		ViewHolder holder;
		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater
					.inflate(R.layout.singleview_statistics_layout, null);
			holder = new ViewHolder();
			v.setTag(holder);
	} 
		else {
		holder = (ViewHolder) v.getTag();
	}

		/*
		 * Recall that the variable position is sent in as an argument to this
		 * method. The variable simply refers to the position of the current
		 * object in the list. (The ArrayAdapter iterates through the list we
		 * sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		FoodOpeningPackage i = listPackage.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			holder.nameLabel = (TextView) v
					.findViewById(R.id.stat_name);
			holder.averageRate = (TextView) v
					.findViewById(R.id.stat_rate);
			

			// check to see if each individual textview is null.
			// if not, assign some text!
			// Config parameter of each textview and imageview(Resize)
			FoodOpeningPackage cur = listPackage.get(position);
			
			holder.nameLabel.setText((position+1) + ". "+ cur.getTitle());
			holder.averageRate.setText( new DecimalFormat("0.0").format(cur.getAverage()) + "/5.0");
						

		}

		// the view must be returned to our activity
		return v;

	}
	/**
	 * 
	 * @author Tran Vu Xuan Nhat
	 *
	 */
	private static class ViewHolder {
		TextView nameLabel;
		TextView averageRate;
	}
}
