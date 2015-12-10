package com.grs.product.smartflat.activities;

import com.grs.product.smartflat.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CreateAccoutForActivity extends Activity{

	private Button mButtonRegisterFlatOwner, mButtonRegisterFamilyMember, mButtonRegisterTenant;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account_for);
		initialiseUI();
		addListeners();
	}
	
	private void initialiseUI(){
		mButtonRegisterFlatOwner = (Button) findViewById(R.id.buttonRegisterFlatOwner);
		mButtonRegisterFamilyMember = (Button) findViewById(R.id.buttonRegisterFamilyMember);
		mButtonRegisterTenant = (Button) findViewById(R.id.buttonRegisterTenant);
	}
	
	private void addListeners(){
		mButtonRegisterFlatOwner.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
        		Intent goToRegistration = new Intent(CreateAccoutForActivity.this,StartActivity.class);
        		startActivity(goToRegistration);
        		finish();
			}
		});
		
		mButtonRegisterFamilyMember.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
        		Intent goToRegistration = new Intent(CreateAccoutForActivity.this,FamilyMemberAndTenantCheckValidationActivity.class);
        		goToRegistration.putExtra("registrationFor", "FamilyMember");
        		startActivity(goToRegistration);
        		finish();			
			}
		});		
		
		mButtonRegisterTenant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
        		Intent goToRegistration = new Intent(CreateAccoutForActivity.this,FamilyMemberAndTenantCheckValidationActivity.class);
        		goToRegistration.putExtra("registrationFor", "Tenant");
        		startActivity(goToRegistration);
        		finish();				
			}
		});	
	}
	
	@Override
	public void onBackPressed() {
		Intent goToLoginScreen = new Intent(CreateAccoutForActivity.this,LoginActivity.class);
		startActivity(goToLoginScreen);
		finish();
	}
}
