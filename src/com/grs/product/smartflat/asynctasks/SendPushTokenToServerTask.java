package com.grs.product.smartflat.asynctasks;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class SendPushTokenToServerTask  extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = LoginTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	String pushToken;
	Response mLoginStatus;
	private String flatOwnerCode;
	private String societyCode;
	
	public SendPushTokenToServerTask(Context ctx, AsyncTaskCompleteListener<Response> listener, String pushToken,
			String flatOwnerCode, String societyCode) 
	{
		this.context = ctx;
		this.listener = listener;
		this.pushToken = pushToken;	
		this.flatOwnerCode = flatOwnerCode;
		this.societyCode = societyCode;
		
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
			mLoginStatus =  smartFlatAPI.sendPushToken(pushToken,flatOwnerCode,societyCode);
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
		
		if(mLoginStatus!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mLoginStatus);
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
