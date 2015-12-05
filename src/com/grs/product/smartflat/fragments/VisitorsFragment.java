package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.adapter.VisitorsListAdapter;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.GetVisitorsTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableVisitorDetails;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.VisitorDetails;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;

public class VisitorsFragment  extends Fragment{

	private List<VisitorDetails> mListVisitorDetails;
	private ListView mListViewVisitorDetails;
	private VisitorsListAdapter mVisitorsListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_visitors, container, false);
		initialiseUI(rootView);
		getVisitors();
		addListener();
		return rootView;	
	}

	private void initialiseUI(View rootView){
		mListVisitorDetails = new ArrayList<VisitorDetails>();
		mListViewVisitorDetails = (ListView) rootView.findViewById(R.id.listVIewVisitorsDetails);
	}

	private void addListener(){

	}
	
	private void getVisitors(){	
		if (NetworkDetector.init(getActivity()).isNetworkAvailable()) 
		{
			new GetVisitorsTask(getActivity(), new GetVisitorsTaskListener())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			showDataInListView();
		}						
	}
	
	public class GetVisitorsTaskListener implements AsyncTaskCompleteListener<List<VisitorDetails>>{

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(getActivity(), "", false);
		}

		@Override
		public void onTaskComplete(List<VisitorDetails> result) {
			if (result!=null) {
				saveVisitorInLocalDB(result);
				showDataInListView();	
			}else{
				showDataInListView();			
			}	
		}

		@Override
		public void onStoped() {
			CustomProgressDialog.removeDialog();
		}

		@Override
		public void onStopedWithError(SmartFlatError e) {
			CustomProgressDialog.removeDialog();
			showDataInListView();
		}
		
	}
	
	private void saveVisitorInLocalDB(List<VisitorDetails> listVisitor){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		for (int i = 0; i < listVisitor.size(); i++) {
			boolean isAdded = dbManager.saveVisitor(listVisitor.get(i));
			if (isAdded) {
				Log.e("Visitor Details", "Inserted Successfully");
			}
		}
	}
	
	private void showDataInListView(){
		getVisitorsFromDB();
		if(mListVisitorDetails.size()==0){
			Utilities.ShowAlertBox(getActivity(), "Message", "No Visitor Details to show");
		}else{
			mVisitorsListAdapter = new VisitorsListAdapter(getActivity(), mListVisitorDetails);
			mListViewVisitorDetails.setAdapter(mVisitorsListAdapter);			
		}
	}
	
	private void getVisitorsFromDB(){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		Cursor cursorDetails = dbManager.getVisitors();
		if (cursorDetails!=null && cursorDetails.getCount()>0) 
		{
			for(int i = 0; i<=cursorDetails.getCount();i++)
			{
				boolean isdata = cursorDetails.moveToPosition(i);
				if(isdata)
				{
					VisitorDetails temp = new VisitorDetails();
					temp.setmVisitorName(cursorDetails.getString(cursorDetails.getColumnIndex(TableVisitorDetails.VISITOR_NAME)));
					temp.setmVisitorInTime(cursorDetails.getString(cursorDetails.getColumnIndex(TableVisitorDetails.VISITOR_IN_TIME)));
					mListVisitorDetails.add(temp);
				}
			}
		}
	}

}
