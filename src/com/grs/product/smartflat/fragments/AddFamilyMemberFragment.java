package com.grs.product.smartflat.fragments;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.AddFamilyMemberTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

public class AddFamilyMemberFragment extends Fragment {
	
	private EditText mEditTextFMemberName, mEditTextFMemberDOB,  mEditTextFMemberAge,  mEditTextFMemberContactNo,mEditTextFMemberEmailId;
	private RadioGroup mRadioGroupLogin;
	private RadioButton mRadioButtonYes, mRadioButtonNo;
	private RadioButton mRadioButtonMale, mRadioButtonFemale;
	private RadioGroup mRadioGroupGender;
	private Button mButtonAddMember;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_add_family_member, container, false);
		initialiseUI(rootView);
		addListener();
        return rootView;
	}
	
	private void initialiseUI(View rootView){
		mEditTextFMemberName = (EditText) rootView.findViewById(R.id.editTextFmemberName);
		mEditTextFMemberDOB = (EditText) rootView.findViewById(R.id.editTextFmemberDOB);
		mEditTextFMemberAge = (EditText) rootView.findViewById(R.id.editTextFmemberAge);
		mEditTextFMemberAge.setText("25");
		mEditTextFMemberAge.setVisibility(View.GONE);
		mEditTextFMemberContactNo = (EditText) rootView.findViewById(R.id.editTextFmemberContactNo);
		mRadioGroupLogin = (RadioGroup) rootView.findViewById(R.id.RadioGroupLogin);
		mButtonAddMember = (Button) rootView.findViewById(R.id.buttonAddMember);
		mRadioButtonYes = (RadioButton) rootView.findViewById(R.id.radioButtonYes);
		mRadioButtonNo = (RadioButton) rootView.findViewById(R.id.radioButtonNo);
		mRadioGroupGender = (RadioGroup) rootView.findViewById(R.id.RadioGroupGender);
		mRadioButtonMale = (RadioButton) rootView.findViewById(R.id.radioButtonMale);
		mRadioButtonFemale = (RadioButton) rootView.findViewById(R.id.radioButtonFemale);
		mEditTextFMemberEmailId =  (EditText) rootView.findViewById(R.id.editTextFmemberEmailId);
	}
	
	private void addListener(){
		mButtonAddMember.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(validateUIEntries()){
					saveFamilyMemberOnServer();
					
				}
			}
		});
		mEditTextFMemberDOB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new SelectDateFragment();
	            newFragment.show(getFragmentManager(), "DatePicker");			
			}
		});
	}
	
	private boolean validateUIEntries(){

		if(mEditTextFMemberName.getText().toString().equals(""))
		{
			mEditTextFMemberName.setError("Please enter member name");
			return false;
		}
/*		if(mEditTextFMemberRelation.getText().toString().equals(""))
		{
			mEditTextFMemberRelation.setError("Please enter relation with member");
			return false;
		}*/
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
		if(mEditTextFMemberEmailId.getText().toString().equals(""))
		{
			mEditTextFMemberEmailId.setError("Please enter email id");
			return false;
		}
		
		return true;
	}
	
	private FamilyDetails getFamilyDetails(){
		FamilyDetails tempFamilyDetails = new FamilyDetails();
		tempFamilyDetails.setmFamilyMemberName(mEditTextFMemberName.getText().toString());
		//tempFamilyDetails.setmFamilyMemberRelation(mEditTextFMemberRelation.getText().toString());
		tempFamilyDetails.setmFamilyMemberDOB(mEditTextFMemberDOB.getText().toString());
		tempFamilyDetails.setmFamilyMemberAge(mEditTextFMemberAge.getText().toString());
		tempFamilyDetails.setmFamilyMemberContactno(mEditTextFMemberContactNo.getText().toString());
		tempFamilyDetails.setmFamilyMemberEmailId(mEditTextFMemberEmailId.getText().toString());
		String gender = "Male";
		int idgender = mRadioGroupGender.getCheckedRadioButtonId();
		if(idgender == mRadioButtonFemale.getId()){
			gender = "Female";
		}
		tempFamilyDetails.setmGender(gender);
		
		boolean needLogin=false;
		int id = mRadioGroupLogin.getCheckedRadioButtonId();
		if(id == mRadioButtonYes.getId()){
			needLogin = true;
		}
		tempFamilyDetails.setmNeedLogin(needLogin);
		tempFamilyDetails.setmFlatOwnerCode(SmartFlatApplication.getFlatOwnerAccessCodeFromSharedPreferences());
		return tempFamilyDetails;

		}
	
	private void clearAllFields(){
		mEditTextFMemberName.setText("");
		//mEditTextFMemberRelation.setText("");
		mEditTextFMemberDOB.setText("");
		mEditTextFMemberAge.setText("");
		mEditTextFMemberContactNo.setText("");
		mRadioButtonNo.setChecked(true);
	}
	
	private void saveFamilyMemberOnServer(){


		if (NetworkDetector.init(getActivity()).isNetworkAvailable()) 
		{
			new AddFamilyMemberTask(getActivity(), new AddFamilyMemberTaskListener(), getFamilyDetails())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(getActivity(),"Error", "Please check your Internet");
		}	
	
	}
	
	public class AddFamilyMemberTaskListener implements AsyncTaskCompleteListener<Response>{

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(getActivity(), "", false);
		
		}

		@Override
		public void onTaskComplete(Response result) {

			if (result != null) 
			{
				if (result.getStatus().equalsIgnoreCase("success")) 
				{
					saveFamilyDetailsInDB();
					clearAllFields();
					Utilities.ShowAlertBox(getActivity(),"Message","Family member added successfully");				

				}else{
					Utilities.ShowAlertBox(getActivity(),"Error","Error Occured please try later");		
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
			Utilities.ShowAlertBox(getActivity(), "Error", "Server error occured. Please try later");
			
		}
		
	}
	
	private void saveFamilyDetailsInDB(){
			SmartFlatDBManager dbManager = new SmartFlatDBManager();
		boolean status = dbManager.saveFamilyDetails(getFamilyDetails());
		if(status){
			Log.e("Family Member", "Family Member Added");
		}
		
	}
	
	 public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	        @Override
	        public Dialog onCreateDialog(Bundle savedInstanceState) {
	        final Calendar calendar = Calendar.getInstance();
	        int yy = calendar.get(Calendar.YEAR);
	        int mm = calendar.get(Calendar.MONTH);
	        int dd = calendar.get(Calendar.DAY_OF_MONTH);
	        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
	        }

	        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
	            populateSetDate(yy, mm+1, dd);
	        }
	        public void populateSetDate(int year, int month, int day) {
	        	mEditTextFMemberDOB.setText(month+"/"+day+"/"+year);
	            }

	    }

}
