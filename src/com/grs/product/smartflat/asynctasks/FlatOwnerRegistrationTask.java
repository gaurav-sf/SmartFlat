package com.grs.product.smartflat.asynctasks;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.Utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class FlatOwnerRegistrationTask  extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = LoginTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	private FlatOwnerDetails mFlatOwnerDetails;
	Response mRegistrationStatus;
	
	public FlatOwnerRegistrationTask(Context ctx, AsyncTaskCompleteListener<Response> listener, FlatOwnerDetails mFlatOwnerDetails) 
	{
		this.context = ctx;
		this.listener = listener;
		this.mFlatOwnerDetails=mFlatOwnerDetails;
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
			mRegistrationStatus =  smartFlatAPI.getFlatOwnerRegistration(mFlatOwnerDetails);
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
		
		if(mRegistrationStatus!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mRegistrationStatus);
				listener = null;
			}
		}
		else 
		{		
			if(listener!=null)
			{
				if(error!=null)
				{
					Utilities.ShowAlertBox(context, error.errorType, error.errorMessage);
					listener.onStopedWithError(error);
				}
				
				listener = null;
			}
			
		}

	}

}
