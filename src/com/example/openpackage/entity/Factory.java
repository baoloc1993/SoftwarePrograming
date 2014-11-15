package com.example.openpackage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.openpackage.ui.YoutubeFragment;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Class that control the create of Shared Media, Video player and Reminder
 * @author Huynh Ba Dat
 *
 */
public class Factory {
	/**
	 * Create a facebook user interface
	 * @param choice The choice 
	 * @param activity The activity that facebook user interface run on
	 * @return Return a FacebookUIHelper  
	 */
	public static UIHelper createShareMedia(String choice, Activity activity)
	{
		if(choice.equals("Facebook"))
		{
			return new FacebookUIHelper(activity,null);
		}
		return null;
	}
	
	/**
	 * Create a video player fragment
	 * @param choice The choice 
	 * @param URL The url of video
	 * @return
	 */
	public static Fragment createVideoPlayer(String choice, String URL)
	{
		if(choice.endsWith("Youtube"))
		{
			return YoutubeFragment.newInstance(URL);
		}
		return null;
	}
	
	/**
	 * Gets a list of reminder
	 * @param choice The choice
	 * @return Returns an ArrayList of reminder 
	 * @throws ParseException
	 */
	public static ArrayList<Reminder> listAllReminder(String choice) throws ParseException
	{
		ArrayList<Reminder> res = null;
		if(choice.equals("RemoteDB"))
		{
			res = new ArrayList<Reminder>();
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Reminder");
			
				List<ParseObject> reminders = query.find();
				for(ParseObject reminder : reminders) {
					Reminder cur = new ReminderRemote(reminder);
					res.add(cur);
				}
		}
		return res;
	}
	
	/**
	 * Create a reminder object with given information
	 * @param choice The choice 
	 * @param name The name
	 * @param description The description
	 * @param time The item
	 * @param active The activate
	 * @return Returns a reminder object 
	 * @throws ParseException
	 */
	public static Reminder createReminderObject(String choice, String name, String description, Date time, boolean active) throws ParseException
	{
		if(choice.equals("RemoteDB"))
		{
		return new ReminderRemote(name,description,time,active) ;
		}
		return null;
	}
	
	
	
}
