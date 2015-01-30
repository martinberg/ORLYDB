package com.example.orlydb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static String searchtxt;
	public static boolean haveConnectedWifi;
	public static boolean haveConnectedMobile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	    //ändrar till svart bakgrundsfärg
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		//Ta bort den fula svarta rutan längst upp. kanske
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_main);
		
	}
	
	private boolean haveNetworkConnection() {
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void searchBtnClick (View view) {
		//felhantering
		
		EditText search = (EditText) findViewById(R.id.editText1);
		searchtxt = search.getText().toString();
		
		if (searchtxt != null && !searchtxt.isEmpty()) {
			Log.d("searchtxt", searchtxt);
			Intent searchActIntent = new Intent(this, SearchAct.class);
			startActivity(searchActIntent);
			
			}
		else {
			Toast searchnull = Toast.makeText(getApplicationContext(), "Skriv något i sökrutan",Toast.LENGTH_LONG);
			searchnull.show();
		}
		
	}

	public boolean onOptionsItemSelected(Menu menu) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		   MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.main, menu);
		    return super.onCreateOptionsMenu(menu);
	    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		switch (item.getItemId()) {
	        case R.id.action_settings:
	            openSettings(null);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void openSettings(View view) {
		Intent i = new Intent(this, Prefs.class);
		startActivity(i);
	}
}
