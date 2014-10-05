package com.example.openpackage.entity;

import com.parse.ParseException;

public abstract class User {
	//private String id;
	//private String username;
	//private String password;
	//private String email;
	public abstract void logIn() throws ParseException;
	public abstract void logOut() throws ParseException;
	public abstract String getUsername();
	public abstract String getPassword();
	public abstract String getEmail();
	public abstract boolean isGender();
}
