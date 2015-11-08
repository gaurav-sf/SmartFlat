package com.grs.product.smartflat.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerDetails;
import com.grs.product.smartflat.models.FlatOwnerDetails;

public class HomeFragment extends Fragment {
	
	private FlatOwnerDetails mFlatOwnerDetails = new FlatOwnerDetails();
	private TextView mTextViewUsername, mTextViewOwnername, mTextViewContactNo, mTextViewEmailId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		getUserLoginDetailsFromDB();
		initialiseUI(rootView);
		

        return rootView;
	}
	
	private void getUserLoginDetailsFromDB(){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		Cursor cursor = objManager.getFlatOwnerDetails();
		if(cursor!=null && cursor.getCount()>0){
			mFlatOwnerDetails.setmUsername(cursor.getString(cursor.getColumnIndex(TableFlatOwnerDetails.USERNAME)));
			mFlatOwnerDetails.setmFlatOwnerName(cursor.getString(cursor.getColumnIndex(TableFlatOwnerDetails.FLAT_OWNER_NAME)));
			mFlatOwnerDetails.setmFlatOwnerContactNo(cursor.getString(cursor.getColumnIndex(TableFlatOwnerDetails.FLAT_OWNER_CONTACT_NO)));
			mFlatOwnerDetails.setmFlatOwnerEmailId(cursor.getString(cursor.getColumnIndex(TableFlatOwnerDetails.FLAT_OWNER_EMAIL_ID)));
		}		
	}
	
	private void initialiseUI(View rootview){
		mTextViewUsername = (TextView) rootview.findViewById(R.id.textViewUsername);
		mTextViewUsername.setText(mFlatOwnerDetails.getmUsername());
		
		mTextViewOwnername = (TextView) rootview.findViewById(R.id.textViewOwnerName);
		mTextViewOwnername.setText("Welcome \n"+mFlatOwnerDetails.getmFlatOwnerName());
		
		mTextViewContactNo = (TextView) rootview.findViewById(R.id.textViewOwnerContactNo);
		mTextViewContactNo.setText("Contact No - "+mFlatOwnerDetails.getmFlatOwnerContactNo());
		
		mTextViewEmailId = (TextView) rootview.findViewById(R.id.textViewOwnerEmaidId);
		mTextViewEmailId.setText("Email id - " +mFlatOwnerDetails.getmFlatOwnerEmailId());			
	}

}
