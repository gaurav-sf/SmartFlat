package com.grs.product.smartflat.activities;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationStep1Activity extends Activity {

	EditText mEditTextName, mEditTextDOB, mEditTextAge, mEditTextContactNo, mEditTextNoOfFamilyMembers, mEditTextNoOfVehicle, mEditTextFlatNo;
	Spinner mSpinnerBuildingName, mSpinnerFloorNo;
	Button mButtonNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_step1);
		initializeUI();
		createSpinnerData();
	}

	private void initializeUI(){

		mEditTextName = (EditText) findViewById(R.id.editTextName);
		mEditTextDOB = (EditText) findViewById(R.id.editTextDOB);
		mEditTextAge = (EditText) findViewById(R.id.editTextAge);
		mEditTextContactNo = (EditText) findViewById(R.id.editTextContactNo);
		mEditTextNoOfFamilyMembers = (EditText) findViewById(R.id.editTextNoOfFamilyMembers);
		mEditTextNoOfVehicle = (EditText) findViewById(R.id.editTextNoOfVehicle);
		mEditTextFlatNo = (EditText) findViewById(R.id.editTextFlatNo);
		mSpinnerBuildingName = (Spinner) findViewById(R.id.spinnertBuildingName);
		mSpinnerFloorNo = (Spinner) findViewById(R.id.spinnerTextFloorNo);
		mButtonNext = (Button) findViewById(R.id.buttonNext);

	}

	private void createSpinnerData(){
		//Later the values for the spinner will come from the database. 
		//The values which we are going to save after validation of society code


		//Create Adapter and set data to spinner building name
		List<String> listBuilidingName = new ArrayList<String>();
		listBuilidingName.add("A");
		listBuilidingName.add("B");
		listBuilidingName.add("C");
		listBuilidingName.add("D");
		listBuilidingName.add("E");
		listBuilidingName.add("F");
		ArrayAdapter<String> adapterBuildingName = new ArrayAdapter<String>
		(this, android.R.layout.simple_dropdown_item_1line, listBuilidingName);
		adapterBuildingName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		mSpinnerBuildingName.setAdapter(adapterBuildingName);
		
		//Create Adapter and set data to spinner floor no
		List<String> listFloorNo = new ArrayList<String>();
		listFloorNo.add("1");
		listFloorNo.add("2");
		listFloorNo.add("3");
		listFloorNo.add("4");
		listFloorNo.add("5");
		listFloorNo.add("6");
		listFloorNo.add("7");
		ArrayAdapter<String> adapterFloorNo = new ArrayAdapter<String>
		(this, android.R.layout.simple_dropdown_item_1line, listFloorNo);
		adapterFloorNo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		mSpinnerBuildingName.setAdapter(adapterFloorNo);		
	}

}
