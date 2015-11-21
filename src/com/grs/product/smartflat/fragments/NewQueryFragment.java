package com.grs.product.smartflat.fragments;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerQueryDetails;
import com.grs.product.smartflat.models.QueryDetails;
import com.grs.product.smartflat.utils.Utilities;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewQueryFragment extends Fragment {
	private EditText mEditTextQueryDetails,mEditTextCopmType;
	private Button mButtonRaised;
	private TextView mTextViewTitle;
	
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
		mTextViewTitle = (TextView) rootview.findViewById(R.id.textView1);
		mTextViewTitle.setText("RAISE NEW QUERY");
		mEditTextQueryDetails = (EditText) rootview.findViewById(R.id.editTextComplaintTDetails);
		mEditTextQueryDetails.setHint("Query Details");
		mButtonRaised = (Button) rootview.findViewById(R.id.buttonRaise);
		mButtonRaised.setText("RAISE QUERY");
		mEditTextCopmType = (EditText) rootview.findViewById(R.id.editTextComplaintType);
		mEditTextCopmType.setVisibility(View.GONE);
	}
	
	private void addListener(){
		mButtonRaised.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(validateUI()){
					saveComplaintDetails();
					mEditTextQueryDetails.setText("");
				}
			}
		});
	}
	
	private boolean validateUI(){
		if(mEditTextQueryDetails.getText().toString().equals(""))
		{
			mEditTextQueryDetails.setError("Please enter details");
			return false;
		}
		return true;
	}
	
	private void saveComplaintDetails(){
		QueryDetails queryDetails = new QueryDetails();
		queryDetails.setmQueryDetails(mEditTextQueryDetails.getText().toString());
		queryDetails.setmQueryDateTime(Utilities.getCurrentDateTime());
		queryDetails.setmQueryNumber(getQueryNumber());
		queryDetails.setmQueryStatus("Raised");
		
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		boolean status = dbManager.saveQueryDetails(queryDetails);
		if(status){
			Log.e("Query", "Query Raised");
		}
	}
	
	private String getQueryNumber(){
		String queryNumber = "";
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getAllQueryDetails();
		if(cursor.getCount()==0){
			queryNumber = "QUE100";
		}else{
			cursor.moveToLast();
			String id = cursor.getString(cursor.getColumnIndex(TableFlatOwnerQueryDetails.QUERY_NUMBER));
			int nextid = Integer.parseInt(id.replace("QUE", ""))+1;
			queryNumber = "QUE"+nextid;		
		}
		
		return queryNumber;
	}



}
