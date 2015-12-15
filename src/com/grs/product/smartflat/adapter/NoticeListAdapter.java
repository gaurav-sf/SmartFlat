package com.grs.product.smartflat.adapter;

import java.util.ArrayList;
import java.util.List;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.NoticeDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoticeListAdapter   extends BaseAdapter {
	private Context context;
	private List<NoticeDetails> listNoticeDetails  = new ArrayList<NoticeDetails>();
	
	public NoticeListAdapter(Context context,
			List<NoticeDetails> listFlatOwnerDetails) {
		super();
		this.context = context;
		this.listNoticeDetails = listFlatOwnerDetails;
	}
	

	@Override
	public int getCount() {
		return listNoticeDetails.size();
	}

	@Override
	public Object getItem(int position) {
		return listNoticeDetails.get(position);
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
			rowView = infalInflater.inflate(R.layout.notice_list_item, null);
		}
		NoticeDetails temp = listNoticeDetails.get(position);		 
		 TextView noticeNo = (TextView) rowView.findViewById(R.id.textViewNoticeNo);
		 noticeNo.setText(temp.getmNoticeNumber());
		// TextView noticeRecipient = (TextView) rowView.findViewById(R.id.textViewRecipient);
		 //noticeRecipient.setText(temp.getmNoticeTo());
		 TextView noticeSubject = (TextView) rowView.findViewById(R.id.textViewSubject);
		 noticeSubject.setText(temp.getmNoticeSubject());
		 TextView noticeDateTime = (TextView) rowView.findViewById(R.id.textViewDatetime);
		 noticeDateTime.setText(temp.getmNoticeDateTime());

		return rowView;
	}
	
}
