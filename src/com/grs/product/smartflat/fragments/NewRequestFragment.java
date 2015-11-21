package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerRequestDetails;
import com.grs.product.smartflat.models.RequestDetails;
import com.grs.product.smartflat.utils.Utilities;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class NewRequestFragment extends Fragment {
	
	private Spinner mSpinnerRequestCategory;
	private RadioButton mRadioButtonRequest, mRadioButtonComplaint;
	private RadioGroup mRadioGroupType;
	private RadioButton mRadioButtonHigh, mRadioButtonMedium, mRadioButtonLow;
	private RadioGroup mRadioGroupPriority;
	private EditText mEditTextRequestDetails;
	private Button mButtonRaiseRequest;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_new_request, container, false);
		initialiseUI(rootView);
		createSpinnerData();
		addListener();
        return rootView;
	}
	
	private void initialiseUI(View rootView){
		mSpinnerRequestCategory = (Spinner) rootView.findViewById(R.id.spinnertRequestType);
		mRadioGroupType = (RadioGroup) rootView.findViewById(R.id.RadioGroupType);
		mRadioButtonRequest = (RadioButton) rootView.findViewById(R.id.radioButtonRequest);
		mRadioButtonComplaint = (RadioButton) rootView.findViewById(R.id.radioButtonComplaint);
		mRadioGroupPriority = (RadioGroup) rootView.findViewById(R.id.RadioGroupPriority);
		mRadioButtonHigh = (RadioButton) rootView.findViewById(R.id.radioButtonHigh);
		mRadioButtonMedium = (RadioButton) rootView.findViewById(R.id.radioButtonMedium);
		mRadioButtonLow = (RadioButton) rootView.findViewById(R.id.radioButtonLow);
		mEditTextRequestDetails = (EditText) rootView.findViewById(R.id.editTextRequestDetails);
		mButtonRaiseRequest = (Button) rootView.findViewById(R.id.buttonRaiseRequest);		
	}
	
	private void createSpinnerData(){
		List<String> listRequestType = new ArrayList<String>();
		listRequestType.add("Parcel");
		listRequestType.add("Electricity");
		listRequestType.add("Cleaning");
		listRequestType.add("Parking");
		listRequestType.add("Plumber");
		listRequestType.add("Other");		
		ArrayAdapter<String> adapterBuildingName = new ArrayAdapter<String>
		(getActivity(), android.R.layout.simple_dropdown_item_1line, listRequestType);
		adapterBuildingName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		mSpinnerRequestCategory.setAdapter(adapterBuildingName);
	}
	
	private void addListener(){
		mButtonRaiseRequest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(validateUiEntries()){
					saveRequestData();
					clearUiFields();
				}
				
			}
		});
	}
	
	private boolean validateUiEntries(){
		
		if(mEditTextRequestDetails.getText().toString().equals("")){
			mEditTextRequestDetails.setError("Please enter details");
		}
		
		return true;
	}
	
	private void saveRequestData(){
		RequestDetails temp = new RequestDetails();
		temp.setmRequestNumber(getRequestNumber());
		String type = "Request";
		int idtype = mRadioGroupType.getCheckedRadioButtonId();
		if(idtype == mRadioButtonComplaint.getId()){
			type = "Complaint";
		}
		temp.setmRequestType(type);
		temp.setmRequestCategory(mSpinnerRequestCategory.getSelectedItem().toString());
		String priority = "Low";
		int id = mRadioGroupPriority.getCheckedRadioButtonId();
		if(id == mRadioButtonHigh.getId()){
			priority = "High";
		}
		if(id == mRadioButtonMedium.getId()){
			priority = "Medium";
		}
		temp.setmRequestPriority(priority);
		temp.setmRequestDetails(mEditTextRequestDetails.getText().toString());
		temp.setmRequestStatus("Raised");
		temp.setmRequestDateTime(Utilities.getCurrentDateTime());
		
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		boolean status = dbManager.saveRequestDetails(temp);
		if(status){
			Log.e("Request", "Request Raised");
		}
	}
	
	private String getRequestNumber(){
		String requestNumber = "";
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getAllRequestDetails();
		if(cursor.getCount()==0){
			requestNumber = "REQ100";
		}else{
			cursor.moveToLast();
			String id = cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER));
			int nextid = Integer.parseInt(id.replace("REQ", ""))+1;
			requestNumber = "REQ"+nextid;		
		}
		
		return requestNumber;
	}
	
	private void clearUiFields(){
		mEditTextRequestDetails.setText("");
		mRadioButtonLow.setChecked(true);
		mRadioButtonRequest.setChecked(true);
		createSpinnerData();
	}


}
