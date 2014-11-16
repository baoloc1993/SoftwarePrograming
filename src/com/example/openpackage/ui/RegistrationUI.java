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

/**
 * Regsiter Activity
 * 
 * Field:
 * - Username
 * - Password
 * - Confirm Password
 * - Email
 * - Confirm Email
 * - Age
 * - Gender
 * 
 * Button:
 * - Create new account
 * 
 * Note:
 * - Password and Confirm password have to be the same
 * - Email and Confirm Email have to be the same
 * - Valid all field before create account
 * 
 * @author Nguyen Tuan Anh
 * */
public class RegistrationUI extends Activity {
	/** The isFemale states user's gender attribute*/
	boolean isFemale = false;
	
	/** The username attribute*/
	String user;
	
	/** The password attribute*/
	String pass;
	
	/** The confirm password attribute*/
	String rePass;
	
	/** The email of user attribute */
	String emailStr;
	
	/** The confirm email of user attribute*/
	String reEmailStr;
	
	/** The age attribute*/
	int ageInt = 0;
	
	
	/* Called when the activity is first created. */
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
	    

	    final RadioGroup gender = (RadioGroup)findViewById(R.id.radioGroup);
	   
	   
	    
	    
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
				if (age.getText().toString().compareTo("") == 0) ageInt = -1; 
	    		else{
	    			try{
	    				ageInt = Integer.parseInt(age.getText().toString());
	    			}catch (Exception e) {
	    				ageInt = -1; 
	    			}
	    		}
				//final boolean genderChecked;
				 gender.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							// Is the button now checked?
						    boolean genderChecked = ((RadioButton) v).isChecked();
						    
						    // Check which radio button was clicked
						    switch(v.getId()) {
						        case R.id.male_signup_radio:
						            if (genderChecked){
						                isFemale = false;
						            	break;
						            }
						        case R.id.female_signup_radio:
						            if (genderChecked){
						            	isFemale = true;
						            	break;
						            }
						    }
						}
						
					});
				 
				if (isRegisterValid(user, pass, rePass, emailStr, reEmailStr,ageInt)){
					UserController userController = new UserController(getApplicationContext());
					String result = userController.validateNewUserData(user, pass, emailStr, ageInt, isFemale);
					Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	/**
	 * Returns true if register is valid, else return false and display the error
	 * @param user The username
	 * @param password The password
	 * @param rePassword The confirm password
	 * @param email The email
	 * @param reEmail The confirm email
	 * @param ageInt The age
	 * @return Returns true if register is valid
	 */
	private boolean isRegisterValid(String user, String password, String rePassword, String email, String reEmail,int ageInt){
		if (user == null || password == null || email == null){
			Toast.makeText(getApplicationContext(), "You have to fill all the field", Toast.LENGTH_LONG).show();
		}
		//CHeck if any field missing
		if (user.compareTo("") == 0 || password.compareTo("") == 0 || email.compareTo("") == 0 || ageInt == -1){
			Toast.makeText(getApplicationContext(), "You have to fill all the field", Toast.LENGTH_LONG).show();
		}
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
