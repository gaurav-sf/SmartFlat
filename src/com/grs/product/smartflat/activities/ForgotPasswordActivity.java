package com.grs.product.smartflat.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.activities.LoginActivity.LoginTaskCompleteListener;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.LoginTask;
import com.grs.product.smartflat.asynctasks.UpdatePasswordTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerDetails;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

public class ForgotPasswordActivity  extends Activity{
	
	private EditText mEditTextSecurityquestion, mEditTextAnswer, mEditTextNewPassword;
	private Button mButtonResetPassword;
	private TextView mTextViewNewPassword;
	private FlatOwnerDetails mFlatOwnerDetails;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);	
		mFlatOwnerDetails = getFlatOwnerDetails();
		if(mFlatOwnerDetails == null){
			showAlertBox(this, "Message", "Sorry no account associated with this device");
			
		}else{
			initialiseUI();
			addListeners();
		}

	}
	
	private void initialiseUI(){
		mEditTextSecurityquestion = (EditText) findViewById(R.id.editTextSecurityQue);
		mEditTextSecurityquestion.setText(mFlatOwnerDetails.getmSecurityQuestion());
		mEditTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
		mEditTextNewPassword = (EditText) findViewById(R.id.editTextNewPassword);
		mTextViewNewPassword = (TextView) findViewById(R.id.textViewNewPassword);
		mButtonResetPassword = (Button) findViewById(R.id.buttonResetPassword);		
	}
	
	private void addListeners(){
		mButtonResetPassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (validateUI()) {
					if(mButtonResetPassword.getText().toString().equalsIgnoreCase("Submit")){
						checkAnswer();
					}else{
						updatePassword();
						
					}			
				}
				
			}
		});		
	}
	
	private boolean validateUI(){
		if(mButtonResetPassword.getText().toString().equalsIgnoreCase("Submit")){
			if(mEditTextAnswer.getText().toString().equals("")){
				mEditTextAnswer.setError("Please enter your answer");
				return false;
			}
			
		}else if(mButtonResetPassword.getText().toString().equalsIgnoreCase("Reset Password")){
			if(mEditTextNewPassword.getText().toString().equals("")){
				mEditTextNewPassword.setError("Please enter new password");
				return false;
			}
		}
		return true;
	}
	
	private FlatOwnerDetails getFlatOwnerDetails(){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		FlatOwnerDetails temp = null;
		Cursor details = dbManager.getFlatOwnerDetails();
		if (details!=null && details.getCount()>0) {
			details.moveToFirst();
			temp = new FlatOwnerDetails();
			temp.setmSecurityQuestion(details.getString(details.getColumnIndex(TableFlatOwnerDetails.SECURITY_QUESTION)));
			temp.setmAnswer(details.getString(details.getColumnIndex(TableFlatOwnerDetails.ANSWER)));
		}
		return temp;
	}
	
	private void checkAnswer(){
		if(mEditTextAnswer.getText().toString().equalsIgnoreCase(mFlatOwnerDetails.getmAnswer()))
		{
			mEditTextAnswer.setEnabled(false);
			mTextViewNewPassword.setVisibility(View.VISIBLE);
			mEditTextNewPassword.setVisibility(View.VISIBLE);
			mButtonResetPassword.setText("RESET PASSWORD");
			
		}else{
			Utilities.ShowAlertBox(this, "Message", "Sorry..! \n You have provided a wrong answer");
			mEditTextAnswer.setText("");
		}
	}
	
	private void updatePassword(){
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new UpdatePasswordTask(getApplicationContext(), new UpdatePasswordTaskListener(),mEditTextNewPassword.getText().toString())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(ForgotPasswordActivity.this,"Error", "Please check your Internet");
		}		
	}
	
	public class UpdatePasswordTaskListener implements AsyncTaskCompleteListener<Response> {

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(ForgotPasswordActivity.this, "", false);		
		}

		@Override
		public void onTaskComplete(Response result) {
			if (result != null) 
			{
				if (result.getStatus().equalsIgnoreCase("success")) 
				{
					showAlertBox(ForgotPasswordActivity.this,"Error",result.getMessage());					
					
				}else{
					Utilities.ShowAlertBox(ForgotPasswordActivity.this,"Error",result.getMessage());		
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
			Utilities.ShowAlertBox(ForgotPasswordActivity.this,"Error",e.getMessage());					
		}		
	}
	
	private void showAlertBox(final Context context, String title,
			String message) {
		final Dialog mDialog = new Dialog(context,
				android.R.style.Theme_Translucent_NoTitleBar);
		View layout = LayoutInflater.from(context)
				.inflate(R.layout.alert, null);
		TextView tvAlert = (TextView) layout.findViewById(R.id.tvAlert);
		TextView tvAlertMsg = (TextView) layout.findViewById(R.id.tvAlertMsg);
		tvAlertMsg.setText(message);
		tvAlert.setText(title);
		mDialog.setContentView(layout);
		Button btnOk = (Button) layout.findViewById(R.id.btnOk);
		btnOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View view) {
				mDialog.dismiss();
	    		Intent goToLoginScreen = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
	    		startActivity(goToLoginScreen);
	    		finish();
			}
		});
		mDialog.show();
	}
	
	@Override
	public void onBackPressed() {
		Intent goToLoginScreen = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
		startActivity(goToLoginScreen);
		finish();
	}
}