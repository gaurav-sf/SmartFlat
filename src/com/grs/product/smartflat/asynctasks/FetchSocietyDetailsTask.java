package com.grs.product.smartflat.asynctasks;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.Utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class FetchSocietyDetailsTask extends AsyncTask<Void, Void, SmartFlatError>{
	
	private static final String TAG = FetchSocietyDetailsTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<SocietyDetails> listener = null;
	String societyCode;
	SocietyDetails mSocietyDetails;
	
	public FetchSocietyDetailsTask(Context context , AsyncTaskCompleteListener<SocietyDetails> listener, String societyCode){
		this.context = context;
		this.societyCode= societyCode; 
		this.listener = listener;
	}
	
	@Override
	protected void onPreExecute() {
		if(listener!=null)
			listener.onStarted();
	}

	@Override
	protected SmartFlatError doInBackground(Void... arg0) {
		
		try {
			SmartFlatAPI smartFlatAPI = new SmartFlatAPI(context);
			mSocietyDetails = smartFlatAPI.getSocietyDetails(societyCode);
			if(mSocietyDetails == null){
				return new SmartFlatError("Invalid Society Code");
			}
		} catch (SmartFlatError e) {
			Log.e(TAG, e.toString());
			return e;
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(SmartFlatError error) {
		
		if(mSocietyDetails!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mSocietyDetails);
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
