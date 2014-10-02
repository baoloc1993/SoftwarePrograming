package com.example.openpackage;

import android.app.Application;

import com.parse.Parse;

public class OpenPacketApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "snW30KkVJYtESfXpAsC5Fs3Vs2edMkjCc2aNZj8n", "b6qwtdgC17C6TTRiwuBT4vbOmVehMb4oIQLPHl9D");
	}
}
