package com.example.openpackage.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.openpackage.entity.FacebookUIHelper;
import com.example.openpackage.entity.UIHelper;

public class Factory {
	public static UIHelper createShareMedia(String choice, Activity activity)
	{
		if(choice.equals("Facebook"))
		{
			return new FacebookUIHelper(activity,null);
		}
		return null;
	}
	
	public static Fragment createVideoPlayer(String choice, String URL)
	{
		if(choice.endsWith("Youtube"))
		{
			return YoutubeFragment.newInstance(URL);
		}
		return null;
	}
}
