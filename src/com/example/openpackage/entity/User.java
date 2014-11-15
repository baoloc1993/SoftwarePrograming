package com.example.openpackage.entity;

import com.parse.ParseException;

/**
 * Abstract class for ManufacturerRemote and CustomerRemote
 * @author Nguyen Tuan Anh
 *
 */
public abstract class User {
	//private String id;
	//private String username;
	//private String password;
	//private String email;
	
	/** 
	 * Log in
	 * @throws ParseException
	 */
	public abstract void logIn() throws ParseException;
	
	/**
	 * Log out
	 * @throws ParseException
	 */
	public abstract void logOut() throws ParseException;
	
	/**
	 * Gets username
	 * @return Returns a username String
	 */
	public abstract String getUsername();
	
	/**
	 * Gets password
	 * @return Returns a password String 
	 */
	public abstract String getPassword();
	
	/**
	 * Gets email
	 * @return Returns a email String 
	 */
	public abstract String getEmail();
}
