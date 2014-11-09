package com.example.openpackage.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.openpackage.entity.FoodOpeningPackageRemote;
import com.example.openpackage.entity.ReminderRemote;
import com.example.openpackageapplication.R;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListFoodPackageAdapter extends ArrayAdapter<FoodOpeningPackageRemote>{

	public ListFoodPackageAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<FoodOpeningPackageRemote> listPackage;
	/*
	 * here we must override the constructor for ArrayAdapter the only variable
	 * we care about now is ArrayList<Item> objects, because it is the list of
	 * objects we want to display.
	 */
	
	public ListFoodPackageAdapter(Context context, int textViewResourceId,
			ArrayList<FoodOpeningPackageRemote> listPackage){
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
		FoodOpeningPackageRemote i = listPackage.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			holder.nameLabel = (TextView) v
					.findViewById(R.id.stat_name);
			holder.averageRate = (TextView) v
					.findViewById(R.id.stat_rate);
			//TextView dateInfo = (TextView) v.findViewById(R.id.date_info_single_reminder);
			
			//ImageView status = (ImageView) v.findViewById(R.id.status_reminder);
			

			// check to see if each individual textview is null.
			// if not, assign some text!
			// Config parameter of each textview and imageview(Resize)
			FoodOpeningPackageRemote cur = listPackage.get(position);
			
			holder.nameLabel.setText((position+1) + ". "+ cur.getTitle());
			holder.averageRate.setText( new DecimalFormat("0.0").format(cur.getAverage()) + "/5.0");
			

			// init example series data
			/*GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
			    new GraphViewData(1, 2.0d)
			    , new GraphViewData(2, 1.5d)
			    , new GraphViewData(3, 2.5d)
			    , new GraphViewData(4, 1.0d)
			});
			 
			GraphView graphView = new BarGraphView(
			    this.getContext() // context
			    , "GraphViewDemo" // heading
			);
			graphView.addSeries(exampleSeries); // data
			 
			LinearLayout layout = (LinearLayout) v.findViewById(R.layout.singleview_statistics_layout);
			layout.addView(graphView);*/
			

		}

		// the view must be returned to our activity
		return v;

	}
	private static class ViewHolder {
		TextView nameLabel;
		TextView averageRate;
	}
}
