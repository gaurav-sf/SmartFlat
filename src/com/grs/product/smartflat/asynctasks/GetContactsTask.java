package com.grs.product.smartflat.asynctasks;

import java.util.List;

import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.apicall.SmartFlatAPI;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.ContactDetails;
import com.grs.product.smartflat.models.RequestMessages;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class GetContactsTask    extends AsyncTask<Void, Void, SmartFlatError>{

	private static final String TAG = GetMessagesTask.class.getName();
	final Context mContext;
	private AsyncTaskCompleteListener<List<ContactDetails>> listener = null;
	List<ContactDetails> listContacts;
	
	public GetContactsTask(Context mContext, 
			AsyncTaskCompleteListener<List<ContactDetails>> listener) 
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
			
			listContacts = smartFlatAPI.getContacts();
			if (listContacts==null) {
				return new SmartFlatError("No Contacts");				
			}
			
		} catch (SmartFlatError e) {
			Log.e(TAG, e.toString());
			return e;	
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(SmartFlatError error) {
		
		if(listContacts!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(listContacts);
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