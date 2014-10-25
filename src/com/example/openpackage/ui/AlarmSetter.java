package com.example.openpackage.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmSetter extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent service = new Intent(context, ReminderService.class);
		service.setAction(ReminderService.CREATE);
		context.startService(service);
	}

}
