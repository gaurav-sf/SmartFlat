package com.grs.product.smartflat.adapter;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.QueryDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RaisedQueryListAdapter extends BaseAdapter {


	Context mContext;
	List<QueryDetails> listQueryDetails = new ArrayList<QueryDetails>();
	private TextView textViewQueryNumber, textViewQueryDate;
	
	public RaisedQueryListAdapter(Context context, List<QueryDetails> listQueryDetails) {
		mContext = context;
		this.listQueryDetails = listQueryDetails;
	}
	

	@Override
	public int getCount() {	
		return listQueryDetails.size();
	}

	@Override
	public Object getItem(int position) {	
		return listQueryDetails.get(position);
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
		QueryDetails tempQueryDetails = listQueryDetails.get(position);
		textViewQueryNumber = (TextView) rowView.findViewById(R.id.textView1);
		textViewQueryNumber.setText(tempQueryDetails.getmQueryNumber());
		textViewQueryDate = (TextView) rowView.findViewById(R.id.textView2);
		textViewQueryDate.setText(tempQueryDetails.getmQueryDateTime());
		return rowView;
	}





}
