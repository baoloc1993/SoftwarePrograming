package com.example.openpackage.ui;

import com.example.openpackageapplication.R;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GraphFragment extends Fragment{
	View rootView;
	Bundle bundle; ;
	public GraphFragment(){
		bundle = ViewSingleStatistic.bundle;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		rootView = inflater.inflate(R.layout.graph_layout, container,
				false);
		
	    double[] rate_list = bundle.getDoubleArray("rateList");
	    //Log.d("rate_list",String.valueOf(rate_list[0]));
	    
	    TextView t1 = (TextView) rootView.findViewById(R.id.graph_title);
	    //t1.setText("aaaa");
	    t1.setText("This bar chart show the statistic for package "+bundle.getString("name")+" type "+bundle.getString("type")+
	    		"\nX-axis is labeled by the rate level. \nY-axis is labeled by number of people that have same rate.");
//	    //t1.setTextColor(Color.WHITE);
	    
	    GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
	    new GraphViewData(0,rate_list[0])
	    , new GraphViewData(1, rate_list[1])
	    , new GraphViewData(2, rate_list[2])
	    , new GraphViewData(3, rate_list[3])
	    , new GraphViewData(4, rate_list[4])
	});
	GraphView graphView = new BarGraphView(
	    getActivity().getApplicationContext()// context
	    , bundle.getString("name") // heading
	);
		
	graphView.addSeries(exampleSeries);
	graphView.getGraphViewStyle().setGridColor(Color.BLACK);
	graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.RED);
	graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLUE);
	graphView.getGraphViewStyle().setNumVerticalLabels(3);
	graphView.setHorizontalLabels(new String[]{"1","2","3","4","5"} );
	
	RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.graph_draw);
	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	params.addRule(RelativeLayout.BELOW, R.id.graph_title);
	layout.setLayoutParams(params);
	layout.addView(graphView);
	
	Button Back = (Button) rootView.findViewById(R.id.graph_exit_button);
	Back.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(getActivity().getApplicationContext(),ManufacturerUI.class);
			startActivity(i);
		}
		
	});
	
	return rootView;
	}
}
