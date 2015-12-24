package com.grs.product.smartflat.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;

public class SignOutTask  extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = SignOutTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	String role;
	Response mSignOutStatus;
	
	public SignOutTask(Context ctx, AsyncTaskCompleteListener<Response> listener,String role) 
	{
		this.context = ctx;
		this.listener = listener;
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
			mSignOutStatus =  smartFlatAPI.getSignOut(role);
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
		
		if(mSignOutStatus!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mSignOutStatus);
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
