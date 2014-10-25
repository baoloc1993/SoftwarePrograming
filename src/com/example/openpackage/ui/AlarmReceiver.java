package com.example.openpackage.ui;

import com.example.openpackageapplication.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{
	final int NotificationID = 1;
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		        String desc = intent.getStringExtra("description");
		        String name = intent.getStringExtra("name"); 
		        Notification n = new Notification(R.drawable.ic_launcher, "Reminder to set up data connection plan", 
		                                            System.currentTimeMillis());
		        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);
		         
		        n.setLatestEventInfo(context, name, desc, pi);
		        // TODO check user preferences
		        //n.defaults |= Notification.DEFAULT_VIBRATE;
		        //n.sound = Uri.parse(RemindMe.getRingtone());
		      	//n.defaults |= Notification.DEFAULT_SOUND;       
		        //n.flags |= Notification.FLAG_AUTO_CANCEL;       
		         
		        NotificationManager nm = (NotificationManager) 
		                                    context.getSystemService(Context.NOTIFICATION_SERVICE);
		        nm.notify(NotificationID, n);

	}

}
