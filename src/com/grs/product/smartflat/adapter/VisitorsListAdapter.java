package com.grs.product.smartflat.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.VisitorDetails;

public class VisitorsListAdapter   extends BaseAdapter {
	private Context context;
	private List<VisitorDetails> listVisitorDetails  = new ArrayList<VisitorDetails>();
	
	public VisitorsListAdapter(Context context,
			List<VisitorDetails> listFlatOwnerDetails) {
		super();
		this.context = context;
		this.listVisitorDetails = listFlatOwnerDetails;
	}
	

	@Override
	public int getCount() {
		return listVisitorDetails.size();
	}

	@Override
	public Object getItem(int position) {
		return listVisitorDetails.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = infalInflater.inflate(R.layout.visitor_list_item, null);
		}
		 VisitorDetails temp = listVisitorDetails.get(position);
		 TextView visitorName = (TextView) rowView.findViewById(R.id.textViewVisitorName);
		 visitorName.setText(temp.getmVisitorName());
		 TextView visitorInTime = (TextView) rowView.findViewById(R.id.textViewVisitorInTime);
		 visitorInTime.setText(temp.getmVisitorInTime());

		return rowView;
	}
	
}
