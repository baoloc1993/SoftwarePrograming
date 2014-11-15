package com.example.openpackage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.openpackage.controller.UserController;
import com.example.openpackageapplication.R;

/**
 * Display forget password activity
 * @author Nguyen Tuan Anh
 *
 */
public class ForgetPasswordActivity extends Activity {

	/** The email of user */
	String emailStr;
	
	/** The username*/
	String username;
	
	/* Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.forget_password_layout);
	    
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    
	    // TODO Auto-generated method stub
	    
	    final EditText email = (EditText) findViewById(R.id.email_forgot_field);
	   
	    
	    Button forgetButton = (Button) findViewById(R.id.send_email_button);
	    forgetButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 emailStr = email.getText().toString();
				 
				UserController userController = new UserController(getApplicationContext());
				if(!userController.verifyForgetInfo(emailStr)){
					Toast.makeText(getApplicationContext(),
							"Email does not exist",
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"Email has been sent. Please check your email!",
							Toast.LENGTH_LONG).show();
					finish();
				}
				
			}
		});
	}

}