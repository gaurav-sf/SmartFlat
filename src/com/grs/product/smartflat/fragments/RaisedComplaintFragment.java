package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.adapter.RaisedComplaintListAdapter;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerComplaintDetails;
import com.grs.product.smartflat.models.ComplaintDetails;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class RaisedComplaintFragment extends Fragment {
	private ListView listViewComplaintDetails;
	private TextView textViewMessage;
	private List<ComplaintDetails> listComplaintDetails;
	private RaisedComplaintListAdapter mComplaintListAdapter;
 	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_raised_complaint, container, false);
		initializeUI(rootView);
		return rootView;
	}
	
	private void initializeUI(View rootview){
		listViewComplaintDetails = (ListView) rootview.findViewById(R.id.listViewRaisedComplaints);
		textViewMessage = (TextView) rootview.findViewById(R.id.textView1);
		listComplaintDetails = new ArrayList<ComplaintDetails>();
		createComplaintsList();
		mComplaintListAdapter = new RaisedComplaintListAdapter(getActivity(), listComplaintDetails);
		listViewComplaintDetails.setAdapter(mComplaintListAdapter);
	}
	
	private void createComplaintsList(){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getRaisedComplaintDetails();
		if(cursor.getCount()==0){
			textViewMessage.setVisibility(View.VISIBLE);
			listViewComplaintDetails.setVisibility(View.GONE);
			textViewMessage.setText("NO Complaints to display");
		}else{
			for(int i = 0; i<=cursor.getCount();i++){
				boolean isdata = cursor.moveToPosition(i);
				if(isdata)
				{
					ComplaintDetails tempComplaintDetails = new ComplaintDetails();
					tempComplaintDetails.setmComplaintNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerComplaintDetails.COMPLAINT_NUMBER)));
					tempComplaintDetails.setmComplaintRaisedDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerComplaintDetails.COMPLAINT_RAISED_DATETIME)));
					listComplaintDetails.add(tempComplaintDetails);
				}
				
			}
		}
	}


}
