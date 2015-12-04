package com.grs.product.smartflat.fragments;

import android.support.v4.app.Fragment;

public class NewComplaintFragment extends Fragment {/*
	private EditText mEditTextCopmType, mEditTextCopmDetails;
	private Button mButtonRaised;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_new_complaint, container, false);
		initialiseUI(rootView);
		addListener();

        return rootView;
	}
	
	private void initialiseUI(View rootview){	
		mEditTextCopmType = (EditText) rootview.findViewById(R.id.editTextComplaintType);
		mEditTextCopmDetails = (EditText) rootview.findViewById(R.id.editTextComplaintTDetails);
		mButtonRaised = (Button) rootview.findViewById(R.id.buttonRaise);		
	}
	
	private void addListener(){
		mButtonRaised.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(validateUI()){
					saveComplaintDetails();
					mEditTextCopmDetails.setText("");
					mEditTextCopmType.setText("");
				}
			}
		});
	}
	
	private boolean validateUI(){
		if(mEditTextCopmType.getText().toString().equals(""))
		{
			mEditTextCopmType.setError("Please enter complaint type");
			return false;
		}
		if(mEditTextCopmDetails.getText().toString().equals(""))
		{
			mEditTextCopmDetails.setError("Please enter details");
			return false;
		}
		return true;
	}
	
	private void saveComplaintDetails(){
		ComplaintDetails complaintDetails = new ComplaintDetails();
		complaintDetails.setmComplaintType(mEditTextCopmType.getText().toString());
		complaintDetails.setmComplaintDetails(mEditTextCopmDetails.getText().toString());
		complaintDetails.setmComplaintRaisedDateTime(Utilities.getCurrentDateTime());
		complaintDetails.setmComplaintNumber(getComplaintNumber());
		complaintDetails.setmComplaintStatus("Raised");
		
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		boolean status = dbManager.saveComplaintDetails(complaintDetails);
		if(status){
			Log.e("Complaint", "Complaint Raised");
		}
	}
	
	private String getComplaintNumber(){
		String compalintNumber = "";
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getAllComplaintDetails();
		if(cursor.getCount()==0){
			compalintNumber = "COMP100";
		}else{
			cursor.moveToLast();
			String id = cursor.getString(cursor.getColumnIndex(TableFlatOwnerComplaintDetails.COMPLAINT_NUMBER));
			int nextid = Integer.parseInt(id.replace("COMP", ""))+1;
			compalintNumber = "COMP"+nextid;		
		}
		
		return compalintNumber;
	}

*/}
