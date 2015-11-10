package com.grs.product.smartflat.adapter;

import java.util.ArrayList;
import java.util.List;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.RequestDetails;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RaisedRequestListAdapter extends BaseAdapter {

	Context mContext;
	List<RequestDetails> listRequestDetails = new ArrayList<RequestDetails>();
	private TextView textViewRequestNumber, textViewRequestDate;
	
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
		textViewRequestNumber = (TextView) rowView.findViewById(R.id.textView1);
		textViewRequestNumber.setText(tempRequestDetails.getmRequestNumber());
		textViewRequestDate = (TextView) rowView.findViewById(R.id.textView2);
		textViewRequestDate.setText(tempRequestDetails.getmRequestDateTime());
		return rowView;
	}



}
