package com.example.openpackage.ui;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class YoutubeFragment extends YouTubePlayerSupportFragment {
	private String currentVideoID = "video_id";
    private YouTubePlayer activePlayer;
    private static String TAG = "YoutubeFragment";
    
    public static YoutubeFragment newInstance(String url) {

    YoutubeFragment YoutubeFragment = new YoutubeFragment();

    Bundle bundle = new Bundle();
    bundle.putString("url", url);

    YoutubeFragment.setArguments(bundle);
    YoutubeFragment.init();
    return YoutubeFragment;
    }

    private void init() {

        initialize(DeveloperKey.DEVELOPER_KEY, new OnInitializedListener() {

			@Override
			public void onInitializationFailure(Provider arg0,
					YouTubeInitializationResult arg1) {
				// TODO Auto-generated method stub
				Log.i(TAG, "Datdeptrai");
			}

			@Override
			public void onInitializationSuccess(Provider provider,
					YouTubePlayer player, boolean wasRestored) {
				// TODO Auto-generated method stub
				
				String [] token = getArguments().getString("url").split("^\\D+=");
				
				 activePlayer = player;
	                activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
	                if (!wasRestored) {
	                	if(token.length == 1)
	                		activePlayer.cueVideo(token[0]);
	                	else
	                		activePlayer.cueVideo(token[1]);
	                }
			}
        });
    }

}
