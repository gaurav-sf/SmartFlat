package com.grs.product.smartflat.activities;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.GetSocietyDetailsTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends Activity {
	private Button button_continue;
	private EditText edittext_society_code;
	private final String LOG = StartActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		/** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
 
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.activity_start);
		initializeUI();
		addListeners();
	}

	private void initializeUI(){
		button_continue = (Button) findViewById(R.id.button_continue);
		edittext_society_code = (EditText) findViewById(R.id.edittext_society_code);
	}

	private void addListeners(){
		button_continue.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(edittext_society_code.getText().toString().equals("")){
					edittext_society_code.setError("Please enter society code");
				}else{		
					getSocietyDetails(edittext_society_code.getText().toString());
				}
			}
		});
	}

	private void gotoNextActivity(){
			Intent registrationStep1 = new Intent(this, RegistrationStep1Activity.class);
			startActivity(registrationStep1);
			finish();
	}

	private void saveSocietyDetailsInDB(SocietyDetails societyDetails)
	{
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		boolean result = objManager.saveSocietyDetails(societyDetails);
		if(result){
			Log.e(LOG, "Society Details Insertion Successful");
		}	
	}

	private void getSocietyDetails(String societyCode){

		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new GetSocietyDetailsTask(StartActivity.this, new GetSocietyDetailsListener(), societyCode)
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(StartActivity.this,"Error", "Please check your Internet");
		}
	}

	public class GetSocietyDetailsListener implements AsyncTaskCompleteListener<SocietyDetails>{

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(StartActivity.this, "", false);
		}

		@Override
		public void onTaskComplete(SocietyDetails result) {
			if(result != null){
				saveSocietyDetailsInDB(result);
				SmartFlatApplication.saveSocietyCodeInSharedPreferences(result.getmSocietyCode());
				gotoNextActivity();
			}else{
				Utilities.ShowAlertBox(StartActivity.this, "Message", "Null aala na be....");
			}
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			CustomProgressDialog.removeDialog();
			if (e!=null)
			Utilities.ShowAlertBox(StartActivity.this, "Error", e.getMessage());		
		}

	}

}
