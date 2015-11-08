package com.grs.product.smartflat.adapter;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.ComplaintDetails;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RaisedComplaintListAdapter extends BaseAdapter {
	Context mContext;
	List<ComplaintDetails> listComplaintDetails = new ArrayList<ComplaintDetails>();
	private TextView textViewComplaintNumber, textViewComplaintDate;
	
	public RaisedComplaintListAdapter(Context context, List<ComplaintDetails> listComplaintDetails) {
		mContext = context;
		this.listComplaintDetails = listComplaintDetails;
	}
	

	@Override
	public int getCount() {	
		return listComplaintDetails.size();
	}

	@Override
	public Object getItem(int position) {	
		return listComplaintDetails.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater infalInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = infalInflater.inflate(R.layout.raised_complaint_list_item, null);
		}
		ComplaintDetails tempComplaintDetails = listComplaintDetails.get(position);
		textViewComplaintNumber = (TextView) rowView.findViewById(R.id.textView1);
		textViewComplaintNumber.setText(tempComplaintDetails.getmComplaintNumber());
		textViewComplaintDate = (TextView) rowView.findViewById(R.id.textView2);
		textViewComplaintDate.setText(tempComplaintDetails.getmComplaintRaisedDateTime());
		return rowView;
	}

}
