package com.grs.product.smartflat.activities;

import java.util.ArrayList;
import java.util.List;

import android.R.layout;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.utils.Utilities;

public class RegistrationStep1Activity extends Activity {

	private EditText mEditTextName, mEditTextDOB, mEditTextAge, mEditTextContactNo, mEditTextEmailId, mEditTextNoOfFamilyMembers, mEditTextNoOfVehicle, mEditTextFlatNo;
	private Spinner mSpinnerBuildingName, mSpinnerFloorNo;
	private Button mButtonNext;
	private RadioButton mRadioButtonMale, mRadioButtonFemale;
	private RadioGroup mRadioGroupGender;
	private SocietyDetails mSocietyDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_step1);
		//Utilities.addCustomActionBar(this);
		initializeUI();
		mSocietyDetails = Utilities.getSocietyDetails();
		createSpinnerData();
		addListeners();
	}
	
	/*private void addCustomActionBar(){
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater inflater = LayoutInflater.from(this);
		View mCustomView = inflater.inflate(R.layout.actionbar, null);
		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayShowCustomEnabled(true);
	}*/

	private void initializeUI(){

		mEditTextName = (EditText) findViewById(R.id.editTextName);
		mEditTextDOB = (EditText) findViewById(R.id.editTextDOB);
		mEditTextAge = (EditText) findViewById(R.id.editTextAge);
		mEditTextContactNo = (EditText) findViewById(R.id.editTextContactNo);
		mEditTextEmailId =  (EditText) findViewById(R.id.editTextEmailId);
		mEditTextNoOfFamilyMembers = (EditText) findViewById(R.id.editTextNoOfFamilyMembers);
		mEditTextNoOfVehicle = (EditText) findViewById(R.id.editTextNoOfVehicle);
		mEditTextFlatNo = (EditText) findViewById(R.id.editTextFlatNo);
		mSpinnerBuildingName = (Spinner) findViewById(R.id.spinnertBuildingName);
		mSpinnerFloorNo = (Spinner) findViewById(R.id.spinnerTextFloorNo);
		mButtonNext = (Button) findViewById(R.id.buttonNext);
		mRadioGroupGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
		mRadioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
		mRadioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
	}

	private void createSpinnerData(){
		//Later the values for the spinner will come from the database. 
		//The values which we are going to save after validation of society code
		List<String> listBuilidingName = new ArrayList<String>();
		String[] buildingName = mSocietyDetails.getmBuildingName().split("@");
		for(int i=0; i<buildingName.length;i++){
			listBuilidingName.add(buildingName[i]);	
		}
		
		//Create Adapter and set data to spinner building name
		ArrayAdapter<String> adapterBuildingName = new ArrayAdapter<String>
		(this, android.R.layout.simple_dropdown_item_1line, listBuilidingName);
		adapterBuildingName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		mSpinnerBuildingName.setAdapter(adapterBuildingName);
		
		//Create Adapter and set data to spinner floor no
		List<String> listFloorNo = new ArrayList<String>();
		for(int i =1; i<=mSocietyDetails.getmTotalFloorNumber();i++){
			listFloorNo.add(String.valueOf(i));	
		}
		ArrayAdapter<String> adapterFloorNo = new ArrayAdapter<String>
		(this, android.R.layout.simple_dropdown_item_1line, listFloorNo);
		adapterFloorNo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		mSpinnerFloorNo.setAdapter(adapterFloorNo);		
	}
	
	private void addListeners(){
		
		mButtonNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isValidateUiEntries()){
					String username = createUserName();
					sendDataForward(username);
				}
			}
		});	
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
		if(mEditTextAge.getText().toString().equals(""))
		{
			mEditTextAge.setError("Please enter your Age");
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
		}
		if(mEditTextFlatNo.getText().toString().equals(""))
		{
			mEditTextFlatNo.setError("Please enter flat no");
			return false;
		}
		if(mEditTextNoOfFamilyMembers.getText().toString().equals(""))
		{
			mEditTextNoOfFamilyMembers.setError("Please enter total number of family members");
			return false;
		}
		if(mEditTextNoOfVehicle.getText().toString().equals(""))
		{
			mEditTextNoOfVehicle.setError("Please enter total no of vehicles");
			return false;
		}
		return true;
		
	}
	
	private String createUserName()
	{
		String username = "";
		username = mEditTextName.getText().toString().split(" ")[0]
				+ mSpinnerBuildingName.getSelectedItem().toString()
				+mSpinnerFloorNo.getSelectedItem().toString()
				+"@"
				+mEditTextFlatNo.getText().toString();
		return username;	
	}
	
	private void sendDataForward(String username)
	{
		Intent intentRegistrationStep2 = new Intent(RegistrationStep1Activity.this, RegistrationStep2Activity.class);
		intentRegistrationStep2.putExtra("username", username);
		intentRegistrationStep2.putExtra("name", mEditTextName.getText().toString());
		intentRegistrationStep2.putExtra("dob", mEditTextDOB.getText().toString());
		intentRegistrationStep2.putExtra("age", mEditTextAge.getText().toString());
		intentRegistrationStep2.putExtra("contactno", mEditTextContactNo.getText().toString());
		intentRegistrationStep2.putExtra("emailid", mEditTextEmailId.getText().toString());
		intentRegistrationStep2.putExtra("buildingname", mSpinnerBuildingName.getSelectedItem().toString());
		intentRegistrationStep2.putExtra("floorno",  mSpinnerFloorNo.getSelectedItem().toString());
		intentRegistrationStep2.putExtra("flatno", mEditTextFlatNo.getText().toString());
		intentRegistrationStep2.putExtra("nooffamilymem", mEditTextNoOfFamilyMembers.getText().toString());
		intentRegistrationStep2.putExtra("noofvehicle", mEditTextNoOfVehicle.getText().toString());
		String gender = "Male";
		int id = mRadioGroupGender.getCheckedRadioButtonId();
		if(id == mRadioButtonFemale.getId()){
			gender = "Female";
		}
		//for now sending this in extra later on will access it from Application file using shared pref
		intentRegistrationStep2.putExtra("societycode", mSocietyDetails.getmSocietyCode());
		intentRegistrationStep2.putExtra("gender", gender);

		startActivity(intentRegistrationStep2);
		finish();
	}

}
