package com.grs.product.smartflat;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gcm.GCMBaseIntentService;
import com.grs.product.smartflat.activities.DashBoardActivity;
import com.grs.product.smartflat.activities.RequestDetailsActivity;
import com.grs.product.smartflat.apicall.JSONSingleObjectDecode;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.models.RequestMessages;
import com.grs.product.smartflat.utils.Param;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GCMIntentService extends GCMBaseIntentService {
	
	public static String registerid="";
	public static int uniqueid = 0;

	
	public GCMIntentService() {
		super(Param.GOOGLE_PROJ_ID);
	}

	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		String message = intent.getExtras().getString("message");
		if(message.contains("New Message"))
		{
			JSONObject json;
			try {
				json = new JSONObject(intent.getExtras().getString("jsonData"));
				JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
				saveMessageInDB(objectjson.getMessages(json));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Log.i(TAG, "new message= "+message);
		generateNotification(context, message);
		
	}

	@Override
	protected void onRegistered(Context arg0, String registeredid) {
		// TODO Auto-generated method stub
		Log.d("----Device registered: regId-----",registeredid);
		registerid = registeredid;
		
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		// TODO Auto-generated method stub
		return super.onRecoverableError(context, errorId);
	}
	
	/**
	 * Issues a notification to inform the user that server has sent a message.
	 */
	@SuppressWarnings("deprecation")
	private static void generateNotification(Context context, String message) {
		
		Intent notificationIntent = null;
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, message, when);
		// Play default notification sound
		  notification.defaults |= Notification.DEFAULT_SOUND;
		  notification.defaults |= Notification.DEFAULT_VIBRATE;
		  
		String title = context.getString(R.string.app_name);

		if(message.contains("New Message")){
			 notificationIntent = new Intent(context, RequestDetailsActivity.class);
			 notificationIntent.putExtra("requestno", message.split("-")[1].trim());
		}else if(message.contains("New Notice")){
			notificationIntent = new Intent(context, DashBoardActivity.class);
		}
			
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		uniqueid++;
		notificationManager.notify(0, notification);
	}
	
	private void saveMessageInDB(List<RequestMessages> listMessages){
		
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		for (int i = 0; i < listMessages.size(); i++)
		{
			boolean result = objManager.saveMessage(listMessages.get(i));
			if(result)
			{
				Log.e("Message Details", " Insertion Successful");
			}
		}		
	
	}

}
