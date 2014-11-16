package com.example.openpackage;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
/**
 * The Application which start at the very first part of the program
 * @author Tran Phuong Thao
 *
 */
public class OpenPacketApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "8Xufbq7PYSRLcuxLmhDmA6kzFvBKvzbzMnbv9snM", "cU89WWRKmVVjsxfvKlpzu1zM1iIxEpjILzUcuVaM");
	}
}
