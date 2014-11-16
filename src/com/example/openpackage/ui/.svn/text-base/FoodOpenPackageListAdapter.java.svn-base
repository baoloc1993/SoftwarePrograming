package com.example.openpackage.ui;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.openpackage.entity.FoodOpeningPackage;
import com.example.openpackage.entity.FoodOpeningPackageRemote;
import com.example.openpackageapplication.R;

public class FoodOpenPackageListAdapter extends ArrayAdapter<FoodOpeningPackage> {
	private List<FoodOpeningPackage> mList;
	private Context mContext;
	public FoodOpenPackageListAdapter(Context context,List<FoodOpeningPackage> list) {
		super(context, R.layout.foodopenpackagelist_item, list);
		mContext = context;
		mList = list;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.foodopenpackagelist_item, null);
			holder = new ViewHolder();
			holder.nameLabel = (TextView) convertView
					.findViewById(R.id.name);
			holder.averageRate = (TextView) convertView
					.findViewById(R.id.rate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		FoodOpeningPackage cur = mList.get(position);
		
		holder.nameLabel.setText((position+1) + ". "+ cur.getTitle());
		holder.averageRate.setText( new DecimalFormat("0.0").format(cur.getAverage()) + "/5.0");
		
		return convertView;
	}
	
	private static class ViewHolder {
		TextView nameLabel;
		TextView averageRate;
	}
	
	public void refill( List<FoodOpeningPackage> list ) {
		mList.clear();
		mList.addAll(list);
		notifyDataSetChanged();
	}
}
