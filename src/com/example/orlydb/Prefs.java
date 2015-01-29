package com.example.orlydb;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.CheckBox;

public class Prefs extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//sätter bakgrundsfärgen till svart
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		super.onCreate(savedInstanceState);
		//föråldrad och bör bytas ut
		addPreferencesFromResource(R.xml.prefs);
	}
	public void onCheckboxClicked(View view) {
	    //är checkboxarna ikryssade?
	    boolean checked = ((CheckBox) view).isChecked();
	    
	    //kolla om checkboxen är ikryssad
	    switch(view.getId()) {
	        case R.id.donotwantthis:
	            if (checked){
	            	//ta bort opassande saker
	            }
	            else{
	            	//låt det opassande materialet vara kvar
	            }
	    }
	}
}
