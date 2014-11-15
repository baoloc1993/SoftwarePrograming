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
	 * Abstract class og in
	 * @throws ParseException
	 */
	public abstract void logIn() throws ParseException;
	
	/**
	 * Abstract class for log out
	 * @throws ParseException
	 */
	public abstract void logOut() throws ParseException;
	
	/**
	 * Abstract class to get username
	 * @return Returns a username String
	 */
	public abstract String getUsername();
	
	/**
	 * Abstract class to get password
	 * @return Returns a password String 
	 */
	public abstract String getPassword();
	
	/**
	 * Abstract class to get email
	 * @return Returns a email String 
	 */
	public abstract String getEmail();
}
