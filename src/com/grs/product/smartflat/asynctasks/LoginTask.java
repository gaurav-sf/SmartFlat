package com.grs.product.smartflat.asynctasks;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LoginTask extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = LoginTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	String username;
	String password;
	String role;
	Response mLoginStatus;
	
	public LoginTask(Context ctx, AsyncTaskCompleteListener<Response> listener, String username, String password,String role) 
	{
		this.context = ctx;
		this.listener = listener;
		this.username = username;
		this.password = password;	
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
			mLoginStatus =  smartFlatAPI.getLogin(username, password, role);
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
