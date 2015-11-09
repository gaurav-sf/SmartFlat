package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.adapter.FamilyDetailsLIstAdapter;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerFamilyDetails;
import com.grs.product.smartflat.models.FamilyDetails;

public class FamilyDetailsFragment extends Fragment{
	private ExpandableListView mExpListViewFamilyDetails;
	private List<FamilyDetails> mListFamilyDetails = new ArrayList<FamilyDetails>();
	private FamilyDetailsLIstAdapter mFamilyDetailsLIstAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_family_details, container, false);
		initialiseUI(rootView);
		createDataForList();
		mFamilyDetailsLIstAdapter = new FamilyDetailsLIstAdapter(getActivity(), mListFamilyDetails);
		mExpListViewFamilyDetails.setAdapter(mFamilyDetailsLIstAdapter);
		mExpListViewFamilyDetails.setOnGroupExpandListener(new OnGroupExpandListener() {
			
			@Override
			public void onGroupExpand(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        return rootView;
	}
	
	private void initialiseUI(View rootView){
		mExpListViewFamilyDetails = (ExpandableListView) rootView.findViewById(R.id.expandaleListViewFamilyDetails);
		
	}
	
	private void createDataForList(){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		Cursor cursorFamilydetails = dbManager.getFamilyDetails();
		if(cursorFamilydetails.getCount() >0){
			for (int i = 0; i < cursorFamilydetails.getCount(); i++) {
				cursorFamilydetails.moveToPosition(i);
				FamilyDetails temp = new FamilyDetails();
				temp.setmFamilyMemberName(cursorFamilydetails.getString(cursorFamilydetails.getColumnIndex(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_NAME)));
				temp.setmFamilyMemberRelation(cursorFamilydetails.getString(cursorFamilydetails.getColumnIndex(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_RELATION)));
				temp.setmFamilyMemberDOB(cursorFamilydetails.getString(cursorFamilydetails.getColumnIndex(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_DOB)));
				temp.setmFamilyMemberAge(cursorFamilydetails.getString(cursorFamilydetails.getColumnIndex(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_AGE)));
				temp.setmFamilyMemberContactno(cursorFamilydetails.getString(cursorFamilydetails.getColumnIndex(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_CONTACT_NO)));			
				mListFamilyDetails.add(temp);
			}
		}
	}

}
