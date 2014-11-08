package com.example.openpackage.ui;

import com.example.openpackageapplication.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class ViewSingleStaticticsFragment extends Fragment{
	
	public ViewSingleStaticticsFragment(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("DEBUG", "Inside draw graph");
		View rootView = inflater.inflate(R.layout.graph_layout, container,
				false);
	/*GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
			    new GraphViewData(1, 2.0d)
			    , new GraphViewData(2, 1.5d)
			    , new GraphViewData(3, 2.5d)
			    , new GraphViewData(4, 1.0d)
			});
		BarGraphView graphView = new BarGraphView(
			    getActivity().getApplicationContext()// context
			    , "GraphViewDemo" // heading
			);
			graphView.addSeries(exampleSeries);*/ // data
			 //Log.d("DEBUG",graphView.toString());
			 TextView txt = new TextView(getActivity());
			 txt.setText("Hello");
			RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.graph_layout);
			//layout.addView(graphView);
			Log.d("DEBUG","add View finish");
			layout.addView(txt);
			return rootView;
	}
	
	
}
