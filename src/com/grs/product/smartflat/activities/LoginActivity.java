package com.grs.product.smartflat.activities;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.LoginTask;
import com.grs.product.smartflat.asynctasks.SendPushTokenToServerTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerDetails;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Param;
import com.grs.product.smartflat.utils.Utilities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class LoginActivity extends Activity{
	
	private EditText mEditTextUsername, mEditTextPassword;
	private Button mButtonLogin;
	private FlatOwnerDetails mFlatOwnerDetails = new FlatOwnerDetails();
	private RadioGroup mRadioGroupUserType;
	private RadioButton mRadioButtonOwner,mRadioButtonFamilyMember,mRadioButtonTenant;
	private TextView mTextViewCreateAccount, mTextViewForgotPassword;
	private GoogleCloudMessaging gcmObj;
	String regId = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
 
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
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
		mRadioGroupUserType = (RadioGroup) findViewById(R.id.RadioGroupUserType);
		mRadioButtonOwner = (RadioButton) findViewById(R.id.radioButtonOwner);
		mRadioButtonFamilyMember = (RadioButton) findViewById(R.id.radioButtonFamilyMember);
		mRadioButtonTenant = (RadioButton) findViewById(R.id.radioButtonTenant);
		mTextViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);
		mTextViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
	}
	
	private void addListeners(){
		
		mButtonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(validateUiEntries())
				{
					int id = mRadioGroupUserType.getCheckedRadioButtonId();
					if (id== mRadioButtonOwner.getId()) {
						getLoginCall(mRadioButtonOwner.getText().toString());
					}else if(id== mRadioButtonFamilyMember.getId()){
						getLoginCall(mRadioButtonFamilyMember.getText().toString());
					}else{
						getLoginCall(mRadioButtonTenant.getText().toString());
					}
					
				}		
			}
		});
		mRadioGroupUserType.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == mRadioButtonOwner.getId()){
					
				}else if(checkedId == mRadioButtonFamilyMember.getId()){
					
				}else if(checkedId == mRadioButtonTenant.getId()){
					
				}
			}
		});
		
		mTextViewCreateAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		Intent goToRegistration = new Intent(LoginActivity.this,CreateAccoutForActivity.class);
        		startActivity(goToRegistration);
        		//finish();
			}
		});
		
		mTextViewForgotPassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		Intent goToRegistration = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        		startActivity(goToRegistration);
        		finish();	
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
	
	private void getLoginCall(String role){

		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new LoginTask(getApplicationContext(), new LoginTaskCompleteListener(),mEditTextUsername.getText().toString(), mEditTextPassword.getText().toString(),role)
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
					getPushTokenFromServer(mEditTextUsername.getText().toString());
					//goToNextActivity();
					
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
	
	private void getPushTokenFromServer(String flatOwnerCode){

			new AsyncTask<Void, Void, String>() {
				@Override
				protected String doInBackground(Void... params) {
					String msg = "";
					try {
						if (gcmObj == null) {
							gcmObj = GoogleCloudMessaging
									.getInstance(getApplicationContext());
						}
						regId = gcmObj
								.register(Param.GOOGLE_PROJ_ID);
						msg = "Registration ID :" + regId;

					} catch (IOException ex) {
						msg = "Error :" + ex.getMessage();
					}
					return msg;
				}

				@Override
				protected void onPostExecute(String msg) {
					if (!TextUtils.isEmpty(regId)) {
						SmartFlatApplication.saveFlatOwnerPushTokenInSharedPreferences(regId);
						sendPushTokenToServer();
						Log.e("Push Token Success", msg);
					} else {
						Log.e("Push Token Failure", msg);
					}
				}
			}.execute(null, null, null);
	}
	
	private void sendPushTokenToServer(){


		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new SendPushTokenToServerTask(getApplicationContext(), new SendPushTokenToServerTaskListener(),regId )
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(LoginActivity.this,"Error", "Please check your Internet");
		}		
	}
	
	public class SendPushTokenToServerTaskListener implements AsyncTaskCompleteListener<Response> {

		@Override
		public void onStarted() {
		//	CustomProgressDialog.showProgressDialog(LoginActivity.this, "", false);		
		}

		@Override
		public void onTaskComplete(Response result) {
			if (result != null) 
			{
				if (result.getStatus().equalsIgnoreCase("success")) 
				{
					goToNextActivity();
					
				}else{
					Utilities.ShowAlertBox(LoginActivity.this,"Error",result.getMessage());		
				}
			}	
		}

		@Override
		public void onStoped() {
		//	CustomProgressDialog.removeDialog();	
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			Utilities.ShowAlertBox(LoginActivity.this,"Error",e.getMessage());		
		//	CustomProgressDialog.removeDialog();	
		}
		
	}

}
