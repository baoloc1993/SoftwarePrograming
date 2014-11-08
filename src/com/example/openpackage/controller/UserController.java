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
import com.example.openpackage.entity.ManufacturerRemote;
import com.example.openpackage.entity.SurveyRemote;
import com.example.openpackage.entity.User;
import com.example.openpackage.ui.MainActivity;
import com.example.openpackage.ui.ManufacturerUI;
import com.parse.ParseException;

public class UserController {
	private final static String TAG = "UserController";
	Context mContext;
	
	public UserController (Context mContext){
		this.mContext = mContext;
	}
	
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
	
	private ManufacturerRemote getManufactureUser() {
		try {
			ArrayList<ManufacturerRemote> mManufacturers = ManufacturerRemote.listAll();
			return mManufacturers.get(0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean validateLogin( String username, String password ) {
		ArrayList<CustomerRemote> customers;
		ManufacturerRemote mManufacturer = getManufactureUser();
		try {
			customers = CustomerRemote.listAll();
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		for(CustomerRemote customer : customers) {
			if ( customer.getUsername().equals(username) && customer.getPassword().equals(password) ) {
				try {
					Log.i(TAG, "Login Customer");
					customer.logIn();
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
				Log.i(TAG, "Log in Manufacturer");
				mManufacturer.logIn();
				Intent intent = new Intent( mContext, ManufacturerUI.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				mContext.startActivity(intent);
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public String validateRegister( String username, String password, String email, int age, boolean gender ) {
		ArrayList<CustomerRemote> customers;
		
		try {
			customers = CustomerRemote.listAll();
		} catch (ParseException e) {
			e.printStackTrace();
			return "There is some error with internet connection.";
		}
		
		if ( username.isEmpty() || password.isEmpty() || email.isEmpty() ) return "You need to fill all information.";
		if (!isValidEmailAddress(email)) return "Your email is invalid.";
		for(CustomerRemote customer : customers) {
			if ( customer.getUsername().equals(username) || customer.getEmail().equals(email) ) {
				return "Your Username or Email has existed.";
			}
		}
		
		try {
			CustomerRemote customer = new CustomerRemote( username, password, email, age, gender );
			customer.logIn();
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
	
	public boolean verifyForgetInfo(String email) {
		Log.i(TAG, email);
		ArrayList<CustomerRemote> customers;
		try {
			customers = CustomerRemote.listAll();
			for(CustomerRemote customer : customers) {
				if ( customer.getEmail().equals(email) ) {
					sendEmail( customer );
					Log.i(TAG, "find" + customer.getEmail() );
					return true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public ArrayList<SurveyRemote> retrievePersonalRecord(CustomerRemote customer) {
		try {
			return customer.getSurveyList();
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
	
	private void sendEmail( CustomerRemote customer ) {

        String msgBody = "<p>Here is your username and password. Thank you for use our Application.</p>"
        		+ "<ul>"
        		+ "<li>Username: " + customer.getUsername() + "</li>"
        		+ "<li>Password: " + customer.getPassword() + "</li>"
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
                             new InternetAddress(customer.getEmail(), "Mr " + customer.getUsername()));
            msg.setSubject("Get Username and Password");
            msg.setContent(msgBody, "text/html");
            Transport.send(msg);
            Log.i(TAG, "email sented");
        } catch (AddressException e) {
        	e.printStackTrace();
        } catch (MessagingException e) {
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
