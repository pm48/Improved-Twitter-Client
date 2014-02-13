package com.codepath.apps.mytwitterapp;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mytwitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		MyTwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler()
		{
				@Override
				public void onSuccess(int arg0, JSONObject json) {
					// TODO Auto-generated method stub
					User u = User.fromJson(json);
					
					getActionBar().setTitle("@"+u.getScreenName());
					populateProfileHeader(u);;
				}

				private void populateProfileHeader(User u) {
					TextView tvName = (TextView)findViewById(R.id.tvName);
					TextView tvTagLine = (TextView)findViewById(R.id.tvTagLine);
					TextView tvFollowers = (TextView)findViewById(R.id.tvFollowers);
					TextView tvFollowing = (TextView)findViewById(R.id.tvFollowing);
					ImageView ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
					tvName.setText(u.getName());
					tvTagLine.setText(u.getTagLine());
					tvFollowers.setText(u.getFollowersCount());
					tvFollowing.setText(u.getFriendsCount());
					ImageLoader.getInstance().displayImage(u.getProfileImageUrl(), ivProfileImage);
					
				}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
