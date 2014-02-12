package com.codepath.apps.mytwitterapp;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mytwitterapp.fragments.HomeTimeLineFragment;
import com.codepath.apps.mytwitterapp.fragments.MentionsFragment;
import com.codepath.apps.mytwitterapp.models.Tweet;

public class TimelineActivity extends FragmentActivity implements TabListener {
	private TweetsAdapter lvadapter;
	
	ArrayList<Tweet> tweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
		setupNavigationTabs();
		//lvView = (ListView) findViewById(R.id.lvTweets);
		/*lvView.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void loadMore(int page, int totalItemsCount) {
				//View v = getCurrentFocus();
				MyTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
					
                 fragment =  (TweetsListFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentTweets);
					public void onSuccess(JSONArray jsonTweets)
					{
						ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
						//tweets.addAll(moretweets);
						fragment.getAdapter().addAll(tweets);
						maxId = tweets.get(tweets.size() - 1).getId();
						fragment.getAdapter().notifyDataSetChanged();
					}
					
					public void onFailure(Throwable e, JSONObject error) {
					    // Handle the failure and alert the user to retry
					    Log.e("ERROR", e.toString());
					  }
				},totalItemsCount,maxId);
				
			

			}
		});*/
	
		
	

	}

	private void setupNavigationTabs() {
	ActionBar actionbar = getActionBar();
	actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	actionbar.setDisplayShowTitleEnabled(true);
	Tab tabhome = actionbar.newTab().setText("Home")
			.setTag("HomeTimelineFragment")
			.setIcon(R.drawable.ic_home)
			.setTabListener(this);
	
	Tab tabmentions = actionbar.newTab().setText("Mentions")
			.setTag("MentionsTimelineFragment")
			.setIcon(R.drawable.ic_launcher)
			.setTabListener(this);
	
	actionbar.addTab(tabhome);
	actionbar.addTab(tabmentions);
	actionbar.selectTab(tabhome);
	
	}

	public void onProfileView(MenuItem mi)
	{
		Intent i = new Intent(this,ProfileActivity.class);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.Compose:
			Intent i = new Intent(this,ComposeActivity.class);
	    	 startActivityForResult(i, 1);
			Toast.makeText(this, "ComposeTweet ", Toast.LENGTH_LONG).show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == RESULT_OK) {
		//	if (data.getSerializableExtra("tweetposted") != null) {
          Tweet tweet = (Tweet) data.getSerializableExtra("tweetposted");
          
          Log.d("Tweet is",tweet.getBody());
         // lvadapter.add(tweet);
         // lvadapter.insert(tweet,0);
          tweets.add(0, tweet);
          lvadapter.notifyDataSetChanged();
          }
		//}

}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction fts =manager.beginTransaction();
		
		if(tab.getTag()=="HomeTimelineFragment")
		{
		   fts.replace(R.id.frame_Container,new HomeTimeLineFragment());	
		}
		else
		{
			fts.replace(R.id.frame_Container,new MentionsFragment());	
		}
		
		fts.commit();
		
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
	

	

	
}

