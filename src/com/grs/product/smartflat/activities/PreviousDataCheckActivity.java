package com.grs.product.smartflat.activities;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;

import android.app.Activity;
import android.os.Bundle;

public class PreviousDataCheckActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_previous_data_check);
		checkIfUserExists();
	}
	
	private void checkIfUserExists(){
		if(SmartFlatApplication.getFlatOwnerPushTokenFromSharedPreferences().equals(null)||SmartFlatApplication.getFlatOwnerPushTokenFromSharedPreferences()==null){
			
		}
		
	}

}
