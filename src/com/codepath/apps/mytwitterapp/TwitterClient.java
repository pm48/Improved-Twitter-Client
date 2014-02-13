package com.codepath.apps.mytwitterapp;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "1UEIXST02lehJw7bRzzXA";       // Change this
    public static final String REST_CONSUMER_SECRET = "JjJYcSjkRzbFH5zTElBkMv72oL9lxzCI03k8IJ6TjM"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://codepathtweets"; // Change this (here and in manifest)
    public static int callsToGetHomeTimeline = 0;
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
   /* public void getInterestingnessList(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("format", "json");
        client.get(apiUrl, params, handler);
    }*/
    
    public void getHomeTimeline(AsyncHttpResponseHandler handler, int count, long maxId)
    {
    	RequestParams params =null;
       String url = getApiUrl("statuses/home_timeline.json"); 
       /*if(callsToGetHomeTimeline==0)
       {
        	   params = new RequestParams("count",count);
       } 
       else
       {
    	   params = new RequestParams("count",count,"max_id",maxId);   
       }
        		   client.get(url, params,handler);
        		   callsToGetHomeTimeline++;
        }*/
       client.get(url, params,handler);
    }
   
       
   
    public void getMentions(AsyncHttpResponseHandler handler)
    {
    	String url = getApiUrl("statuses/mentions_timeline.json"); 
    	 client.get(url, null,handler);
    }
    
    public void getUserTimeline(AsyncHttpResponseHandler handler)
    {
    	String url = getApiUrl("statuses/user_timeline.json"); 
    	 client.get(url, null,handler);
    }
    
    
    public void getMyInfo(AsyncHttpResponseHandler handler)
    {
    	String url = getApiUrl("account/verify_credentials.json"); 
    	 client.get(url, null,handler);
    }
    public void postTweet(AsyncHttpResponseHandler handler,String status)
    {
       String url = getApiUrl("statuses/update.json"); 
       RequestParams params = new RequestParams("status",status);
    		   client.post(url, params,handler);
    } 
}