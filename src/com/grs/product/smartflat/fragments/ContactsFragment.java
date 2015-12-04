package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.adapter.ContactsListAdapter;
import com.grs.product.smartflat.apicall.AsyncTaskCompleteListener;
import com.grs.product.smartflat.asynctasks.GetContactsTask;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableContactDetails;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.ContactDetails;
import com.grs.product.smartflat.utils.CustomProgressDialog;
import com.grs.product.smartflat.utils.NetworkDetector;
import com.grs.product.smartflat.utils.Utilities;
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

public class ContactsFragment extends Fragment {
	
	private List<ContactDetails> mContactList;
	private ListView mListViewContacts;
	private ContactsListAdapter mContactsListAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
		initialiseUI(rootView);
		getContactsFromServer();
		addListener();
        return rootView;	
        }
	
	private void initialiseUI(View rootView){
		mContactList = new ArrayList<ContactDetails>();
		mListViewContacts = (ListView) rootView.findViewById(R.id.listViewContacts);
	}
	
	private void addListener(){
		
		
	}
	
	private void getContactsFromServer(){
		
		if (NetworkDetector.init(getActivity()).isNetworkAvailable()) 
		{
			new GetContactsTask(getActivity(), new GetContactsTaskListener())
			.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} 
		else 
		{
			showDataInListView();
		}					
	
	}
	
	public class GetContactsTaskListener implements AsyncTaskCompleteListener<List<ContactDetails>>{

		@Override
		public void onStarted() {
			CustomProgressDialog.showProgressDialog(getActivity(), "", false);
		}

		@Override
		public void onTaskComplete(List<ContactDetails> result) {
			if (result!=null) {
				saveContactInDB(result);
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
			Utilities.ShowAlertBox(getActivity(), "Error", e.getMessage());
		}
		
	}
	
	private void getContactsFromDB(){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		Cursor cursorDetails = dbManager.getAllContacts();
		if (cursorDetails!=null && cursorDetails.getCount()>0) 
		{
			for(int i = 0; i<=cursorDetails.getCount();i++)
			{
				boolean isdata = cursorDetails.moveToPosition(i);
				if(isdata)
				{
					ContactDetails temp = new ContactDetails();
					temp.setmContactNumber(cursorDetails.getString(cursorDetails.getColumnIndex(TableContactDetails.CONTACT_NAME)));
					temp.setmContactNumber(cursorDetails.getString(cursorDetails.getColumnIndex(TableContactDetails.CONTACT_NUMBER)));
					temp.setmContactEmailId(cursorDetails.getString(cursorDetails.getColumnIndex(TableContactDetails.CONTACT_EMAIL_ID)));
					temp.setmContactOccupation(cursorDetails.getString(cursorDetails.getColumnIndex(TableContactDetails.CONTACT_OCCUPATION)));
					mContactList.add(temp);
				}
			}
		}
	}
	
	private void saveContactInDB(List<ContactDetails> listContacts){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		for (int i = 0; i < listContacts.size(); i++) {
			boolean isAdded = dbManager.saveContact(listContacts.get(i));
			if (isAdded) {
				Log.e("Contact Details", "Inserted Successfully");
			}
		}
	}
	
	
	private void showDataInListView(){
		getContactsFromDB();
		mContactsListAdapter = new ContactsListAdapter(getActivity(), mContactList);
		mListViewContacts.setAdapter(mContactsListAdapter);	
	}
}
