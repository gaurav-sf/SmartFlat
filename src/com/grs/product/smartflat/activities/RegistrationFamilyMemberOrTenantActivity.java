package com.grs.product.smartflat.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.drive.internal.GetMetadataRequest;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.AddFamilyMemberTask;
import com.grs.product.smartflat.asynctasks.FamilyMemberRegistrationTask;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.fragments.AddFamilyMemberFragment.AddFamilyMemberTaskListener;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistrationFamilyMemberOrTenantActivity extends Activity{
	
	private EditText mEditTextName, mEditTextDOB, mEditTextContactNo, mEditTextEmailId,mEditTextUsername, mEditTextPassword, mEditTextAnswer;
	private RadioButton mRadioButtonMale, mRadioButtonFemale;
	private RadioGroup mRadioGroupGender;
	private Spinner  mSpinnerSecurityQue;
	private Button buttonSubmit;
	private Bundle extras;
	private FamilyDetails mFamilyDetails;
	private String registrationFor, stringJsonData;
	String[] security_Questions = {
			"What is your nick name?",
			"What is your birth place?",
			"Who is your favorite cricket player?",
			"What is your favorite color",
			};
	 private Calendar cal;
	 private int currentDay;
	 private int currentMonth;
	 private int currentYear;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_family_member_tenant);
		getDataFromExtras();
		initialiseUI();
		displayData();
		addListeners();
	}
	
	private void initialiseUI(){
		mEditTextName = (EditText) findViewById(R.id.editTextName);
		mEditTextDOB = (EditText) findViewById(R.id.editTextDOB);
		mEditTextContactNo = (EditText) findViewById(R.id.editTextContactNo);
		mEditTextEmailId =  (EditText) findViewById(R.id.editTextEmailId);
		mRadioGroupGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
		mRadioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
		mRadioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
		mEditTextUsername = (EditText) findViewById(R.id.editTextUsername); 
		mEditTextPassword = (EditText) findViewById(R.id.editTextPassword); 
		mSpinnerSecurityQue =  (Spinner) findViewById(R.id.spinnerSecurityQue); 
		mEditTextAnswer =  (EditText) findViewById(R.id.editTextAnswer);
		buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
		cal = Calendar.getInstance();
		currentDay = cal.get(Calendar.DAY_OF_MONTH);
		currentMonth = cal.get(Calendar.MONTH);
		currentYear = cal.get(Calendar.YEAR);
		createSpinnerData();
	}
	
	private void displayData(){
		if(registrationFor.equalsIgnoreCase("FamilyMember"))
		{
			mEditTextName.setText(mFamilyDetails.getmFamilyMemberName());
			mEditTextDOB.setText(mFamilyDetails.getmFamilyMemberDOB());
			mEditTextContactNo.setText(mFamilyDetails.getmFamilyMemberContactno());
			mEditTextEmailId.setText(mFamilyDetails.getmFamilyMemberEmailId());
			mEditTextUsername.setText(mFamilyDetails.getmFamilyMemberUsername());
			if(mFamilyDetails.getmGender().equalsIgnoreCase("Male")){
				mRadioButtonMale.setChecked(true);
			}else{
				mRadioButtonFemale.setChecked(true);
			}
		}else{
			//Tenant Customization will be here
		}
	}
	
	private void addListeners(){
		buttonSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(isValidateUiEntries()){
					getFamilyMemberDataFromUI();
					registerOnServer();
				}
			}
		});
		
		mEditTextDOB.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(0);
				
			}
		});
			
	}
	
	private void getDataFromExtras(){
		extras = getIntent().getExtras();
		 registrationFor =  extras.getString("registrationFor");
		 stringJsonData =  extras.getString("jsonData").replace("[", "").replace("]", "");
		if(registrationFor.equalsIgnoreCase("FamilyMember")){
			mFamilyDetails = getFamilyDetailsFromJson(stringJsonData);
		}else{
			//Here will be code for tenant
		}
	}
	
	private FamilyDetails getFamilyDetailsFromJson(String jsonData){
		FamilyDetails temp = new FamilyDetails();
		try {
			JSONObject json = new JSONObject(jsonData);
			temp.setmFamilyMemberName(json.getString("Family_Member_Name"));
			temp.setmFamilyMemberDOB(json.getString("Family_Member_DOB"));
			temp.setmFamilyMemberContactno(json.getString("Family_Member_Contact_no"));
			temp.setmFamilyMemberEmailId(json.getString("Family_Member_Email_Id"));
			temp.setmGender(json.getString("Gender"));
			temp.setmFlatOwnerCode(json.getString("Flat_Owner_Code"));
			temp.setmFamilyMemberUsername(json.getString("Family_Member_UserName"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	private void createSpinnerData()
	{
		//Later the values for the spinner will come from the database. 
		//The values which we are going to save after validation of society code
		List<String> listSecurityQuestion = new ArrayList<String>();
		for (int i = 0; i < security_Questions.length; i++) {
			listSecurityQuestion.add(security_Questions[i]);
			
		}
		ArrayAdapter<String> securityQuestion = new ArrayAdapter<String>
		(this, R.layout.spinner_item, listSecurityQuestion);
		securityQuestion.setDropDownViewResource(R.layout.spinner_item);		
		mSpinnerSecurityQue.setAdapter(securityQuestion);
}
	
	private boolean isValidateUiEntries(){
		if(mEditTextName.getText().toString().equals(""))
		{
			mEditTextName.setError("Please enter your full name");
			return false;
		}
		if(mEditTextDOB.getText().toString().equals(""))
		{
			mEditTextDOB.setError("Please enter your DOB");
			return false;
		}
		if(mEditTextContactNo.getText().toString().equals(""))
		{
			mEditTextContactNo.setError("Please enter your contact no");
			return false;
		}
		if(mEditTextEmailId.getText().toString().equals(""))
		{
			mEditTextEmailId.setError("Please enter your email id");
			return false;
		}else{
			if(!Utilities.isValidEmail(mEditTextEmailId.getText().toString())){
				mEditTextEmailId.setError("Please enter valid email id");	
				return false;
			}
		}
		
		if(mEditTextPassword.getText().toString().equals(""))
		{
			mEditTextPassword.setError("Please enter password");
			return false;
		}
		
		if(mEditTextAnswer.getText().toString().equals(""))
		{
			mEditTextAnswer.setError("Please enter answer for security question");
			return false;
		}
		return true;	
	}
	
	private void getFamilyMemberDataFromUI(){
		mFamilyDetails.setmFamilyMemberName(mEditTextName.getText().toString());
		mFamilyDetails.setmFamilyMemberDOB(mEditTextDOB.getText().toString());
		mFamilyDetails.setmFamilyMemberContactno(mEditTextContactNo.getText().toString());
		mFamilyDetails.setmFamilyMemberEmailId(mEditTextEmailId.getText().toString());
		mFamilyDetails.setmFamilyMemberUsername(mEditTextUsername.getText().toString());
		mFamilyDetails.setmPassword(mEditTextPassword.getText().toString());
		mFamilyDetails.setmSecurityQuestion(mSpinnerSecurityQue.getSelectedItem().toString());
		mFamilyDetails.setmAnswer(mEditTextAnswer.getText().toString());
		String gender = "";
		int idgender = mRadioGroupGender.getCheckedRadioButtonId();
		if(idgender == mRadioButtonFemale.getId()){
			gender = "Female";
		}else{
			gender = "Male";	
		}
		mFamilyDetails.setmGender(gender);		
	}
	
	private void registerOnServer(){
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new FamilyMemberRegistrationTask(RegistrationFamilyMemberOrTenantActivity.this, new FamilyMemberRegistrationTaskListener(), mFamilyDetails)
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(RegistrationFamilyMemberOrTenantActivity.this,"Error", "Please check your Internet");
		}		
	}
	
	public class FamilyMemberRegistrationTaskListener implements AsyncTaskCompleteListener<Response>{

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(RegistrationFamilyMemberOrTenantActivity.this, "", false);
		
		}

		@Override
		public void onTaskComplete(Response result) {

			if (result != null) 
			{
				if (result.getStatus().equalsIgnoreCase("success")) 
				{
					showAlertBox(RegistrationFamilyMemberOrTenantActivity.this, "Message", "Registration Successfull");
				}else{
					Utilities.ShowAlertBox(RegistrationFamilyMemberOrTenantActivity.this,"Error","Error Occured please try later");		
				}
			}	
		
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();			
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			CustomProgressDialog.removeDialog();		
			Utilities.ShowAlertBox(RegistrationFamilyMemberOrTenantActivity.this, "Error", "Server error occured. Please try later");
			
		}
		
	}
	
	 @Override
	 @Deprecated
	 protected Dialog onCreateDialog(int id) {
	  return new DatePickerDialog(this, datePickerListener, currentYear, currentMonth, currentDay);
	 }

	 private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
	  public void onDateSet(DatePicker view, int selectedYear,
	    int selectedMonth, int selectedDay) {
		  mEditTextDOB.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
	     + selectedYear);
	  }
	 };
	 
		private void showAlertBox(Context context, String title,
				String message) {
			final Dialog mDialog = new Dialog(context,
					android.R.style.Theme_Translucent_NoTitleBar);
			View layout = LayoutInflater.from(context)
					.inflate(R.layout.alert, null);
			TextView tvAlert = (TextView) layout.findViewById(R.id.tvAlert);
			TextView tvAlertMsg = (TextView) layout.findViewById(R.id.tvAlertMsg);
			tvAlertMsg.setText(message);
			tvAlert.setText(title);
			mDialog.setContentView(layout);
			Button btnOk = (Button) layout.findViewById(R.id.btnOk);
			btnOk.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(final View view) {
					mDialog.dismiss();
					Intent goToLoginScreen = new Intent(RegistrationFamilyMemberOrTenantActivity.this,LoginActivity.class);
					startActivity(goToLoginScreen);
					finish();
				}
			});
			mDialog.show();
		}
		
		@Override
		public void onBackPressed() {
			Intent goToLoginScreen = new Intent(RegistrationFamilyMemberOrTenantActivity.this,LoginActivity.class);
			startActivity(goToLoginScreen);
			finish();
		}
}