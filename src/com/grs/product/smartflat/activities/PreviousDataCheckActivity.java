package com.grs.product.smartflat.activities;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.activities.LoginActivity.LoginTaskCompleteListener;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.GetFlatOwnerDataTask;
import com.grs.product.smartflat.asynctasks.LoginTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class PreviousDataCheckActivity extends Activity {
	
	private Bundle extras;
	private String accessRole;
	private String userCode;
	private String ownerCode;
	private SmartFlatDBManager dbManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_previous_data_check);
		dbManager = new SmartFlatDBManager();
		extras = getIntent().getExtras();
		accessRole = extras.getString("accessRole").trim();
		userCode = extras.getString("userCode");
		ownerCode = extras.getString("ownerCode");
		checkIfUserExists();
	}
	
	private void checkIfUserExists()
	{
		//First Time Application is getting used
		if(SmartFlatApplication.getUserCodeFromSharedPreferences()==null
				&& SmartFlatApplication.getApplicationAccessRoleFromSharedPreferences()==null){
			//If user is owner fetch data
			dbManager.deleteDataFromAllTables();
			if(accessRole.equals("Owner")){
			getFlatOwnerData();	
			}
			//If user is Family member fetch data
			else if(accessRole.equals("Family Member")){
			getFamilyMemberData();	
			}
			//If user is tenant fetch data
			else{
			getTenantData();	
			}		
		}
		//if login Username and role is same means same user is logging in into system again
		//Move user to dashboard directly
		else if(SmartFlatApplication.getUserCodeFromSharedPreferences().equals(userCode)
				&& SmartFlatApplication.getApplicationAccessRoleFromSharedPreferences().equals(accessRole)){
			gotoDashBoardActivity();
		}
		//If details are changed then check for the access role and according to that fetch data from server
		else{
			dbManager.deleteDataFromAllTables();
			//If user is owner fetch data
			if(accessRole.equals("Owner")){
			getFlatOwnerData();	
			}
			//If user is Family member fetch data
			else if(accessRole.equals("Family Member")){
			getFamilyMemberData();	
			}
			//If user is tenant fetch data
			else{
			getTenantData();	
			}
		}

	}
	
	private void getFlatOwnerData(){
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new GetFlatOwnerDataTask(PreviousDataCheckActivity.this, new GetFlatOwnerDataTaskListener(), userCode, accessRole, ownerCode)
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(PreviousDataCheckActivity.this,"Error", "Please check your Internet");
		}			
	}
	
	private void getFamilyMemberData(){
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new GetFlatOwnerDataTask(PreviousDataCheckActivity.this, new GetFlatOwnerDataTaskListener(), userCode, accessRole, ownerCode)
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(PreviousDataCheckActivity.this,"Error", "Please check your Internet");
		}			
	}
	
	private void getTenantData(){
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
		//	.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(PreviousDataCheckActivity.this,"Error", "Please check your Internet");
		}			
	}
	
	public class GetFlatOwnerDataTaskListener implements AsyncTaskCompleteListener<Response> {

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(PreviousDataCheckActivity.this, "", false);		
		}

		@Override
		public void onTaskComplete(Response result) {
			if (result != null) 
			{
				if (result.getStatus().equalsIgnoreCase("success")) 
				{
					SmartFlatApplication.saveApplicationAccessRoleInSharedPreferences(accessRole);
					SmartFlatApplication.saveUserCodeInSharedPreferences(userCode);
					SmartFlatApplication.saveSocietyCodeInSharedPreferences(result.getMessage());
					SmartFlatApplication.saveFlatOwnerCodeInSharedPreferences(result.getRole());
					gotoDashBoardActivity();
					
				}else{
					
				}
			}	
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();	
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			Utilities.ShowAlertBox(PreviousDataCheckActivity.this,"Error",e.getMessage());		
			CustomProgressDialog.removeDialog();	
		}
		
	}
	
	private void gotoDashBoardActivity(){
		Intent intentDashboard = new Intent(PreviousDataCheckActivity.this,DashBoardActivity.class);
		startActivity(intentDashboard);	
		finish();
	}

}
