package com.grs.product.smartflat.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.drive.internal.GetMetadataRequest;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.FamilyDetails;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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
		 stringJsonData =  extras.getString("jsonData");
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
		(this, android.R.layout.simple_dropdown_item_1line, listSecurityQuestion);
		securityQuestion.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		mSpinnerSecurityQue.setAdapter(securityQuestion);
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
}