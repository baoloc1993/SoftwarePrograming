package com.example.openpackage.entity;

import com.parse.ParseException;

/**
 * Parent class of Manufacturer and Customer class
 * 
 *
 */
public abstract class User {
	
	
	public abstract void logIn() throws ParseException;
	public abstract void logOut() throws ParseException;
	public abstract String getUsername();
	public abstract String getPassword();
	public abstract String getEmail();
}
