package com.grs.product.smartflat.adapter;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.ContactDetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactsListAdapter   extends BaseAdapter {
	private Context context;
	private List<ContactDetails> listContactDetails  = new ArrayList<ContactDetails>();
	
	public ContactsListAdapter(Context context,
			List<ContactDetails> listFlatOwnerDetails) {
		super();
		this.context = context;
		this.listContactDetails = listFlatOwnerDetails;
	}
	

	@Override
	public int getCount() {
		return listContactDetails.size();
	}

	@Override
	public Object getItem(int position) {
		return listContactDetails.get(position);
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
			rowView = infalInflater.inflate(R.layout.contact_details_list_item, null);
		}
		ContactDetails temp = listContactDetails.get(position);
		 TextView contactName = (TextView) rowView.findViewById(R.id.textViewContactName);
		 contactName.setText(temp.getmContactName());
		 
		 TextView contactEmailID = (TextView) rowView.findViewById(R.id.textViewContactEmailId);
		 if (temp.getmContactEmailId().equals("")) {
			 contactEmailID.setVisibility(View.GONE);
		}else{
			 contactEmailID.setText(temp.getmContactEmailId());
		}

		 TextView contactOccupation = (TextView) rowView.findViewById(R.id.textViewOccupation);
		 contactOccupation.setText(temp.getmContactOccupation());

		 final TextView contactNumber = (TextView) rowView.findViewById(R.id.textViewContactNumber);
		 contactNumber.setText(temp.getmContactNumber());
		 contactNumber.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_CALL);

				intent.setData(Uri.parse("tel:" + contactNumber.getText()));
				context.startActivity(intent);
			}
		});
		return rowView;
	}
	
}
