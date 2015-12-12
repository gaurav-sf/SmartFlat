package com.grs.product.smartflat.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;

public class GetFlatOwnerDataTask extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = GetFlatOwnerDataTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	String username;
	String ownerCode;
	String accessRole;
	Response mStatus;
	
	public GetFlatOwnerDataTask(Context ctx, AsyncTaskCompleteListener<Response> listener, String username,String accessRole, String ownerCode) 
	{
		this.context = ctx;
		this.listener = listener;
		this.username = username;
		this.ownerCode = ownerCode;
		this.accessRole = accessRole;
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
			mStatus =  smartFlatAPI.getFlatOwnerData(username, ownerCode, accessRole);
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
