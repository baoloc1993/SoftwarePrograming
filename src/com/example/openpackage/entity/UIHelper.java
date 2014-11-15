package com.example.openpackage.entity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * Interface for FacebookUIHelper 
 * @author Huynh Ba Dat
 *
 */
public interface UIHelper {
	/**
	 * Create the activity
	 * @param savedInstanceState the state that has been saved
	 */
	public void onCreate(Bundle savedInstanceState);
	
	/**
	 * Resume activity
	 */
	public void onResume();
	
	/**
	 * Request activity result
	 * @param requestCode The request code for result
	 * @param resultCode The result code send back
	 * @param data The data get back from result
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data);
	
	/**
	 * Pause the activity
	 */
	public void onPause();
	
	/**
	 * Stop the activity
	 */
	public void onStop();
	
	/**
	 * Destroy the activity
	 */
	public void onDestroy();
	
	/**
	 * Open a Dialog 
	 * @param survey The survey
	 * @param foodopeningpackage The food opening package
	 */
	public void openDialog(Survey survey, FoodOpeningPackage foodopeningpackage);
	
	/**
	 * Save the instance state of activity
	 * @param outState The current state
	 */
	public void onSaveInstanceState(Bundle outState);
}

