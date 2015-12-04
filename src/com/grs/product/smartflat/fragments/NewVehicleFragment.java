package com.grs.product.smartflat.fragments;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.AddVehicleTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.VehicleDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NewVehicleFragment extends Fragment {
	
	private RadioButton mRadioButton2Wheeler, mRadioButton4Wheeler;
	private RadioGroup mRadioGroupVehicleType;
	private EditText mEditTextVehicleNumber, mEditTextVehicleManufacturer, mEditTextVehicleModel, mEditTextVehicleColor;
	private Button mButtonAddVehicle;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_add_new_vehicle, container, false);
		initialiseUI(rootView);
		addListener();
        return rootView;	
        }
	
	private void initialiseUI(View rootView){
		mEditTextVehicleNumber = (EditText) rootView.findViewById(R.id.editTextVehicleNumber);
		mEditTextVehicleManufacturer = (EditText) rootView.findViewById(R.id.editTextManfaturerName);
		mEditTextVehicleModel = (EditText) rootView.findViewById(R.id.editTextModelName);
		mEditTextVehicleColor = (EditText) rootView.findViewById(R.id.editTextVehicleColor);
		mRadioGroupVehicleType = (RadioGroup) rootView.findViewById(R.id.RadioGroupVehicleType);
		mRadioButton2Wheeler = (RadioButton) rootView.findViewById(R.id.radioButton2Wheeler);
		mRadioButton4Wheeler = (RadioButton) rootView.findViewById(R.id.radioButton4Wheeler);
		mButtonAddVehicle = (Button) rootView.findViewById(R.id.buttonAddVehicle);
	}
	
	private void addListener(){
		mButtonAddVehicle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(validateUiEntries()){
					saveVehicleOnServer();
					clearUiEntries();
				}
			}
		});
		
	}
	
	private boolean validateUiEntries(){
		if(mEditTextVehicleNumber.getText().toString().equals("")){
			mEditTextVehicleNumber.setError("Please enter vehicle number");
			return false;
		}
		if(mEditTextVehicleManufacturer.getText().toString().equals("")){
			mEditTextVehicleManufacturer.setError("Please enter manufacturer name");
			return false;
		}
		if(mEditTextVehicleModel.getText().toString().equals("")){
			mEditTextVehicleModel.setError("Please enter vehicle model");
			return false;
		}
		if(mEditTextVehicleColor.getText().toString().equals("")){
			mEditTextVehicleColor.setError("Please enter vehicle color");
			return false;
		}
		
		return true;
	}
	
	private VehicleDetails getVehicleDetails(){
		VehicleDetails tempDetails = new VehicleDetails();
		String vehicleType = "2 Wheeler";
		int id = mRadioGroupVehicleType.getCheckedRadioButtonId();
		if(id == mRadioButton4Wheeler.getId()){
			vehicleType = "4 Wheeler";
		}
		tempDetails.setmVehicleType(vehicleType);
		tempDetails.setmVehicleCompany(mEditTextVehicleManufacturer.getText().toString());
		tempDetails.setmVehicleModel(mEditTextVehicleModel.getText().toString());
		tempDetails.setmVehicleNumber(mEditTextVehicleNumber.getText().toString());
		tempDetails.setmVehicleColor(mEditTextVehicleColor.getText().toString());
		
		return tempDetails;

	}
	
	private void clearUiEntries(){
		mEditTextVehicleNumber.setText("");
		mEditTextVehicleManufacturer.setText("");
		mEditTextVehicleModel.setText("");
		mEditTextVehicleColor.setText("");
		mRadioButton2Wheeler.setChecked(true);	
	}
	
	private void saveVehicleOnServer(){


		if (NetworkDetector.init(getActivity()).isNetworkAvailable()) 
		{
			new AddVehicleTask(getActivity(), new AddVehicleTaskListener(), getVehicleDetails())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(getActivity(),"Error", "Please check your Internet");
		}	
	
	}
	
	public class AddVehicleTaskListener implements AsyncTaskCompleteListener<Response>{

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
					saveVehicleInDB();
					clearUiEntries();
					Utilities.ShowAlertBox(getActivity(),"Message","Vehicle Added Successfully");		

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
	
	private void saveVehicleInDB(){
			SmartFlatDBManager dbManager = new SmartFlatDBManager();
		boolean status = dbManager.saveVehicleDetails(getVehicleDetails());
		if(status){
			Log.e("Vehicle", "Vehicle Added");
		}
		
	}

}
