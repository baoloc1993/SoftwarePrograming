package com.example.openpackage.entity;

import java.util.concurrent.Callable;

import android.content.Intent;
import android.os.Bundle;

public interface UIHelper {
	public void onCreate(Bundle savedInstanceState);
	public void onResume();
	public void onActivityResult(int requestCode, int resultCode, Intent data);
	public void onPause();
	public void onStop();
	public void onDestroy();
	public void openDialog();
	public void onSaveInstanceState(Bundle outState);
}

