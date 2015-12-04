package com.grs.product.smartflat.fragments;

import android.support.v4.app.Fragment;

public class ClosedComplaintFragment extends Fragment {/*
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
		View rootView = inflater.inflate(R.layout.fragment_closed_complaint, container, false);
		initializeUI(rootView);
		addListener();
		return rootView;
	}
	
	private void initializeUI(View rootview){
		listViewComplaintDetails = (ListView) rootview.findViewById(R.id.listViewClosedComplaints);
		textViewMessage = (TextView) rootview.findViewById(R.id.textView1);
		listComplaintDetails = new ArrayList<ComplaintDetails>();
		createComplaintsList();
		mComplaintListAdapter = new RaisedComplaintListAdapter(getActivity(), listComplaintDetails);
		listViewComplaintDetails.setAdapter(mComplaintListAdapter);
	}
	
	private void createComplaintsList(){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getClosedComplaintDetails();
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
	
	private void addListener(){
	listViewComplaintDetails.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Intent singleComplaintDetails = new Intent(getActivity(), ComplaintDetailsActivity.class);
			singleComplaintDetails.putExtra("complaintno", listComplaintDetails.get(position).getmComplaintNumber());
			startActivity(singleComplaintDetails);
			
		}
	});
	}




*/}
