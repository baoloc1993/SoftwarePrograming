package com.example.openpackage.ui;

import com.example.openpackage.controller.UserController;
import com.example.openpackageapplication.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFormActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login_layout);
	    
	    EditText username = (EditText)findViewById(R.id.username_login_field);
	    final String user = username.getText().toString();
	    
	    EditText password = (EditText)findViewById(R.id.password_login_field);
	    final String pass = password.getText().toString();
	    
	    //LOGIN BUTTON
		Button login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					UserController userController = new UserController(getApplicationContext());
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
		Button createAcc = (Button) findViewById(R.id.forget_password_button);
		forgetPass.setOnClickListener(new OnClickListener() {
			
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
	 * 
	 */
	
	public void displayErrorMessage(String username, String password){
			Toast.makeText(
					getApplicationContext(),
					"Username or password is not correct",
					Toast.LENGTH_LONG).show();
		
	}
}




