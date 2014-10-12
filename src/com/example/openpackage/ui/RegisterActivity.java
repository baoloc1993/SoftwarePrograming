package com.example.openpackage.ui;

import com.example.openpackage.controller.UserController;
import com.example.openpackageapplication.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	boolean isMale = false;
	String user;
	String pass;
	String rePass;
	String emailStr;
	String reEmailStr;
	int ageInt;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.register_layout);
	    // TODO Auto-generated method stub
	    
	    final EditText username = (EditText)findViewById(R.id.username_signup_field);
	    final EditText password = (EditText)findViewById(R.id.password_signup_field);
	    final EditText rePassword = (EditText)findViewById(R.id.rewrite_password_signup_field);
	    final EditText email = (EditText)findViewById(R.id.email_signup_field);
	    final EditText reEmail = (EditText)findViewById(R.id.rewrite_email_signup_field);
	    final EditText age = (EditText)findViewById(R.id.age_signup_field);
	    
	   //boolean isMale = true;
	    final RadioGroup gender = (RadioGroup)findViewById(R.id.radioGroup);
	   
	    gender.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Is the button now checked?
			    boolean checked = ((RadioButton) v).isChecked();
			    
			    // Check which radio button was clicked
			    switch(v.getId()) {
			        case R.id.male_signup_radio:
			            if (checked){
			                isMale = true;
			            	break;
			            }
			        case R.id.female_signup_radio:
			            if (checked){
			            	isMale = false;
			            	break;
			            }
			    }
			}
			
		});
	    
	    //REG BUTTON
	    Button register = (Button)findViewById(R.id.signup_button);
	    register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//GET INFORMATION FROM UI
				user = username.getText().toString();
				pass = password.getText().toString();
				rePass = rePassword.getText().toString();
				emailStr = email.getText().toString();
				reEmailStr = reEmail.getText().toString();
				ageInt = Integer.parseInt(age.getText().toString());
				 
				 
				if (isRegisterValid(pass, rePass, emailStr, reEmailStr)){
					UserController userController = new UserController(getApplicationContext());
					String result = userController.validateRegister(user, pass, emailStr, ageInt, isMale);
					Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	private boolean isRegisterValid(String password, String rePassword, String email, String reEmail){
		
		if (password.compareTo(rePassword) != 0){
			Toast.makeText(getApplicationContext(), "Password is not match", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if (email.compareTo(reEmail) != 0){
			Toast.makeText(getApplicationContext(), "Email is not match", Toast.LENGTH_LONG).show();
			return false;
		}
		
		
		return true;
		
	}

}
