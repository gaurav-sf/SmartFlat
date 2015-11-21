package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.activities.RequestDetailsActivity;
import com.grs.product.smartflat.adapter.RaisedRequestListAdapter;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerRequestDetails;
import com.grs.product.smartflat.models.RequestDetails;

public class RaisedRequestFragment extends Fragment {

	private ListView listViewRequestDetails;
	private TextView textViewMessage;
	private List<RequestDetails> listRequestDetails;
	private RaisedRequestListAdapter mRequestListAdapter;
	private Spinner mSpinnertSorting;
	SmartFlatDBManager objManager = new SmartFlatDBManager();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_raised_complaint, container, false);
		initializeUI(rootView);
		addListener();
		return rootView;
	}

	private void initializeUI(View rootview){
		listViewRequestDetails = (ListView) rootview.findViewById(R.id.listViewRaisedComplaints);
		textViewMessage = (TextView) rootview.findViewById(R.id.textView1);
		listRequestDetails = new ArrayList<RequestDetails>();
		createRequestComplaintsList();
		mRequestListAdapter = new RaisedRequestListAdapter(getActivity(), listRequestDetails);
		listViewRequestDetails.setAdapter(mRequestListAdapter);
		mSpinnertSorting = (Spinner) rootview.findViewById(R.id.spinnertSorting);
		createSortingSpinnerData();
	}

	private void createSortingSpinnerData(){
		List<String> listRequestType = new ArrayList<String>();
		listRequestType.add("By Date");
		listRequestType.add("By Type");
		listRequestType.add("By Category (A-Z)");
		listRequestType.add("By Priority High to Low");
		listRequestType.add("By Priority Low to High");
		ArrayAdapter<String> adapterBuildingName = new ArrayAdapter<String>
		(getActivity(), android.R.layout.simple_dropdown_item_1line, listRequestType);
		adapterBuildingName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		mSpinnertSorting.setAdapter(adapterBuildingName);

	}

	private void createRequestComplaintsList(){
		Cursor cursor = objManager.getRaisedRequestDetails();
		if(cursor.getCount()==0){
			textViewMessage.setVisibility(View.VISIBLE);
			listViewRequestDetails.setVisibility(View.GONE);
			mSpinnertSorting.setVisibility(View.GONE);
			textViewMessage.setText("No Request to display");
		}else{
			for(int i = 0; i<=cursor.getCount();i++){
				boolean isdata = cursor.moveToPosition(i);
				if(isdata)
				{
					RequestDetails tempComplaintDetails = new RequestDetails();
					tempComplaintDetails.setmRequestNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER)));
					tempComplaintDetails.setmRequestDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME)));
					listRequestDetails.add(tempComplaintDetails);
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

		mSpinnertSorting.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				//Sort By date
				case 0:
					sortListByDate();
					break;

					//By Type
				case 1: 
					sortListByType();
					break;

					//By Category
				case 2:
					sortListByCategory();
					break;

					//By Priority High to Low
				case 3:
					sortListByPriorityHtoL();
					break;

					//By Priority Low to High
				case 4:
					sortListByPriorityLtoH();
					break;

				default:
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void sortListByDate(){
		Cursor cursor = objManager.getRaisedRequestDetails();
		listRequestDetails.clear();
		for(int i = 0; i<=cursor.getCount();i++){
			boolean isdata = cursor.moveToPosition(i);
			if(isdata)
			{
				RequestDetails tempComplaintDetails = new RequestDetails();
				tempComplaintDetails.setmRequestNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER)));
				tempComplaintDetails.setmRequestDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME)));
				listRequestDetails.add(tempComplaintDetails);
			}
		}
		mRequestListAdapter.notifyDataSetChanged();
	}

	private void sortListByType(){
		Cursor cursor = objManager.getRaisedRequestDetailsByType();
		listRequestDetails.clear();
		for(int i = 0; i<=cursor.getCount();i++){
			boolean isdata = cursor.moveToPosition(i);
			if(isdata)
			{
				RequestDetails tempComplaintDetails = new RequestDetails();
				tempComplaintDetails.setmRequestNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER)));
				tempComplaintDetails.setmRequestDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME)));
				listRequestDetails.add(tempComplaintDetails);
			}	
		}	
		mRequestListAdapter.notifyDataSetChanged();
	}

	private void sortListByCategory(){
		Cursor cursor = objManager.getRaisedRequestDetailsByCategory();
		listRequestDetails.clear();
		for(int i = 0; i<=cursor.getCount();i++){
			boolean isdata = cursor.moveToPosition(i);
			if(isdata)
			{
				RequestDetails tempComplaintDetails = new RequestDetails();
				tempComplaintDetails.setmRequestNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER)));
				tempComplaintDetails.setmRequestDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME)));
				listRequestDetails.add(tempComplaintDetails);
			}	
		}
		mRequestListAdapter.notifyDataSetChanged();
	}

	private void sortListByPriorityHtoL(){
		Cursor cursor = objManager.getRaisedRequestDetailsByPriorityHtoL();
		listRequestDetails.clear();
		for(int i = 0; i<=cursor.getCount();i++){
			boolean isdata = cursor.moveToPosition(i);
			if(isdata)
			{
				RequestDetails tempComplaintDetails = new RequestDetails();
				tempComplaintDetails.setmRequestNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER)));
				tempComplaintDetails.setmRequestDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME)));
				listRequestDetails.add(tempComplaintDetails);
			}	
		}	
		mRequestListAdapter.notifyDataSetChanged();
	}

	private void sortListByPriorityLtoH(){
		Cursor cursor = objManager.getRaisedRequestDetailsByPriorityLtoH();
		listRequestDetails.clear();
		for(int i = 0; i<=cursor.getCount();i++){
			boolean isdata = cursor.moveToPosition(i);
			if(isdata)
			{
				RequestDetails tempComplaintDetails = new RequestDetails();
				tempComplaintDetails.setmRequestNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER)));
				tempComplaintDetails.setmRequestDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME)));
				listRequestDetails.add(tempComplaintDetails);
			}	
		}		
		mRequestListAdapter.notifyDataSetChanged();
	}

}