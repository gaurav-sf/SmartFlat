package com.grs.product.smartflat.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.utils.Utilities;

public class RegistrationStep2Activity extends Activity {

	private EditText mEditTextUsername, mEditTextPassword, mEditTextSecurityQue, mEditTextAnswer;
	private Button buttonSubmit;
	private Bundle extras;

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
				if(isValidateUiEntries()){
					saveFlatOwnerDetails();
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

	private void saveFlatOwnerDetails()
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
		flatOwnerDetails.setmFlatOwnerCreatedDateTime(Utilities.getUTCDateTime());
	}

}
