package com.grs.product.smartflat.activities;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.models.SocietyDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity {
	Button button_continue;
	EditText edittext_society_code;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		initializeUI();
		addListeners();
	}
	
	private void initializeUI(){
		button_continue = (Button) findViewById(R.id.button_continue);
		edittext_society_code = (EditText) findViewById(R.id.edittext_society_code);
	}
	
	private void addListeners(){
		button_continue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(edittext_society_code.getText().toString().equals("")){
					edittext_society_code.setError("Please enter society code");
				}else{
					societyCodeValidation(edittext_society_code.getText().toString());
				}
				
			}
		});
	}
	
	private void societyCodeValidation(String societyCode){
		if(societyCode.equals("SM@GRS1001"))
		{
			//Here will be web service call to check this code in cloud DB and fetch the society details and save in local DB
			
			//saveSocietyDetailsInDB();
			Intent registrationStep1 = new Intent(this, RegistrationStep1Activity.class);
			startActivity(registrationStep1);
			
		}else{
			edittext_society_code.setError("Please enter valid society code");
		}
		
	}
	
	private void saveSocietyDetailsInDB(){
		SocietyDetails societyDetails = new SocietyDetails();
		societyDetails.setSocietyCode("SM@GRS1001");
		societyDetails.setSocietyOwnerName("Gaurav Lakade");
		societyDetails.setSocietyOwnerAddressLine1("Mahendra Colony");
		societyDetails.setSocietyOwnerAddressLine2("Behind VMV");
		societyDetails.setSocietyOwnerCity("Amravati");
		societyDetails.setSocietyOwnerState("Maharashtra");
		societyDetails.setSocietyOwnerPin("444604");
		societyDetails.setSocietyOwnerContactNo("9028848725");
		societyDetails.setSocietyOwnerEmailId("gauravlakade@gmail.com");
		societyDetails.setSocietyName("Destiny");
		societyDetails.setBuildingName("A?B?C?D?E?F");
		societyDetails.setTotalFloorNumber(7);
		societyDetails.setSocietyAddressLine1("Vishal Nagar");
		societyDetails.setSocietyAddressLine2("Pimple Nilakh");
		societyDetails.setSocietyAddressCity("Pune");
		societyDetails.setSocietyAddressState("Maharashtra");
		societyDetails.setSocietyAddressPin("411027");
		
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		objManager.saveSocietyDetails(societyDetails);
		
		
	}
	

}
