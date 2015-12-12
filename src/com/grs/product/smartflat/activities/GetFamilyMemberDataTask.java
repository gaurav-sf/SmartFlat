package com.grs.product.smartflat.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.asynctasks.GetFlatOwnerDataTask;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;

public class GetFamilyMemberDataTask extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = GetFlatOwnerDataTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	String username;
	String ownerCode;
	Response mStatus;
	
	public GetFamilyMemberDataTask(Context ctx, AsyncTaskCompleteListener<Response> listener, String username,String role, String ownerCode) 
	{
		this.context = ctx;
		this.listener = listener;
		this.username = username;
		this.ownerCode = ownerCode;
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
			mStatus =  smartFlatAPI.getFlatOwnerData(username, ownerCode,"");
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
		
		if(mStatus!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mStatus);
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
