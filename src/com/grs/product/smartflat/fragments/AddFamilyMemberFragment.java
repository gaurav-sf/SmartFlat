package com.grs.product.smartflat.fragments;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.R.id;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDatabase;
import com.grs.product.smartflat.models.FamilyDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddFamilyMemberFragment extends Fragment {
	
	private EditText mEditTextFMemberName, mEditTextFMemberRelation, mEditTextFMemberDOB,  mEditTextFMemberAge,  mEditTextFMemberContactNo;
	private RadioGroup mRadioGroupLogin;
	private RadioButton mRadioButtonYes, mRadioButtonNo;
	private Button mButtonAddMember;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		initialiseUI(rootView);
		addListener();
        return rootView;
	}
	
	private void initialiseUI(View rootView){
		mEditTextFMemberName = (EditText) rootView.findViewById(R.id.editTextFmemberName);
		mEditTextFMemberDOB = (EditText) rootView.findViewById(R.id.editTextFmemberDOB);
		mEditTextFMemberAge = (EditText) rootView.findViewById(R.id.editTextFmemberAge);
		mEditTextFMemberRelation = (EditText) rootView.findViewById(R.id.editTextFmemberRelation);
		mEditTextFMemberContactNo = (EditText) rootView.findViewById(R.id.editTextFmemberContactNo);
		mRadioGroupLogin = (RadioGroup) rootView.findViewById(R.id.RadioGroupLogin);
		mButtonAddMember = (Button) rootView.findViewById(R.id.buttonAddMember);
		mRadioButtonYes = (RadioButton) rootView.findViewById(R.id.radioButtonYes);
		mRadioButtonNo = (RadioButton) rootView.findViewById(R.id.radioButtonNo);
	}
	
	private void addListener(){
		mButtonAddMember.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validateUIEntries()){
					saveFamilyDetails();
					clearAllFields();
					
				}
			}
		});
	}
	
	private boolean validateUIEntries(){

		if(mEditTextFMemberName.getText().toString().equals(""))
		{
			mEditTextFMemberName.setError("Please enter member name");
			return false;
		}
		if(mEditTextFMemberRelation.getText().toString().equals(""))
		{
			mEditTextFMemberRelation.setError("Please enter relation with member");
			return false;
		}
		if(mEditTextFMemberDOB.getText().toString().equals(""))
		{
			mEditTextFMemberDOB.setError("Please enter DOB");
			return false;
		}
		if(mEditTextFMemberAge.getText().toString().equals(""))
		{
			mEditTextFMemberAge.setError("Please enter  Age");
			return false;
		}
		if(mEditTextFMemberContactNo.getText().toString().equals(""))
		{
			mEditTextFMemberContactNo.setError("Please enter contact no");
			return false;
		}
		
	
		return true;
	}
	
	private void saveFamilyDetails(){
		FamilyDetails tempFamilyDetails = new FamilyDetails();
		tempFamilyDetails.setmFamilyMemberName(mEditTextFMemberName.getText().toString());
		tempFamilyDetails.setmFamilyMemberRelation(mEditTextFMemberRelation.getText().toString());
		tempFamilyDetails.setmFamilyMemberDOB(mEditTextFMemberDOB.getText().toString());
		tempFamilyDetails.setmFamilyMemberAge(mEditTextFMemberAge.getText().toString());
		tempFamilyDetails.setmFamilyMemberContactno(mEditTextFMemberContactNo.getText().toString());
		boolean needLogin=false;
		int id = mRadioGroupLogin.getCheckedRadioButtonId();
		if(id == mRadioButtonYes.getId()){
			needLogin = true;
		}
		tempFamilyDetails.setmNeedLogin(needLogin);
		tempFamilyDetails.setmFlatOwnerCode(SmartFlatApplication.getFlatOwnerAccessCodeFromSharedPreferences());
		
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		dbManager.saveFamilyDetails(tempFamilyDetails);
		}
	
	private void clearAllFields(){
		mEditTextFMemberName.setError("");
		mEditTextFMemberRelation.setError("");
		mEditTextFMemberDOB.setError("");
		mEditTextFMemberAge.setError("");
		mEditTextFMemberContactNo.setError("");
		mRadioButtonNo.setChecked(true);
	}

}
