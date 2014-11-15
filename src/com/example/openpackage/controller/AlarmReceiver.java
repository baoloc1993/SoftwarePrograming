package com.example.openpackage.controller;

import com.example.openpackageapplication.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * derived from base class BroadcastReceiver to receive and handle code from sender 
 * @author Tran Phuong Thao
 */
public class AlarmReceiver extends BroadcastReceiver{
	/** The contanst integer NotificationID match ID of notification*/
	final int NotificationID = 1;
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		// get information from sender and 
		        String desc = intent.getStringExtra("description");
		        String name = intent.getStringExtra("name"); 
		//set up notification        
		        Notification n = new Notification(R.drawable.ic_launcher, "Reminder to set up data connection plan", 
		                                            System.currentTimeMillis());
		        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);
		         
		        n.setLatestEventInfo(context, name, desc, pi);
		        // TODO check user preferences
		        n.defaults |= Notification.DEFAULT_VIBRATE;
		      	n.defaults |= Notification.DEFAULT_SOUND;       
		        n.flags |= Notification.FLAG_AUTO_CANCEL;       
		         
		        NotificationManager nm = (NotificationManager) 
		                                    context.getSystemService(Context.NOTIFICATION_SERVICE);
		        nm.notify(NotificationID, n);

	}

}
