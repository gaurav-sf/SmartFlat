package com.grs.product.smartflat.asynctasks;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.RequestMessages;
import com.grs.product.smartflat.models.VisitorDetails;

public class GetVisitorsTask extends AsyncTask<Void, Void, SmartFlatError>{

	private static final String TAG = GetMessagesTask.class.getName();
	final Context mContext;
	private AsyncTaskCompleteListener<List<VisitorDetails>> listener = null;
	List<VisitorDetails> listVisitors;
	
	public GetVisitorsTask(Context mContext, 
			AsyncTaskCompleteListener<List<VisitorDetails>> listener) 
	{		
		this.mContext = mContext;
		this.listener = listener;
	}
	
	@Override
	protected void onPreExecute() {
		if(listener!=null)
			listener.onStarted();
	}
	
	@Override
	protected SmartFlatError doInBackground(Void... params) {
		SmartFlatAPI smartFlatAPI = new SmartFlatAPI(mContext);		
		try {
			
			listVisitors = smartFlatAPI.getVisitors();
			if(listVisitors==null){
					return new SmartFlatError("No Visitors");
			}
		} catch (SmartFlatError e) {
			Log.e(TAG, e.toString());
			return e;	
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(SmartFlatError error) {
		
		if(listVisitors!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(listVisitors);
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