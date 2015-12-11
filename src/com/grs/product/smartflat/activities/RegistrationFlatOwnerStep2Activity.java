package com.grs.product.smartflat.activities;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.FlatOwnerRegistrationTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.response.Response;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationFlatOwnerStep2Activity extends Activity {

	private EditText mEditTextUsername, mEditTextPassword, mEditTextAnswer;
	private Spinner  mSpinnerSecurityQue;
	private Button buttonSubmit;
	private Bundle extras;
	private final String LOG = RegistrationFlatOwnerStep2Activity.class.getName();
	
	String[] security_Questions = {
			"What is your nick name?",
			"What is your birth place?",
			"Who is your favorite cricket player?",
			"What is your favorite color",

			};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		/** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
 
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.activity_registration_flat_owner_step2);
		initializeUI();
		 extras = getIntent().getExtras();
		String username = extras.getString("username");
		mEditTextUsername.setText(username);
		createSpinnerData();
		addListeners();
	}

	private void initializeUI()
	{
		mEditTextUsername = (EditText) findViewById(R.id.editTextUsername); 
		mEditTextPassword = (EditText) findViewById(R.id.editTextPassword); 
		mSpinnerSecurityQue =  (Spinner) findViewById(R.id.spinnerSecurityQue); 
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
		flatOwnerDetails.setmSecurityQuestion( mSpinnerSecurityQue.getSelectedItem().toString());
		flatOwnerDetails.setmAnswer(mEditTextAnswer.getText().toString());
		flatOwnerDetails.setmFlatOwnerName(extras.getString("name"));
		flatOwnerDetails.setmFlatOwnerDOB(extras.getString("dob"));
		flatOwnerDetails.setmFlatOwnerAge(extras.getString("age"));
		flatOwnerDetails.setmFlatOwnerContactNo(extras.getString("contactno"));
		flatOwnerDetails.setmFlatOwnerEmailId(extras.getString("emailid"));
		flatOwnerDetails.setmBuildingName(extras.getString("buildingname"));
		flatOwnerDetails.setmFloorNo(extras.getString("floorno"));
		flatOwnerDetails.setmFlatno(extras.getString("flatno"));
		flatOwnerDetails.setmSocietyCode(extras.getString("societycode"));
		flatOwnerDetails.setmFlatOwnerCode(extras.getString("username"));
		flatOwnerDetails.setmGender(extras.getString("gender"));
		flatOwnerDetails.setmFlatOwnerCreatedDateTime(Utilities.getCurrentDateTime());
		return flatOwnerDetails;
	}
	
/*	private void saveFlatOwnerDetailsInDB(FlatOwnerDetails flatOwnerDetails){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		boolean result = objManager.saveFlatOwnerDeatils(flatOwnerDetails);
		if(result){
			Log.e(LOG, "Flat Owner Details Insertion Successful");
		}
	}*/
	
	private void registerUserOnServer()
	{
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new FlatOwnerRegistrationTask(RegistrationFlatOwnerStep2Activity.this, new FlatOwnerRegistrationTaskListener(), getFlatOwnerDetails())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(RegistrationFlatOwnerStep2Activity.this,"Error", "Please check your Internet");
		}
	}
	
	public class FlatOwnerRegistrationTaskListener implements AsyncTaskCompleteListener<Response>{

		@Override
		public void onStarted()
		{
			CustomProgressDialog.showProgressDialog(RegistrationFlatOwnerStep2Activity.this, "", false);		
		}

		@Override
		public void onTaskComplete(Response result) {
			if(result!=null){
				if(result.getStatus().equals("success")){
				//	saveFlatOwnerDetailsInDB(getFlatOwnerDetails());
				//	SmartFlatApplication.saveFlatOwnerCodeInSharedPreferences(mEditTextUsername.getText().toString());
					deleteSocietyDataFromDB();
					goToNextActivity();
				}else{
					Utilities.ShowAlertBox(RegistrationFlatOwnerStep2Activity.this, "Error", "Some thing went wrong. Please try after some time.");
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
			Utilities.ShowAlertBox(RegistrationFlatOwnerStep2Activity.this, "Error", e.getMessage());
			CustomProgressDialog.removeDialog();		
		}
	}
	
	private void goToNextActivity(){
		Intent loginIntent = new Intent(RegistrationFlatOwnerStep2Activity.this, LoginActivity.class);
		startActivity(loginIntent);
		finish();
	}
	private void createSpinnerData(){
		//Later the values for the spinner will come from the database. 
		//The values which we are going to save after validation of society code
		List<String> listSecurityQuestion = new ArrayList<String>();
		for (int i = 0; i < security_Questions.length; i++) {
			listSecurityQuestion.add(security_Questions[i]);
			
		}
		ArrayAdapter<String> securityQuestion = new ArrayAdapter<String>
		(this, android.R.layout.simple_dropdown_item_1line, listSecurityQuestion);
		securityQuestion.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		mSpinnerSecurityQue.setAdapter(securityQuestion);
}
	
	@Override
	public void onBackPressed() {
		deleteSocietyDataFromDB();
		goToNextActivity();	
	}
	
	private void deleteSocietyDataFromDB(){
		SmartFlatDBManager dbManager =  new SmartFlatDBManager();
		dbManager.deleteDataFromSocietyDetails();
	}
}
