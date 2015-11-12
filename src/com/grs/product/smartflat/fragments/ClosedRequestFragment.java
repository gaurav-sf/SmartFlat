package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.activities.ComplaintDetailsActivity;
import com.grs.product.smartflat.activities.RequestDetailsActivity;
import com.grs.product.smartflat.adapter.RaisedComplaintListAdapter;
import com.grs.product.smartflat.adapter.RaisedRequestListAdapter;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerComplaintDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerRequestDetails;
import com.grs.product.smartflat.models.ComplaintDetails;
import com.grs.product.smartflat.models.RequestDetails;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ClosedRequestFragment extends Fragment {

	private ListView listViewRequestDetails;
	private TextView textViewMessage;
	private List<RequestDetails> listRequestDetails;
	private RaisedRequestListAdapter mRequestListAdapter;
 	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_closed_complaint, container, false);
		initializeUI(rootView);
		addListener();
		return rootView;
	}
	
	private void initializeUI(View rootview){
		listViewRequestDetails = (ListView) rootview.findViewById(R.id.listViewClosedComplaints);
		textViewMessage = (TextView) rootview.findViewById(R.id.textView1);
		listRequestDetails = new ArrayList<RequestDetails>();
		createComplaintsList();
		mRequestListAdapter = new RaisedRequestListAdapter(getActivity(), listRequestDetails);
		listViewRequestDetails.setAdapter(mRequestListAdapter);
	}
	
	private void createComplaintsList(){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getClosedRequestDetails();
		if(cursor.getCount()==0){
			textViewMessage.setVisibility(View.VISIBLE);
			listViewRequestDetails.setVisibility(View.GONE);
			textViewMessage.setText("NO Request to display");
		}else{
			for(int i = 0; i<=cursor.getCount();i++){
				boolean isdata = cursor.moveToPosition(i);
				if(isdata)
				{
					RequestDetails tempRequestDetails = new RequestDetails();
					tempRequestDetails.setmRequestNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_NUMBER)));
					tempRequestDetails.setmRequestDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_DATETIME)));
					listRequestDetails.add(tempRequestDetails);
				}
				
			}
		}
	}
	
	private void addListener(){
	listViewRequestDetails.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Intent singleRequestDetails = new Intent(getActivity(), RequestDetailsActivity.class);
			singleRequestDetails.putExtra("requestno", listRequestDetails.get(position).getmRequestNumber());
			startActivity(singleRequestDetails);
			
		}
	});
	}
}
