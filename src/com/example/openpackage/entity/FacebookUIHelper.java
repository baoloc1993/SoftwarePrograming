package com.example.openpackage.entity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.UiLifecycleHelper;
import com.facebook.Session.StatusCallback;
import com.facebook.widget.FacebookDialog;

/**
 * FacebookUI Helper for facebook
 * @author Huynh Ba Dat
 *
 */
public class FacebookUIHelper extends UiLifecycleHelper implements UIHelper
{
	/** The activity attribute*/
	Activity activity = null;
	
	/**
	 * Instantiate a new FacebookUIHelper object
	 * @param activity The activity
	 * @param callback The callback status
	 */
	public FacebookUIHelper(Activity activity, StatusCallback callback) {
		super(activity, callback);
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
	          @Override
	          public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
	              Log.e("Activity", String.format("Error: %s", error.toString()));
	          }

	          @Override
	          public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
	              Log.i("Activity", "Success!");
	          }
	      });
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void openDialog(Survey survey, FoodOpeningPackage foodopeningpackage) {
		FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this.activity)
        .setLink(foodopeningpackage.getvideoLink())
        .setDescription("Comment: "+survey.getComment()+" Rate: "+survey.getRate())
        .build();
		super.trackPendingDialogCall(shareDialog.present());
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
	}
	
}