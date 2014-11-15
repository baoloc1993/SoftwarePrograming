package com.example.openpackage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.openpackage.entity.CustomerRemote;
import com.example.openpackage.entity.CustomerRemote;
import com.example.openpackage.entity.ManufacturerRemote;
import com.example.openpackage.entity.Survey;
import com.example.openpackage.entity.User;
import com.example.openpackage.ui.MainActivity;
import com.example.openpackage.ui.ManufacturerUI;
import com.parse.ParseException;
/**
 * Control the processing of data of User entity
 * @author Nguyen Tuan Anh
 *
 */
public class UserController {
	/**
	 * The constant TAG
	 */
	private final static String TAG = "UserController";
	/**
	 * context that store application environment
	 */
	Context mContext;
	
	/**
	 * Instantiate a new User Controller
	 * @param mContext
	 */
	public UserController (Context mContext){
		this.mContext = mContext;
	}
	
	/**
	 * Gets information about current user of application
	 * @return Returns the object User that stores information about 
	 * current user of application
	 */
	public User getCurrentUser() {
		try {
			User user = CustomerRemote.getCurrentUser();
			if (user!=null) return user;
			user = ManufacturerRemote.getCurrentUser();
			if (user!=null) return user;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets information about current Manufacturer that are using application
	 * @return the object Manufacturer that stores information 
	 * about current Manufacturer that are using application
	 */
	private ManufacturerRemote getManufactureUser() {
		try {
			ArrayList<ManufacturerRemote> mManufacturers = ManufacturerRemote.listAll();
			return mManufacturers.get(0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Validate the login information that user input and login to application
	 * @param username The username user input
	 * @param password The password user input
	 * @return Returns true if user input is valid
	 * 
	 */
	public boolean validateLogin( String username, String password ) {
		ArrayList<User> customers;
		ManufacturerRemote mManufacturer = getManufactureUser();
		try {
			customers = CustomerRemote.listAll();
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		for(User CustomerRemote : customers) {
			if ( CustomerRemote.getUsername().equals(username) && CustomerRemote.getPassword().equals(password) ) {
				try {
					CustomerRemote.logIn();
					Intent intent = new Intent( mContext, MainActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					mContext.startActivity(intent);
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
				
				return true;
			}
		}
		
		if (mManufacturer!=null && mManufacturer.getUsername().equals(username) && mManufacturer.getPassword().equals(password)) {
			try {
				mManufacturer.logIn();
				Intent intent = new Intent( mContext, ManufacturerUI.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				/*
				 * set up alarm after manufacturer log in
				 */
				//ReminderController reminderController = new ReminderController(mContext);
				ReminderController reminderController = new ReminderController(mContext);
				reminderController.firstUpdateAlarmAfterLogIn();
				mContext.startActivity(intent);
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Validate the register information and create new account
	 * validate the register information that user input
	 * @param username The username user input
	 * @param password The password user input
	 * @param email The email user input
	 * @param age The age user input
	 * @param gender The gender user input 
	 * @return Returns the String that show the errors or result of register process
	 */
	public String validateRegister( String username, String password, String email, int age, boolean gender ) {
		ArrayList<User> customers;
		
		try {
			customers = CustomerRemote.listAll();
		} catch (ParseException e) {
			e.printStackTrace();
			return "There is some error with internet connection.";
		}
		
		if ( username.isEmpty() || password.isEmpty() || email.isEmpty() ) return "You need to fill all information.";
		if (!isValidEmailAddress(email)) return "Your email is invalid.";
		for(User CustomerRemote : customers) {
			if ( CustomerRemote.getUsername().equals(username) || CustomerRemote.getEmail().equals(email) ) {
				return "Your Username or Email has existed.";
			}
		}
		
		try {
			User CustomerRemote = new CustomerRemote( username, password, email, age, gender );
			CustomerRemote.logIn();
			Intent intent = new Intent( mContext, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			mContext.startActivity(intent);
		} catch (ParseException e) {
			e.printStackTrace();
			return "There is some error with internet connection.";
		}
		return "OKAY";
	}
	
	/**
	 * Verify the email of user who forget password and request to send email 
	 * @param email The email that will be sent by request of user who forget password
	 * @return Returns true if email is verified
	 */
	public boolean verifyForgetInfo(String email) {
		ArrayList<User> customers;
		try {
			customers = CustomerRemote.listAll();
			for(User CustomerRemote : customers) {
				if ( CustomerRemote.getEmail().equals(email) ) {
					sendEmail( CustomerRemote );
					return true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
	 * retrieve the personal information of current customer
	 * @param CustomerRemote the information of current Customer
	 * @return Returns a list of Survey belong to current customer
	 */
	public ArrayList<Survey> retrievePersonalRecord(CustomerRemote CustomerRemote) {
		try {
			return CustomerRemote.getSurveyList();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean logOut(User user) {
		try {
			user.logOut();
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	private void sendEmail( User CustomerRemote ) {

        String msgBody = "<p>Here is your username and password. Thank you for use our Application.</p>"
        		+ "<ul>"
        		+ "<li>Username: " + CustomerRemote.getUsername() + "</li>"
        		+ "<li>Password: " + CustomerRemote.getPassword() + "</li>"
        		+ "</ul>";

        try {
        	Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "false");
            props.put("mail.smtp.ssl.enable", "true");

            Session session = Session.getDefaultInstance(props, 
            	    new javax.mail.Authenticator(){
            	        protected PasswordAuthentication getPasswordAuthentication() {
            	            return new PasswordAuthentication(
            	                "ntuananhhp95@gmail.com", "vietanh123");// Specify the Username and the PassWord
            	        }
            	});
            
            Message msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("ntuananhhp95@gmail.com", "Nguyen Tuan Anh"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(CustomerRemote.getEmail(), "Mr " + CustomerRemote.getUsername()));
            msg.setSubject("Get Username and Password");
            msg.setContent(msgBody, "text/html");
            Transport.send(msg);
        } catch (AddressException e) {
        	e.printStackTrace();
        } catch (MessagingException e) {
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
