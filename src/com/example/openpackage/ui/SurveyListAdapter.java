package com.example.openpackage.ui;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.openpackage.entity.Survey;
import com.example.openpackage.entity.SurveyRemote;
import com.example.openpackageapplication.R;
import com.parse.ParseException;

public class SurveyListAdapter extends ArrayAdapter<Survey> {

	private Context mContext;
	private List<Survey> mList;
	private int mType;
	
	public SurveyListAdapter(Context context, List<Survey> objects, int type) {
		super(context, R.layout.survey_view, objects);
		mContext = context;
		mList = objects;
		mType = type;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView==null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.survey_view, null);
			holder = new ViewHolder();
			holder.username = (TextView) convertView.findViewById(R.id.username);
			holder.comment = (TextView) convertView.findViewById(R.id.comment);
			holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar1);
			holder.date = (TextView) convertView.findViewById(R.id.date);
			convertView.setTag(holder);
		}
		else holder = (ViewHolder) convertView.getTag();
		
		Survey survey = mList.get(position);
		if (mType==0) {
			try {
				holder.username.setText(survey.getUser().getUsername());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				holder.username.setText(survey.getFoodOpeningPackage().getTitle());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		String converDate = DateUtils.getRelativeTimeSpanString(
				survey.getParseObject().getUpdatedAt().getTime(),
				new Date().getTime(), 
				DateUtils.SECOND_IN_MILLIS).toString();
		
		holder.date.setText(converDate);
		holder.ratingBar.setRating( (float) survey.getRate() );
		holder.comment.setText(survey.getComment());
		
		return convertView;
	}
	
	private static class ViewHolder {
		TextView username;
		TextView comment;
		RatingBar ratingBar;
		TextView date;
	}
	
	public void refill(List<Survey> objects) {
		mList.clear();
		mList.addAll(objects);
		notifyDataSetChanged();
	}
	
}
