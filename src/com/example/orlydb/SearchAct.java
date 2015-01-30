package com.example.orlydb;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class SearchAct extends Activity {

	public final String URL = "http://orlydb.com/?q=";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TableLayout tablelayout;
		GetSearchesClass getsearches;
		Search search;
		String result = null;
		ArrayList<Result> resultlist = new ArrayList<Result>();
		int i;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		tablelayout = (TableLayout) findViewById(R.id.TableLayout1);
		Log.d("m4st0r", MainActivity.searchtxt);
		search = new Search(URL, MainActivity.searchtxt);
		getsearches = new GetSearchesClass();
		getsearches.execute(search);
		try {
			result = getsearches.get();
		} catch (Exception e) {
            e.printStackTrace();
        }
		
		
		if (!(result == null || result.isEmpty())) {
			//plocka ut det jag vill ha ur kjellkoden
			 Pattern p = Pattern.compile("<span class=\"timestamp\">([^<]*)</span>");
			 Matcher m = p.matcher(result);
			 while (m.find()) { // Find each match in turn; String can't do this.
				 Result res = new Result();
			     String timestamp = m.group(1);
			     res.timestamp = timestamp;
			     resultlist.add(res);
			 }
			 Pattern p2 = Pattern.compile("<span class=\"section\"><a href=\"[^\"]*\">([^<]*)</a></span>");
			 Matcher m2 = p2.matcher(result);
			 i = 0;
			 while (m2.find()) { // Find each match in turn; String can't do this.
				String section = m2.group(1); // Access a submatch group; String can't do this.
				resultlist.get(i).section = section;
				i++;
			 }
			 Pattern p3 = Pattern.compile("<span class=\"release\">([^<]*)</span>");
			 Matcher m3 = p3.matcher(result);
			 i = 0;
			 while (m3.find()) { // Find each match in turn; String can't do this.
				String release = m3.group(1);
				resultlist.get(i).release = release;
				i++;
			 }
			 
			 Context context = getApplicationContext();
			 for(Result r : resultlist) {
				 TableRow row = new TableRow(context);
				 TextView texttime = new TextView(context);
				 TextView textsection = new TextView(context);
				 TextView textrelease = new TextView(context);
				 
				 if(isPr0n(r.section))
					 continue;
				 
				 texttime.setText(r.timestamp);
				 textsection.setText(r.section);
				 textrelease.setText(r.release);
				 row.addView(texttime);
				 row.addView(textsection);
				 row.addView(textrelease);
				 tablelayout.addView(row);
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
	public boolean isPr0n(String pr0n) {
		if(pr0n.equals("XXX"))
			return true;
		if(pr0n.equals("XXX-IMGSET"))
			return true;
		if(pr0n.equals("IMAGESET"))
			return true;
		
		return false;
	}
}
