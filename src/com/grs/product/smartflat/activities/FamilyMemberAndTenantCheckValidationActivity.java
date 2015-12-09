package com.grs.product.smartflat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.activities.LoginActivity.LoginTaskCompleteListener;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.CheckForValidFamilyMemberOrTenantTask;
import com.grs.product.smartflat.asynctasks.LoginTask;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

public class FamilyMemberAndTenantCheckValidationActivity extends Activity{

	private EditText mEditTextFlatOwnerCode, mEditTextUsername;
	private Button mButtonNext;
	private Bundle extras;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_member_and_tenant_check_validation);
		initialiseUI();
		extras = getIntent().getExtras();
		addListeners();
	}
	
	private void initialiseUI(){
		 
		mEditTextFlatOwnerCode = (EditText) findViewById(R.id.editTextFlatOwnerCode);
		mEditTextUsername = (EditText) findViewById(R.id.editTextUsername);
		mButtonNext = (Button) findViewById(R.id.buttonNext);
	}
	
	private void addListeners(){
		mButtonNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validateUiEntries()){
					checkForUser();
				}
			}
		});
		
	}
	
	private boolean validateUiEntries(){
		if(mEditTextFlatOwnerCode.getText().toString().equals("")){
			mEditTextFlatOwnerCode.setError("Please enter flat owner code");
			return false;
		}
		if(mEditTextUsername.getText().toString().equals("")){
			mEditTextUsername.setError("Please enter username");
			return false;
		}
		return true;
			
	}
	
	private void checkForUser(){
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new CheckForValidFamilyMemberOrTenantTask(getApplicationContext(), new CheckForValidFamilyMemberOrTenantTaskListener(), mEditTextFlatOwnerCode.getText().toString(), mEditTextUsername.getText().toString(), extras.getString("registrationFor"))
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(FamilyMemberAndTenantCheckValidationActivity.this,"Error", "Please check your Internet");
		}		
	}
	
	public class CheckForValidFamilyMemberOrTenantTaskListener implements AsyncTaskCompleteListener<Response> {

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(FamilyMemberAndTenantCheckValidationActivity.this, "", false);		
		}

		@Override
		public void onTaskComplete(Response result) {
			if (result != null) 
			{
				if (result.getStatus().equalsIgnoreCase("success")) 
				{
					
				}else{
					Utilities.ShowAlertBox(FamilyMemberAndTenantCheckValidationActivity.this,"Error",result.getMessage());		
				}
			}	
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();	
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			Utilities.ShowAlertBox(FamilyMemberAndTenantCheckValidationActivity.this,"Error",e.getMessage());		
			CustomProgressDialog.removeDialog();	
		}
		
	}
	
	@Override
	public void onBackPressed() {
		Intent goToPrevScreen = new Intent(FamilyMemberAndTenantCheckValidationActivity.this,CreateAccoutForActivity.class);
		startActivity(goToPrevScreen);
		finish();
	}
}
