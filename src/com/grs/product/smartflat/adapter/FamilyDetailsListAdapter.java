package com.grs.product.smartflat.adapter;


import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.FamilyDetails;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class FamilyDetailsListAdapter extends BaseExpandableListAdapter {

	private Context mContext;
	private List<FamilyDetails> listFamilyDetails;
	
	public FamilyDetailsListAdapter(Context mContext, List<FamilyDetails> listFamilyDetails) {
		this.mContext = mContext;
		this.listFamilyDetails = listFamilyDetails;
	}


	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return listFamilyDetails.get(groupPosition);
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
		FamilyDetails temp = listFamilyDetails.get(groupPosition);
		TextView textViewName = (TextView) convertView.findViewById(R.id.textViewName);
		textViewName.setText(temp.getmFamilyMemberName());
		TextView textViewRelation = (TextView) convertView.findViewById(R.id.textViewRelation);
		textViewRelation.setText(temp.getmFamilyMemberRelation());
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
		return listFamilyDetails.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return listFamilyDetails.size();
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

		FamilyDetails temp = listFamilyDetails.get(groupPosition);
		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.textViewFMName);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(temp.getmFamilyMemberName());

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
