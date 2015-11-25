package com.grs.product.smartflat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.text.DateFormat;
import java.util.Date;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.activities.StartActivity.GetSocietyDetailsListener;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.FlatOwnerRegistrationTask;
import com.grs.product.smartflat.asynctasks.GetSocietyDetailsTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

public class RegistrationStep2Activity extends Activity {

	private EditText mEditTextUsername, mEditTextPassword, mEditTextSecurityQue, mEditTextAnswer;
	private Button buttonSubmit;
	private Bundle extras;
	private final String LOG = RegistrationStep2Activity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_step2);
		initializeUI();
		 extras = getIntent().getExtras();
		String username = extras.getString("username");
		mEditTextUsername.setText(username);
		addListeners();
	}

	private void initializeUI()
	{
		mEditTextUsername = (EditText) findViewById(R.id.editTextUsername); 
		mEditTextPassword = (EditText) findViewById(R.id.editTextPassword); 
		mEditTextSecurityQue =  (EditText) findViewById(R.id.editTextSecurityQuestion); 
		mEditTextAnswer =  (EditText) findViewById(R.id.editTextAnswer);
		buttonSubmit = (Button) findViewById(R.id.buttonSubmit);	
	}

	private void addListeners(){
		buttonSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(isValidateUiEntries())
				{					
					registerUserOnServer();
				}
			}
		});
	}

	private boolean isValidateUiEntries()
	{
		if(mEditTextPassword.getText().toString().equals("")){
			mEditTextPassword.setError("Please enter password");
			return false;
		}
		if(mEditTextAnswer.getText().toString().equals("")){
			mEditTextAnswer.setError("Please enter answer");
			return false;
		}

		return true;	
	}

	private FlatOwnerDetails getFlatOwnerDetails()
	{
		FlatOwnerDetails flatOwnerDetails = new FlatOwnerDetails();
		flatOwnerDetails.setmUsername(extras.getString("username"));
		flatOwnerDetails.setmPassword(mEditTextPassword.getText().toString());
		flatOwnerDetails.setmSecurityQuestion(mEditTextSecurityQue.getText().toString());
		flatOwnerDetails.setmAnswer(mEditTextAnswer.getText().toString());
		flatOwnerDetails.setmFlatOwnerName(extras.getString("name"));
		flatOwnerDetails.setmFlatOwnerDOB(extras.getString("dob"));
		flatOwnerDetails.setmFlatOwnerAge(extras.getString("age"));
		flatOwnerDetails.setmFlatOwnerContactNo(extras.getString("contactno"));
		flatOwnerDetails.setmFlatOwnerEmailId(extras.getString("emailid"));
		flatOwnerDetails.setmBuildingName(extras.getString("buildingname"));
		flatOwnerDetails.setmFloorNo(extras.getString("floorno"));
		flatOwnerDetails.setmFlatno(extras.getString("flatno"));
		flatOwnerDetails.setmNoofFamilyMembers(Integer.parseInt(extras.getString("nooffamilymem")));
		flatOwnerDetails.setmNoofVehicles(Integer.parseInt(extras.getString("noofvehicle")));
		flatOwnerDetails.setmSocietyCode(extras.getString("societycode"));
		flatOwnerDetails.setmFlatOwnerCode(extras.getString("username"));
		flatOwnerDetails.setmGender(extras.getString("gender"));
		flatOwnerDetails.setmFlatOwnerCreatedDateTime(Utilities.getCurrentDateTime());
		return flatOwnerDetails;
	}
	
	private void saveFlatOwnerDetailsInDB(FlatOwnerDetails flatOwnerDetails){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		boolean result = objManager.saveFlatOwnerDeatils(flatOwnerDetails);
		if(result){
			Log.e(LOG, "Flat Owner Details Insertion Successful");
		}
	}
	
	private void registerUserOnServer()
	{
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new FlatOwnerRegistrationTask(RegistrationStep2Activity.this, new FlatOwnerRegistrationTaskListener(), getFlatOwnerDetails())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(RegistrationStep2Activity.this,"Error", "Please check your Internet");
		}
	}
	
	public class FlatOwnerRegistrationTaskListener implements AsyncTaskCompleteListener<Response>{

		@Override
		public void onStarted()
		{
			CustomProgressDialog.showProgressDialog(RegistrationStep2Activity.this, "", false);		
		}

		@Override
		public void onTaskComplete(Response result) {
			if(result!=null){
				if(result.getStatus().equals("success")){
					saveFlatOwnerDetailsInDB(getFlatOwnerDetails());
					SmartFlatApplication.saveFlatOwnerCodeInSharedPreferences(mEditTextUsername.getText().toString());
					goToNextActivity();
				}else{
					Utilities.ShowAlertBox(RegistrationStep2Activity.this, "Error", "Some thing went wrong. Please try after some time.");
				}
			}		
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			if(e!=null)
			Utilities.ShowAlertBox(RegistrationStep2Activity.this, "Error", e.getMessage());
			CustomProgressDialog.removeDialog();		
		}
	}
	
	private void goToNextActivity(){
		Intent loginIntent = new Intent(RegistrationStep2Activity.this, LoginActivity.class);
		startActivity(loginIntent);
		finish();
	}

}
