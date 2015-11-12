package com.grs.product.smartflat.adapter;

import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.VehicleDetails;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class VehicleDetailsListAdapter extends BaseExpandableListAdapter {
	
	private Context mContext;
	private List<VehicleDetails> listVehicleDetails;
	
	public VehicleDetailsListAdapter(Context mContext, List<VehicleDetails> listVehicleDetails) {
		this.mContext = mContext;
		this.listVehicleDetails = listVehicleDetails;
	}
	
	


	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return listVehicleDetails.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.expandable_list_child_item_family_details, null);
		}
		VehicleDetails temp = listVehicleDetails.get(groupPosition);
		TextView textViewVehicleType = (TextView) convertView.findViewById(R.id.textViewName);
		textViewVehicleType.setText(temp.getmVehicleType());
		TextView textViewVehicleCompany = (TextView) convertView.findViewById(R.id.textViewRelation);
		textViewVehicleCompany.setText(temp.getmVehicleCompany()
				+"\n"+temp.getmVehicleModel()
				+"\n"+temp.getmVehicleColor());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return listVehicleDetails.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return listVehicleDetails.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.expandable_list_group_item, null);
		}

		VehicleDetails temp = listVehicleDetails.get(groupPosition);
		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.textViewFMName);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(temp.getmVehicleNumber());

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}


}
