package com.grs.product.smartflat.activities;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.adapter.MessageListAdapter;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.SendMessageTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerRequestDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableMessageDetails;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.RequestDetails;
import com.grs.product.smartflat.models.RequestMessages;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Param;
import com.grs.product.smartflat.utils.Utilities;

public class RequestDetailsActivity extends Activity{
	private Bundle extras;
	private TextView mTextViewRequestNo, mTextViewPriorityType, mTextViewCategory,mTextViewDetails;
	private ImageButton mButtonClose,mButtonSendMessage;
	private EditText mEditTextMessage;
	private String mRequestNumber;
	private RequestDetails mRequestDetails;
	private MessageListAdapter mMessageListAdapter;
	private ListView mListViewMessages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_details);		
/*		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;*/
		initializeUI();
		mRequestDetails = getRequestDataFromDB(mRequestNumber);
		setUIData();
		addListeners();
		registerReceiver(mHandleMessageReceived, new IntentFilter(
				Param.DISPLAY_MESSAGE_ACTION));
	}
	
	private void initializeUI(){
		extras = getIntent().getExtras();
		mRequestNumber = extras.getString("requestno");
		mTextViewRequestNo = (TextView) findViewById(R.id.textViewRequestNumber);
		mTextViewPriorityType = (TextView) findViewById(R.id.textViewPriorityType);
		mTextViewCategory = (TextView) findViewById(R.id.textViewCategory);
		mTextViewDetails = (TextView) findViewById(R.id.textViewDetails);
		mButtonClose = (ImageButton) findViewById(R.id.buttonClose);
		mEditTextMessage = (EditText) findViewById(R.id.editTextMessage);
		mButtonSendMessage = (ImageButton) findViewById(R.id.imageButtonSendMessage);
		mListViewMessages = (ListView) findViewById(R.id.listViewMessages);
	}
	
	private void addListeners(){
		mButtonClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setallMessagesRead();
				finish();
			}
		});
		
		mButtonSendMessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				sendMessage();
			}
		});
	}
	
	private RequestDetails getRequestDataFromDB(String requestNumber){
		SmartFlatDBManager objDbManager = new SmartFlatDBManager();
		RequestDetails tempRequestDetails = new RequestDetails();
		Cursor requestDetailsCursor = objDbManager.getSinbleRequestDetails(requestNumber);
			boolean isdata = requestDetailsCursor.moveToFirst();
			if(isdata)
			{
				tempRequestDetails.setmRequestNumber(requestDetailsCursor.getString(requestDetailsCursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER)));
				tempRequestDetails.setmRequestCategory(requestDetailsCursor.getString(requestDetailsCursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_CATEGORY)));
				tempRequestDetails.setmRequestDetails(requestDetailsCursor.getString(requestDetailsCursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DETAILS)));
				tempRequestDetails.setmRequestPriority(requestDetailsCursor.getString(requestDetailsCursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_PRIORITY)));
				tempRequestDetails.setmRequestType(requestDetailsCursor.getString(requestDetailsCursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_TYPE)));
				tempRequestDetails.setmRequestStatus(requestDetailsCursor.getString(requestDetailsCursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS)));
				tempRequestDetails.setmRequestDateTime(requestDetailsCursor.getString(requestDetailsCursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME)));				
				tempRequestDetails.setmMessageList(getMessagesFromDB());
			}			
			return tempRequestDetails;	
	}

	@Override
	public void onBackPressed() {
		
	}
	
	private void setUIData(){
		mTextViewRequestNo.setText(mRequestDetails.getmRequestNumber());
		mTextViewCategory.setText("Category :- "+mRequestDetails.getmRequestCategory());
		mTextViewDetails.setText("Details :- "+mRequestDetails.getmRequestDetails());
		//mTextViewManagementReply.setText(mRequestDetails.getm);
		if(mRequestDetails.getmRequestPriority().equalsIgnoreCase("1")){
			mTextViewPriorityType.setTextColor(Color.RED);
			mTextViewPriorityType.setText("High");	
		}else if(mRequestDetails.getmRequestPriority().equalsIgnoreCase("2")){
			mTextViewPriorityType.setTextColor(Color.BLUE);
			mTextViewPriorityType.setText("Medium");	
		}else{
			mTextViewPriorityType.setTextColor(Color.GREEN);
			mTextViewPriorityType.setText("Low");	
		}
		
		showUpdatedDataInList();		
	}
	
	private void sendMessage(){
		
		if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable()) 
		{
			new SendMessageTask(getApplicationContext(), new SendMessageTaskListener(), mEditTextMessage.getText().toString(), mRequestNumber)
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			Utilities.ShowAlertBox(RequestDetailsActivity.this,"Error", "Please check your Internet");
		}	
	
	}
	
	public class SendMessageTaskListener implements AsyncTaskCompleteListener<Response> {

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(RequestDetailsActivity.this, "", false);		
		}

		@Override
		public void onTaskComplete(Response result) {
			if (result != null) 
			{
				if (result.getStatus().equalsIgnoreCase("success")) 
				{
					saveMessageInDB(result.getMessage());
				//	showUpdatedDataInList();
				//	mEditTextMessage.setText("");
				}else{
					Utilities.ShowAlertBox(RequestDetailsActivity.this,"Error",result.getMessage());		
				}
			}	
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();	
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			Utilities.ShowAlertBox(RequestDetailsActivity.this,"Error",e.getMessage());		
			CustomProgressDialog.removeDialog();	
		}
		
	}
	
	private void saveMessageInDB(String messageNumber)
	{
		RequestMessages tempMessages = new RequestMessages();
		tempMessages.setmMessageNumber(messageNumber);
		tempMessages.setmMessageContent(mEditTextMessage.getText().toString());
		tempMessages.setmRequestNumber(mRequestNumber);
		tempMessages.setmIsSocietyMessage(false);
		tempMessages.setmIsRead(true);
		tempMessages.setmMessageDateTime(Utilities.getCurrentDateTime());
		
		SmartFlatDBManager objDbManager = new SmartFlatDBManager();
		boolean isAdded = objDbManager.saveMessage(tempMessages);
		if (isAdded) {
			Log.e("Request Message", "Inserted Successfully");
		}
		
		mRequestDetails.getmMessageList().add(tempMessages);
		mMessageListAdapter.notifyDataSetChanged();
		mListViewMessages.setSelection(mMessageListAdapter.getCount() - 1);
		mEditTextMessage.setText("");
	}
	
	private List<RequestMessages> getMessagesFromDB(){
		List<RequestMessages> messageList = new ArrayList<RequestMessages>();
		SmartFlatDBManager objDbManager = new SmartFlatDBManager();
		Cursor messageCursor = objDbManager.getMessages(mRequestNumber);
		for(int i = 0; i<=messageCursor.getCount();i++)
		{
			boolean isdata = messageCursor.moveToPosition(i);
			if(isdata)	
			{
				RequestMessages tempMessages = new RequestMessages();
				tempMessages.setmMessageContent(messageCursor.getString(messageCursor.getColumnIndex(TableMessageDetails.MESSAGE_CONTENT)));
				tempMessages.setmMessageDateTime(messageCursor.getString(messageCursor.getColumnIndex(TableMessageDetails.MESSAGE_DATETIME)));
				if(messageCursor.getString(messageCursor.getColumnIndex(TableMessageDetails.IS_SOCIETY_MESSAGE)).equals("0")){
					tempMessages.setmIsSocietyMessage(false);
				}else{
					tempMessages.setmIsSocietyMessage(true);	
				}
				messageList.add(tempMessages);
			}
		}
		return messageList;

	}
	
	private void showUpdatedDataInList(){
		mMessageListAdapter = new MessageListAdapter(getApplicationContext(), mRequestDetails.getmMessageList());
		mListViewMessages.setAdapter(mMessageListAdapter);
		mListViewMessages.setSelection(mMessageListAdapter.getCount() - 1);
	}
	
	private final BroadcastReceiver mHandleMessageReceived = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent!=null)
			{
				String receiveMessage1 = intent.getStringExtra("Message");
				Log.e("Message1", receiveMessage1);			
				mRequestDetails.setmMessageList(getMessagesFromDB());
				mMessageListAdapter = new MessageListAdapter(getApplicationContext(), mRequestDetails.getmMessageList());
				mListViewMessages.setAdapter(mMessageListAdapter);
				mListViewMessages.setSelection(mMessageListAdapter.getCount() - 1);
			}
			
		}
	};
	
	protected void onDestroy() {
		try {
			unregisterReceiver(mHandleMessageReceived);
		} catch (Exception e) {
			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
	}
	
	private void setallMessagesRead()
	{
		SmartFlatDBManager objDbManager = new SmartFlatDBManager();
		objDbManager.setMessagesRead(mRequestNumber);
	}
}
