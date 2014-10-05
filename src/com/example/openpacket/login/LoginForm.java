package com.example.openpacket.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.openpackage.controller.UserController;

public class LoginForm {

	String name;
	String password;
	
	/**
	 * Submit the info when user click Submit button
	 */
	public void submit(){
		
	}
	
	
	/**
	 * Display error message when the login is not successful
	 */
	public void displayErrorMessage(String name, String password){
		if (!UserController.validateLogin(name,password)){
			new FireMissilesDialogFragment.show(); 
		}
			
		
	}
	
	/**
	 * AlertDialog Class
	 */
	public class FireMissilesDialogFragment extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage("Your username or password is not correct")
	               .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   //Go to login page
	                       
	                   }
	               })
	               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // Go to home page
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
	
	
}
