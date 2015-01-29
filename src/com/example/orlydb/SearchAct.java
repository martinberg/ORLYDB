package com.example.orlydb;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SearchAct extends Activity {

	public final String URL = "http://orlydb.com/q?=";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		GetSearchesClass getsearches;
		Search search;
		String result = null;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		Log.d("m4st0r", MainActivity.searchtxt);
		search = new Search(URL, MainActivity.searchtxt);
		getsearches = new GetSearchesClass();
		getsearches.execute(search);
		try {
			result = getsearches.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (result != null) {
			//Log.d("kjellkod", result);
			
			//plocka ut det jag vill ha ur kjellkoden
			 Pattern p = Pattern.compile("/<span class=\"timestamp\">([^<]*)<\\/span>/");
			 Matcher m = p.matcher(result);
			 while (m.find()) { // Find each match in turn; String can't do this.
			     String timestamp = m.group(1); // Access a submatch group; String can't do this.
			 if (timestamp != null && timestamp.isEmpty()){
			     Log.d("timestamp", timestamp);
			 } else {
				 Log.d("timestamp", "timestamp är null eller tom");
			 }
			 }
			 Pattern p2 = Pattern.compile("/<span class=\"section\"><a href=\"[^\"]*\">([^<]*)<\\/a><\\/span>/");
			 Matcher m2 = p2.matcher(result);
			 while (m2.find()) { // Find each match in turn; String can't do this.
			     @SuppressWarnings("unused")
				String section = m2.group(1); // Access a submatch group; String can't do this.
			 }
			 Pattern p3 = Pattern.compile("/<span class=\"release\">([^<]*)<\\/span>/");
			 Matcher m3 = p3.matcher(result);
			 while (m3.find()) { // Find each match in turn; String can't do this.
			     @SuppressWarnings("unused")
				String release = m3.group(1); // Access a submatch group; String can't do this.
			 }
		} else {
			Toast internet = Toast.makeText(getApplicationContext(), "Du är inte ansluten till internet!", Toast.LENGTH_LONG);
			internet.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
