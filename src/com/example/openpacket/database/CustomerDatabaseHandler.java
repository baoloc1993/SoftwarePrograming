package com.example.openpacket.database;


import java.util.ArrayList;
import java.util.List;

import com.example.openpackage.entity.Customer;
import com.example.openpackage.entity.User;

import ngo.vnexpress.reader.RSS.WebSite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerDatabaseHandler extends SQLiteOpenHelper{

	// Database Version
	private static final int DATABASE_VERSION= 1;
	// Database Name
	private static final String DATABASE_NAME = "openPackage";

	// Contacts table name
	private static String TABLE_CUSTOMERS = "customers";
	
	//Column name
	private static String KEY_ID = "id";
	private static String KEY_USERNAME = "username";
	private static String KEY_PASSWORD = "password";
	private static String KEY_EMAIL = "email";
	private static String KEY_AGE = "age";
	private static String KEY_GENDER = "gender";
	
	
	private static CustomerDatabaseHandler mInstance = null;
	
	//Contructor
	public CustomerDatabaseHandler(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	//CREATING TABLE
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_RSS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY, " + KEY_USERNAME + " TEXT, "
				+ KEY_PASSWORD + " TEXT, " + KEY_EMAIL + " TEXT, "
				+ KEY_AGE + " INT, " + KEY_GENDER + " BOOL"
				+ ");";
		db.execSQL(CREATE_RSS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);

		// Create tables again
		onCreate(db);
	}
	
	
	/**
	 * Adding a new users in users table 
	 * */
	public void addSite(Customer user) {
		
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put(KEY_USERNAME, user.getUsername()); // username
		values.put(KEY_PASSWORD, user.getPassword()); // password
		values.put(KEY_EMAIL, user.getEmail()); // email
		values.put(KEY_GENDER, user.isGender()); // gender
		
		db.insert(TABLE_CUSTOMERS, null, values);

	}

	/**
	 * Reading all users from database
	 * */
	public List<Customer> getAllSitesByID() {
		
		List<Customer> customerList = new ArrayList<Customer>();
		//Log.d("DEBUG", "SQL " + TABLE_RSS);
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMERS + " ORDER BY "
				+ KEY_ID + " DESC";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Customer customer = new Customer(
						cursor.getString(1),								//Username
						cursor.getString(2),								//Password
						cursor.getString(3),								//Email
						Integer.parseInt(cursor.getString(4)),				//Age
						Boolean.parseBoolean(cursor.getString(5)));			//Gender
				
				// Adding user to list

				customerList.add(customer);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();

		// return contact list

		return customerList;
	}
	
	/**
	 * Updating a single row will be identified by username
	 * */
	public int updateSite(Customer customer) {
		
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, customer.getUsername());
		values.put(KEY_PASSWORD, customer.getPassword());
		values.put(KEY_AGE, customer.getAge());
		values.put(KEY_GENDER, customer.isGender());
		values.put(KEY_EMAIL, customer.getEmail());

		// updating row return
		int update = db.update(TABLE_CUSTOMERS, values, KEY_USERNAME + " = ?",
				new String[] { String.valueOf(customer.getUsername()) });
		db.close();
		return update;

	}

	/**
	 * Reading a row (customer) row is identified by username
	 * */
	public Customer getCustomerByUsername(String username) {
		
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CUSTOMERS, new String[] { KEY_ID, KEY_USERNAME,
				KEY_PASSWORD, KEY_EMAIL, KEY_AGE, KEY_GENDER },
				KEY_USERNAME + "=?", new String[] { username }, null, null,
				null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}

		Customer customer = new Customer(
				cursor.getString(1), 
				cursor.getString(2),
				cursor.getString(3), 
				Integer.parseInt(cursor.getString(4)), 
				Boolean.parseBoolean(cursor.getString(5)));
		
		cursor.close();
		db.close();
		return customer;
	}

}

