package com.example.openpackage.ui;


import com.example.openpackage.controller.UserController;
import com.example.openpackageapplication.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Login Form Activity
 * 
 * Field:
 * - User Name
 * - Password
 * 
 * Button:
 * - Login
 * - Create new account
 * - Forget Password
 * @author Nguyen Tuan Anh
 */
public class LoginFormActivity extends Activity {
	/** The static constant String TAG*/
	private final static String TAG = "LoginFormActivity";

	/* Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login_layout);
	    
	    final EditText username = (EditText)findViewById(R.id.username_login_field);
	    
	    
	    final EditText password = (EditText)findViewById(R.id.password_login_field);
	    
	    
	    //LOGIN BUTTON
		Button login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					UserController userController = new UserController(getApplicationContext());
					final String user = username.getText().toString();
					final String pass = password.getText().toString();
					
					if (!userController.validateLogin(user, pass)){
						displayErrorMessage(user, pass);
					}
				}
			});
			
	    
		//FORGET PASSWORD BUTTON
		Button forgetPass = (Button) findViewById(R.id.forget_password_button);
		forgetPass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//GOTO FORGETPASS ACTIVITY
				Intent forgetPassActivity = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
				startActivity(forgetPassActivity);
			}
		});
		
		//CREATE ACC BUTTON
		Button createAcc = (Button) findViewById(R.id.new_acc_button);
		createAcc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//GOTO FORGETPASS ACTIVITY
				Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(registerActivity);
			}
		});
	}
	
	
	/**
	 * Display error message when unsucessfull login
	 * @param username The username of user
	 * @param password The password of user
	 */
	public void displayErrorMessage(String username, String password){
			Toast.makeText(
					getApplicationContext(),
					"Username or password is not correct",
					Toast.LENGTH_LONG).show();
		
	}
}