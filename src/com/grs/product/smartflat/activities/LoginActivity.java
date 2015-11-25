package com.grs.product.smartflat.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.LoginTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerDetails;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

public class LoginActivity extends Activity{
	
	private EditText mEditTextUsername, mEditTextPassword;
	private Button mButtonLogin;
	private FlatOwnerDetails mFlatOwnerDetails = new FlatOwnerDetails();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getUserLoginDetailsFromDB();
		initializeUI();
		addListeners();
	}
	
	private void initializeUI()
	{
		mEditTextUsername = (EditText) findViewById(R.id.editTextUsername); 
		mEditTextUsername.setText(SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences());
		mEditTextPassword = (EditText) findViewById(R.id.editTextPassword); 
		mButtonLogin = (Button) findViewById(R.id.buttonLogin);	
	}
	
	private void addListeners(){
		
		mButtonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(validateUiEntries())
				{
					getLoginCall();
				}			
			}
		});
	}
	
	private boolean validateUiEntries(){
		if(mEditTextUsername.getText().toString().equals("")){
			mEditTextUsername.setError("Please enter username");
			return false;
		}
		if(mEditTextPassword.getText().toString().equals("")){
			mEditTextPassword.setError("Please enter password");
			return false;
		}
		return true;
			
	}
	
	private void getUserLoginDetailsFromDB(){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getFlatOwnerDetails();
		if(cursor!=null && cursor.getCount()>0){
			mFlatOwnerDetails.setmUsername(cursor.getString(cursor.getColumnIndex(TableFlatOwnerDetails.USERNAME)));
			mFlatOwnerDetails.setmPassword(cursor.getString(cursor.getColumnIndex(TableFlatOwnerDetails.PASSWORD)));		
		}		
	}
	
	private void getLoginCall(){

		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new LoginTask(getApplicationContext(), new LoginTaskCompleteListener(),mEditTextUsername.getText().toString(), mEditTextPassword.getText().toString())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(LoginActivity.this,"Error", "Please check your Internet");
		}	
	}
	
	public class LoginTaskCompleteListener implements AsyncTaskCompleteListener<Response> {

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(LoginActivity.this, "", false);		
		}

		@Override
		public void onTaskComplete(Response result) {
			if (result != null) 
			{
				if (result.getStatus().equalsIgnoreCase("success")) 
				{
					SmartFlatApplication.saveFlatOwnerAccessCodeInSharedPreferences(result.getMessage());
					goToNextActivity();
					
				}else{
					Utilities.ShowAlertBox(LoginActivity.this,"Error",result.getMessage());		
				}
			}	
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();	
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			Utilities.ShowAlertBox(LoginActivity.this,"Error",e.getMessage());		
			CustomProgressDialog.removeDialog();	
		}
		
	}
	
	private void goToNextActivity(){
		Intent intentDashboard = new Intent(LoginActivity.this,DashBoardActivity.class);
		startActivity(intentDashboard);	
		finish();
	}

}
