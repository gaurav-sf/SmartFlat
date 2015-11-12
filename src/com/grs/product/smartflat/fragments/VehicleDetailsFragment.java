package com.grs.product.smartflat.fragments;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.adapter.FamilyDetailsListAdapter;
import com.grs.product.smartflat.adapter.VehicleDetailsListAdapter;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerFamilyDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerVehicleDetails;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.models.VehicleDetails;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class VehicleDetailsFragment extends Fragment {

	private ExpandableListView mExpListViewVehicleDetails;
	private List<VehicleDetails> mListVehicleDetails;
	private VehicleDetailsListAdapter mVehicleDetailsLIstAdapter;
	
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
		mVehicleDetailsLIstAdapter = new VehicleDetailsListAdapter(getActivity(), mListVehicleDetails);
		mExpListViewVehicleDetails.setAdapter(mVehicleDetailsLIstAdapter);
		mExpListViewVehicleDetails.setOnGroupExpandListener(new OnGroupExpandListener() {
			
			@Override
			public void onGroupExpand(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        return rootView;
	}
	
	private void initialiseUI(View rootView){
		mExpListViewVehicleDetails = (ExpandableListView) rootView.findViewById(R.id.expandaleListViewFamilyDetails);
		mListVehicleDetails = new ArrayList<VehicleDetails>();
	}
	
	private void createDataForList(){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		Cursor cursorVehicledetails = dbManager.getVehicleDetails();
		if(cursorVehicledetails.getCount() >0){
			for (int i = 0; i < cursorVehicledetails.getCount(); i++) {
				cursorVehicledetails.moveToPosition(i);
				VehicleDetails temp = new VehicleDetails();
				temp.setmVehicleType(cursorVehicledetails.getString(cursorVehicledetails.getColumnIndex(TableFlatOwnerVehicleDetails.VEHICLE_TYPE)));
				temp.setmVehicleNumber(cursorVehicledetails.getString(cursorVehicledetails.getColumnIndex(TableFlatOwnerVehicleDetails.VEHICLE_NUMBER)));
				temp.setmVehicleCompany(cursorVehicledetails.getString(cursorVehicledetails.getColumnIndex(TableFlatOwnerVehicleDetails.VEHICLE_COMPANY)));
				temp.setmVehicleModel(cursorVehicledetails.getString(cursorVehicledetails.getColumnIndex(TableFlatOwnerVehicleDetails.VEHICLE_MODEL)));
				temp.setmVehicleColor(cursorVehicledetails.getString(cursorVehicledetails.getColumnIndex(TableFlatOwnerVehicleDetails.VEHICLE_COLOR)));			
				mListVehicleDetails.add(temp);
			}
		}
	}



}
