package com.codepath.apps.mytwitterapp.fragments;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.mytwitterapp.R;
import com.codepath.apps.mytwitterapp.TweetsAdapter;
import com.codepath.apps.mytwitterapp.models.Tweet;

public class TweetsListFragment extends Fragment {
	TweetsAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	return	inflater.inflate(R.layout.fragments_tweet, parent, false);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		ArrayList tweets = new ArrayList<Tweet>();
		
		 adapter = new TweetsAdapter(getActivity(), tweets);
		
	//	maxId = tweets.get(tweets.size() - 1).getId();
		ListView lvView = (ListView)getActivity().findViewById(R.id.lvTweets);
		lvView.setAdapter(adapter);
//		Log.d("DEBUG",jsonTweets.toString());
		super.onActivityCreated(savedInstanceState);
	}

	
	public TweetsAdapter getAdapter(){
		return adapter;
	}
}
