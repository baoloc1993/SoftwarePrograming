package com.example.openpackage.ui;

import com.example.openpackage.controller.UserController;
import com.example.openpackageapplication.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends Activity {

	
	String emailStr;
	String username;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.forget_password_layout);
	    // TODO Auto-generated method stub
	    
	    EditText email = (EditText) findViewById(R.id.email_forgot_field);
	    emailStr = email.getText().toString();
	    
//	    EditText user = (EditText)findViewById(R.id.username_forgot_field);
//	    username = user.getText().toString();
	    
	    Button forgetButton = (Button) findViewById(R.id.send_email_button);
	    forgetButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserController userController = new UserController(getApplicationContext());
				if(!userController.verifyForgetInfo(emailStr)){
					Toast.makeText(getApplicationContext(),
							"Email does not exist",
							Toast.LENGTH_LONG);
				}
				
			}
		});
	}

}
