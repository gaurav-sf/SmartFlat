package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.activities.NoticeDetailsActivity;
import com.grs.product.smartflat.adapter.NoticeListAdapter;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.GetNoticesAsynTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableSocietyNotices;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.NoticeDetails;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

public class NoticeFragment extends Fragment {	
	private List<NoticeDetails> mListNoticeDetails;
	private ListView mListViewNoticeDetails;
	private NoticeListAdapter mNoticeListAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
		initialiseUI(rootView);
		getNoticesFromServer();
		addListener();
        return rootView;	
        }
	
	private void initialiseUI(View rootView){
		mListViewNoticeDetails = (ListView) rootView.findViewById(R.id.listViewNotice);
		mListNoticeDetails = new ArrayList<NoticeDetails>();	
		//getNoticesFromDB();		
	}
	
	private void addListener(){
		mListViewNoticeDetails.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent noticeDetails = new Intent(getActivity(),NoticeDetailsActivity.class);
				noticeDetails.putExtra("noticeNumber", mListNoticeDetails.get(position).getmNoticeNumber());
				startActivity(noticeDetails);
			}
		});
		
	}
	
	private void getNoticesFromServer(){
		
		if (NetworkDetector.init(getActivity()).isNetworkAvailable()) 
		{
			new GetNoticesAsynTask(getActivity(), new GetNoticesAsynTaskListener())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			getNoticesFromDB();
		}						
	
	}
	
	public class GetNoticesAsynTaskListener implements AsyncTaskCompleteListener<List<NoticeDetails>>{

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(getActivity(), "", false);
		}

		@Override
		public void onTaskComplete(List<NoticeDetails> result) {
			if (result!=null) {
				saveNoticeInDB(result);
				getNoticesFromDB();	
			}else{
				getNoticesFromDB();			
			}	
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			CustomProgressDialog.removeDialog();
			getNoticesFromDB();
		}
		
	}
	
	private void getNoticesFromDB()
	{
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		Cursor cursorDetails = dbManager.getAllSocietyNoticeDetails();
		if (cursorDetails!=null && cursorDetails.getCount()>0)
		{
			for(int i = 0; i<=cursorDetails.getCount();i++)
			{
				boolean isdata = cursorDetails.moveToPosition(i);
				if(isdata)
				{
					NoticeDetails noticeDetails = new NoticeDetails();
					noticeDetails.setmNoticeNumber(cursorDetails.getString(cursorDetails.getColumnIndex(TableSocietyNotices.NOTICE_NUMBER)));
					noticeDetails.setmNoticeTo(cursorDetails.getString(cursorDetails.getColumnIndex(TableSocietyNotices.NOTICE_TO)));
					noticeDetails.setmNoticeSubject(cursorDetails.getString(cursorDetails.getColumnIndex(TableSocietyNotices.NOTICE_SUBJECT)));
					noticeDetails.setmNoticeMessage(cursorDetails.getString(cursorDetails.getColumnIndex(TableSocietyNotices.NOTICE_MESSAGE)));
					noticeDetails.setmNoticeDateTime(cursorDetails.getString(cursorDetails.getColumnIndex(TableSocietyNotices.NOTICE_DATETIME)));
					mListNoticeDetails.add(noticeDetails);
				}
			}
		}
		if(mListNoticeDetails.size()==0){
			Utilities.ShowAlertBox(getActivity(), "Message", "There is no notice to display");
		}else{
			mNoticeListAdapter = new NoticeListAdapter(getActivity(), mListNoticeDetails);
			mListViewNoticeDetails.setAdapter(mNoticeListAdapter);
		}
	}
	
	private void saveNoticeInDB(List<NoticeDetails> listNotices){

		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		for (int i = 0; i < listNotices.size(); i++) {
			boolean isAdded = dbManager.saveSocietyNoticeDetails(listNotices.get(i));
			if (isAdded) {
				Log.e("Visitor Details", "Inserted Successfully");
			}
		}
	
	}

}
