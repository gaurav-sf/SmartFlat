package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.activities.QueryDetailsActivity;
import com.grs.product.smartflat.adapter.RaisedQueryListAdapter;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerQueryDetails;
import com.grs.product.smartflat.models.QueryDetails;

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
import android.widget.ListView;
import android.widget.TextView;

public class ClosedQueryFragment extends Fragment {

	private ListView listViewQueryDetails;
	private TextView textViewMessage;
	private List<QueryDetails> listQueryDetails;
	private RaisedQueryListAdapter mQueryListAdapter;
 	
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
		listViewQueryDetails = (ListView) rootview.findViewById(R.id.listViewRaisedComplaints);
		textViewMessage = (TextView) rootview.findViewById(R.id.textView1);
		listQueryDetails = new ArrayList<QueryDetails>();
		createQueryList();
		mQueryListAdapter = new RaisedQueryListAdapter(getActivity(), listQueryDetails);
		listViewQueryDetails.setAdapter(mQueryListAdapter);
	}
	
	private void createQueryList(){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getClosedQueryDetails();
		if(cursor.getCount()==0){
			textViewMessage.setVisibility(View.VISIBLE);
			listViewQueryDetails.setVisibility(View.GONE);
			textViewMessage.setText("No Query to display");
		}else{
			for(int i = 0; i<=cursor.getCount();i++){
				boolean isdata = cursor.moveToPosition(i);
				if(isdata)
				{
					QueryDetails tempQueryDetails = new QueryDetails();
					tempQueryDetails.setmQueryNumber(cursor.getString(cursor.getColumnIndex(TableFlatOwnerQueryDetails.QUERY_NUMBER)));
					tempQueryDetails.setmQueryDateTime(cursor.getString(cursor.getColumnIndex(TableFlatOwnerQueryDetails.QUERY_DATETIME)));
					listQueryDetails.add(tempQueryDetails);
				}
				
			}
		}
	}
	
	private void addListener(){
	listViewQueryDetails.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Intent singleRequestDetails = new Intent(getActivity(), QueryDetailsActivity.class);
			singleRequestDetails.putExtra("queryno", listQueryDetails.get(position).getmQueryNumber());
			startActivity(singleRequestDetails);
			
		}
	});
	}
}
