package com.grs.product.smartflat.asynctasks;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.response.Response;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AddFamilyMemberTask  extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = LoginTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	private FamilyDetails mFamilyDetails;
	Response mFamilyMemberStatus;
	
	public AddFamilyMemberTask(Context ctx, AsyncTaskCompleteListener<Response> listener, FamilyDetails familyDetails) 
	{
		this.context = ctx;
		this.listener = listener;
		this.mFamilyDetails = familyDetails;
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
			mFamilyMemberStatus =  smartFlatAPI.saveFamilyMember(mFamilyDetails);
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
		
		if(mFamilyMemberStatus!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mFamilyMemberStatus);
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
