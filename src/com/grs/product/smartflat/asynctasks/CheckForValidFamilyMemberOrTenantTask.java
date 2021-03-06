package com.grs.product.smartflat.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;

public class CheckForValidFamilyMemberOrTenantTask  extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = LoginTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	String flatOwnerCode;
	String username;
	String role;
	Response mValidationStatus;
	
	public CheckForValidFamilyMemberOrTenantTask(Context ctx, AsyncTaskCompleteListener<Response> listener, String flatOwnerCode, String username,String role) 
	{
		this.context = ctx;
		this.listener = listener;
		this.flatOwnerCode = flatOwnerCode;	
		this.username = username;		
		this.role = role;
	}
	
	@Override
	protected void onPreExecute() {
		if(listener!=null)
			listener.onStarted();
	}
	
	@Override
	protected SmartFlatError doInBackground(Void... params) {
		
		SmartFlatAPI smartFlatAPI = new SmartFlatAPI(context);
		try 
		{
			mValidationStatus =  smartFlatAPI.getFamilyMemberOrTenantValidation(flatOwnerCode, username, role);
		}
		catch (SmartFlatError e) 
		{
			Log.e(TAG, e.toString());
			return e;		
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(SmartFlatError error) {
		
		if(mValidationStatus!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mValidationStatus);
				listener = null;
			}
		}
		else 
		{		
			if(listener!=null)
			{
				if(error!=null)
				{
					listener.onStopedWithError(error);
				}
				
				listener = null;
			}
			
		}

	}

}
