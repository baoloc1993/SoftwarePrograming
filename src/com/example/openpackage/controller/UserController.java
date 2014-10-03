package com.example.openpackage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.content.Context;

import com.example.openpackage.entity.Customer;
import com.example.openpackage.entity.Survey;
import com.example.openpackage.entity.User;
import com.parse.ParseException;

public class UserController {
	
	Context mContext;
	
	public UserController (Context mContext){
		this.mContext = mContext;
	}
	
	public boolean validateLogin( String username, String password ) {
		ArrayList<Customer> customers;
		try {
			customers = Customer.listAll();
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		for(Customer customer : customers) {
			if ( customer.getUsername().equals(username) && customer.getPassword().equals(password) ) {
				try {
					customer.logIn();
					//Intent intent = new Intent( mContext, MainUI.class);
					//intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					//mContext.startActivity(intent);
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
				
				return true;
			}
		}
		return false;
	}
	
	public String validateRegister( String username, String password, String email, int age, boolean gender ) {
		ArrayList<Customer> customers;
		
		try {
			customers = Customer.listAll();
		} catch (ParseException e) {
			e.printStackTrace();
			return "There is some error with internet connection.";
		}
		
		if ( username.isEmpty() || password.isEmpty() || email.isEmpty() ) return "You need to fill all information.";
		if (!isValidEmailAddress(email)) return "Your email is invalid.";
		for(Customer customer : customers) {
			if ( customer.getUsername().equals(username) || customer.getEmail().equals(email) ) {
				return "Your Username or Email has existed.";
			}
		}
		
		try {
			Customer customer = new Customer( username, password, email, age, gender );
			customer.logIn();
			//Intent intent = new Intent( mContext, MainUI.class);
			//intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			//mContext.startActivity(intent);
		} catch (ParseException e) {
			e.printStackTrace();
			return "There is some error with internet connection.";
		}
		return "OKAY";
	}
	
	public boolean verifyForgetInfo(String email) {
		ArrayList<Customer> customers;
		try {
			customers = Customer.listAll();
			for(Customer customer : customers) {
				if ( customer.getEmail().equals(email) ) {
					sendEmail( customer );
					return true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public ArrayList<Survey> retrievePersonalRecord(Customer customer) {
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
	
	private void sendEmail( Customer customer ) {
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "<p>Here is your username and password. Thank you for use our Application.</p>"
        		+ "<ul>"
        		+ "<li>Username: " + customer.getUsername() + "</li>"
        		+ "<li>Password: " + customer.getPassword() + "</li>"
        		+ "</ul>";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("tuananh3668@gmail.com", "Nguyen Tuan Anh"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(customer.getEmail(), "Mr " + customer.getUsername()));
            msg.setSubject("Get Username and Password");
            msg.setContent(msgBody, "text/html; charset=utf-8");
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
