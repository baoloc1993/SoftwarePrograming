package com.example.openpackage.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/** 
 * The Class AlarmSetter
 * derived from class BroadcastReceiver to set the alarm after turning on device  
 * @author: Tran Phuong Thao
 * */
public class AlarmSetter extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent service = new Intent(context, ReminderService.class);
		service.setAction(ReminderService.CREATE);
		context.startService(service);
	}

}
