package com.grs.product.smartflat.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.models.VehicleDetails;
import com.grs.product.smartflat.response.Response;

public class AddVehicleTask  extends AsyncTask<Void, Void, SmartFlatError> {

	private static final String TAG = LoginTask.class.getName();
	final Context context;
	private AsyncTaskCompleteListener<Response> listener = null;
	private VehicleDetails mVehicleDetails;
	Response mVehicleStatus;
	
	public AddVehicleTask(Context ctx, AsyncTaskCompleteListener<Response> listener, VehicleDetails vehicleDetails) 
	{
		this.context = ctx;
		this.listener = listener;
		this.mVehicleDetails = vehicleDetails;
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
			mVehicleStatus =  smartFlatAPI.saveVehicleDetails(mVehicleDetails);
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
		
		if(mVehicleStatus!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mVehicleStatus);
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
