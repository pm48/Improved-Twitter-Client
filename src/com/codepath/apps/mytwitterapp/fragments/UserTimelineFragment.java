package com.codepath.apps.mytwitterapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.mytwitterapp.MyTwitterApp;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		MyTwitterApp.getRestClient().getUserTimeline(new JsonHttpResponseHandler(){
			

			public void onSuccess(JSONArray jsonTweets)
			{
				// fragment =  (TweetsListFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentTweets);
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				//tweets.addAll(moretweets);
				getAdapter().addAll(tweets);
				
				//fragment.getAdapter().notifyDataSetChanged();
				Log.d("DEBUG",jsonTweets.toString());
			}
			
			public void onFailure(Throwable e, JSONObject error) {
			    // Handle the failure and alert the user to retry
			    Log.e("ERROR", e.toString());
			  }
		});
	}
}
