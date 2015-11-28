package com.grs.product.smartflat.adapter;

import java.util.ArrayList;
import java.util.List;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.RequestDetails;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RaisedRequestListAdapter extends BaseAdapter {

	Context mContext;
	List<RequestDetails> listRequestDetails = new ArrayList<RequestDetails>();
	
	public RaisedRequestListAdapter(Context context, List<RequestDetails> listRequestDetails) {
		mContext = context;
		this.listRequestDetails = listRequestDetails;
	}
	

	@Override
	public int getCount() {	
		return listRequestDetails.size();
	}

	@Override
	public Object getItem(int position) {	
		return listRequestDetails.get(position);
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
		RequestDetails tempRequestDetails = listRequestDetails.get(position);
		TextView textViewRequestNumber = (TextView) rowView.findViewById(R.id.textViewRequestNumber);
		textViewRequestNumber.setText(tempRequestDetails.getmRequestNumber());
		TextView textViewRequestDate = (TextView) rowView.findViewById(R.id.textViewDatetime);
		textViewRequestDate.setText(tempRequestDetails.getmRequestDateTime());
		TextView textViewDetails = (TextView) rowView.findViewById(R.id.textViewDetails);
		textViewDetails.setText("Details :- "+tempRequestDetails.getmRequestDetails());
		TextView textViewCategory = (TextView) rowView.findViewById(R.id.textViewCategory);
		textViewCategory.setText("Category :- "+tempRequestDetails.getmRequestCategory());
		TextView textViewPriority = (TextView) rowView.findViewById(R.id.textViewPriority);
		if(tempRequestDetails.getmRequestPriority().equalsIgnoreCase("1")){
			textViewPriority.setTextColor(Color.RED);
			textViewPriority.setText("High");	
		}else if(tempRequestDetails.getmRequestPriority().equalsIgnoreCase("2")){
			textViewPriority.setTextColor(Color.BLUE);
			textViewPriority.setText("Medium");	
		}else{
			textViewPriority.setTextColor(Color.GREEN);
			textViewPriority.setText("Low");	
		}
		return rowView;
	}



}
