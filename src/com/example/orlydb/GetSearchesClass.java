package com.example.orlydb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;
import android.util.Log;

public class GetSearchesClass extends AsyncTask<Search, Integer, String> {

	@Override
	protected String doInBackground(Search... params) {
		String result;
		Search search = params[0];

		try {
			result = getSearches(search.URL, search.search);
		} catch (Exception e) {
			return null;
		}
		return result;
	}
	
	String getSearches(String URL, String search) throws ClientProtocolException, IOException {
		String encoded = URLEncoder.encode(search, "UTF-8");
		
		Log.d("kodadurl",encoded);
		
	    HttpClient httpClient = new DefaultHttpClient();
	    HttpContext localContext = new BasicHttpContext();
	    HttpGet httpGet = new HttpGet(URL + encoded);
	    HttpResponse response = httpClient.execute(httpGet, localContext);
	    
	    Log.d("kodadurl", (URL + encoded));
	    
		String result = "";
		
		BufferedReader reader = new BufferedReader(
		new InputStreamReader(response.getEntity().getContent()));

		String line = null;
		while ((line = reader.readLine()) != null) {
			result += line + "\n";
		}
		return result;
	}
}