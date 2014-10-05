package com.example.openpacket.database;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.openpackage.entity.Customer;
import com.example.openpackage.entity.Survey;
import com.example.openpackage.entity.User;


import com.example.openpacket.ui.MainActivity;
import com.parse.ParseException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SurveyDatabaseHandler extends SQLiteOpenHelper{

	// Database Version
	private static final int DATABASE_VERSION= 1;
	// Database Name
	private static final String DATABASE_NAME = "openPackage";

	// Contacts table name
	private static String TABLE_SURVEYS = "surveys";
	
	//Column name
	private static String KEY_ID = "id";
	private static String KEY_USERNAME = "username";
	private static String KEY_DATETIME = "datetime";
	private static String KEY_COMMENT = "comment";
	private static String KEY_RATE = "rate";
	private static String KEY_TYPE = "type";
	
	
	private static SurveyDatabaseHandler mInstance = null;
	
	//Contructor
	public SurveyDatabaseHandler(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	//CREATING TABLE
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	
		String CREATE_RSS_TABLE = "CREATE TABLE " + TABLE_SURVEYS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " + KEY_USERNAME + " TEXT, "
				+ KEY_DATETIME + " LONG, " + KEY_COMMENT + " TEXT, "
				+ KEY_RATE + " INT, " + KEY_TYPE + " TEXT"
				+ ");";
		db.execSQL(CREATE_RSS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SURVEYS);

		// Create tables again
		onCreate(db);
	}
	
	
	/**
	 * Adding a new survey in users table 
	 * @throws ParseException 
	 * */
	public void addSurvey(Survey survey) throws ParseException {
		
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put(KEY_USERNAME, survey.getUser().getUsername()); 	// username
		values.put(KEY_DATETIME, survey.getDate().getTime()); 		// date
		values.put(KEY_COMMENT, survey.getComment()); 				// comment
		values.put(KEY_RATE, survey.getRate()); 					// rate
		
		//Do not have getType function in Survey Class
		values.put(KEY_TYPE, survey.getParseObject().getClassName()); //Type
		
		db.insert(TABLE_SURVEYS, null, values);

	}

	/**
	 * Reading all users from database
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * */
	public List<Survey> getAllSurveys() throws NumberFormatException, ParseException {
		
		List<Customer> customerList = new ArrayList<Customer>();
		//Log.d("DEBUG", "SQL " + TABLE_RSS);
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SURVEYS + " ORDER BY "
				+ KEY_ID + " DESC";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		//get customer by username
		CustomerDatabaseHandler customerDB = new CustomerDatabaseHandler(MainActivity.activity);
		Customer customer = customerDB.getCustomerByUsername(cursor.getString(1));
		
		//config Date from long integer
		Date date = new Date(Long.parseLong(cursor.getString(2)));
		
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Survey survey = new Survey(
						customer,								//Customer
						date,									//Date 
						cursor.getString(3),								//Comment
						Integer.parseInt(cursor.getString(4)),				//Age
						cursor.getString(5));								//Type
				
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
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * */
	public Customer getCustomerByUsername(String username) throws NumberFormatException, ParseException {
		
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

