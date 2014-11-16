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

/**
 * SurveyListAdapter , control the display of the list of survey
 * Each item of survey will have:
 * 1.  Username of user which created this survey
 * 2. Comment of a survey
 * 3. Rating bar
 * 4. Date when survey is created
 * @author Nguyen Phan Huy
 *
 */
public class SurveyListAdapter extends ArrayAdapter<Survey> {

	private Context mContext;
	private List<Survey> mList;
	private int mType;
	/**
	 * Contructor of class
	 * @param context current context of application
	 * @param objects list of survey
	 * @param type type of object which is rated
	 */
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
		
		/**
		 * Display the time in format : MM minutes ago
		 */
		String converDate = DateUtils.getRelativeTimeSpanString(
				survey.getParseObject().getUpdatedAt().getTime(),
				new Date().getTime(), 
				DateUtils.SECOND_IN_MILLIS).toString();
		
		holder.date.setText(converDate);
		holder.ratingBar.setRating( (float) survey.getRate() );
		holder.comment.setText(survey.getComment());
		
		return convertView;
	}
	
	/**
	 * Class store basic attribute of survey (username, comment. rating bar, date) 
	 * @author Nguyen Phan Huy
	 *
	 */
	private static class ViewHolder {
		TextView username;
		TextView comment;
		RatingBar ratingBar;
		TextView date;
	}
	
	/**
	 * Reset the list of survey
	 * @param objects List of Survey
	 */
	public void refill(List<Survey> objects) {
		mList.clear();
		mList.addAll(objects);
		notifyDataSetChanged();
	}
	
}
