package com.grs.product.smartflat.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.utils.Utilities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class RegistrationFlatOwnerStep1Activity extends Activity {

	private EditText mEditTextName, mEditTextDOB, mEditTextContactNo, mEditTextEmailId, mEditTextFlatNo;
	private Spinner mSpinnerBuildingName, mSpinnerFloorNo;
	private Button mButtonNext;
	private RadioButton mRadioButtonMale, mRadioButtonFemale;
	private RadioGroup mRadioGroupGender;
	private SocietyDetails mSocietyDetails;
	 private Calendar cal;
	 private int currentDay;
	 private int currentMonth;
	 private int currentYear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		/** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
 
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_registration_flat_owner_step1);
		//Utilities.addCustomActionBar(this);
		initializeUI();
		mSocietyDetails = Utilities.getSocietyDetails();
		createSpinnerData();
		addListeners();
	}
	
	
	private void initializeUI(){
		mEditTextName = (EditText) findViewById(R.id.editTextName);
		mEditTextDOB = (EditText) findViewById(R.id.editTextDOB);
		mEditTextContactNo = (EditText) findViewById(R.id.editTextContactNo);
		mEditTextEmailId =  (EditText) findViewById(R.id.editTextEmailId);
		mEditTextFlatNo = (EditText) findViewById(R.id.editTextFlatNo);
		mSpinnerBuildingName = (Spinner) findViewById(R.id.spinnertBuildingName);
		mSpinnerFloorNo = (Spinner) findViewById(R.id.spinnerTextFloorNo);
		mButtonNext = (Button) findViewById(R.id.buttonNext);
		mRadioGroupGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
		mRadioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
		mRadioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
		  cal = Calendar.getInstance();
		  currentDay = cal.get(Calendar.DAY_OF_MONTH);
		  currentMonth = cal.get(Calendar.MONTH);
		  currentYear = cal.get(Calendar.YEAR);
	}

	private void createSpinnerData(){
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
		mEditTextDOB.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(0);
				
			}
		});
		
		mEditTextDOB.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				showDialog(0);
				return true;
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
		Intent intentRegistrationStep2 = new Intent(RegistrationFlatOwnerStep1Activity.this, RegistrationFlatOwnerStep2Activity.class);
		intentRegistrationStep2.putExtra("username", username);
		intentRegistrationStep2.putExtra("name", mEditTextName.getText().toString());
		intentRegistrationStep2.putExtra("dob", mEditTextDOB.getText().toString());
		intentRegistrationStep2.putExtra("contactno", mEditTextContactNo.getText().toString());
		intentRegistrationStep2.putExtra("emailid", mEditTextEmailId.getText().toString());
		intentRegistrationStep2.putExtra("buildingname", mSpinnerBuildingName.getSelectedItem().toString());
		intentRegistrationStep2.putExtra("floorno",  mSpinnerFloorNo.getSelectedItem().toString());
		intentRegistrationStep2.putExtra("flatno", mEditTextFlatNo.getText().toString());
		String gender = "Male";
		int id = mRadioGroupGender.getCheckedRadioButtonId();
		if(id == mRadioButtonFemale.getId()){
			gender = "Female";
		}
		//for now sending this in extra later on will access it from Application file using shared pref
		intentRegistrationStep2.putExtra("societycode", SmartFlatApplication.getSocietyCodeFromSharedPreferences());
		intentRegistrationStep2.putExtra("gender", gender);
		startActivity(intentRegistrationStep2);
		finish();
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
