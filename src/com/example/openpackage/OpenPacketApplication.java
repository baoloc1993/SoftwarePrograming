package com.example.openpackage;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class OpenPacketApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "8Xufbq7PYSRLcuxLmhDmA6kzFvBKvzbzMnbv9snM", "cU89WWRKmVVjsxfvKlpzu1zM1iIxEpjILzUcuVaM");
	}
}
