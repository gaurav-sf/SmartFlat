package com.grs.product.smartflat.activities;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.FetchSocietyDetailsTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.SocietyDetails;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity {
	private Button button_continue;
	private EditText edittext_society_code;
	private final String LOG = StartActivity.class.getName();

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
		if(societyCode.equals("SF@GRS1001"))
		{
			//Here will be web service call to check this code in cloud DB and fetch the society details and save in local DB
			saveSocietyDetailsInDB();
			SmartFlatApplication.saveSocietyCodeInSharedPreferences(societyCode);
			Intent registrationStep1 = new Intent(this, RegistrationStep1Activity.class);
			startActivity(registrationStep1);
			finish();	
		}else{
			edittext_society_code.setError("Please enter valid society code");
		}

	}

	private void saveSocietyDetailsInDB(){
		SocietyDetails societyDetails = new SocietyDetails();
		societyDetails.setmSocietyCode("SM@GRS1001");
		societyDetails.setmSocietyOwnerName("Gaurav Lakade");
		societyDetails.setmSocietyOwnerAddressLine1("Mahendra Colony");
		societyDetails.setmSocietyOwnerAddressLine2("Behind VMV");
		societyDetails.setmSocietyOwnerCity("Amravati");
		societyDetails.setmSocietyOwnerState("Maharashtra");
		societyDetails.setmSocietyOwnerPIN("444604");
		societyDetails.setmSocietyOwnerContactNo("9028848725");
		societyDetails.setmSocietyOwnerEmailId("gauravlakade@gmail.com");
		societyDetails.setmSocietyName("Destiny");
		societyDetails.setmBuildingName("A@B@C@D@E@F");
		societyDetails.setmTotalFloorNumber(7);
		societyDetails.setmSocietyAddressLine1("Vishal Nagar");
		societyDetails.setmSocietyAddressLine2("Pimple Nilakh");
		societyDetails.setmSocietyAddressCity("Pune");
		societyDetails.setmSocietyAddressState("Maharashtra");
		societyDetails.setmSocietyAddressPIN("411027");

		SmartFlatDBManager objManager = new SmartFlatDBManager();
		boolean result = objManager.saveSocietyDetails(societyDetails);
		if(result){
			Log.e(LOG, "Society Details Insertion Successful");
		}	
	}

	private void societyDetailsFetchCall(String societyCode){

		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new FetchSocietyDetailsTask(getApplicationContext(), new FetchSocietyDetailsListener(), societyCode).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(getApplicationContext(),"Error", "Please check your Internet");
		}
	}

	public class FetchSocietyDetailsListener implements AsyncTaskCompleteListener<SocietyDetails>{

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(getApplicationContext(), "", false);
		}

		@Override
		public void onTaskComplete(SocietyDetails result) {
			if(result != null){
				saveSocietyDetailsInDB();
				SmartFlatApplication.saveSocietyCodeInSharedPreferences(result.getmSocietyCode());
			}
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			Utilities.ShowAlertBox(getApplicationContext(), "Error", e.getMessage());
			CustomProgressDialog.removeDialog();
		}


	}

}
