package com.grs.product.smartflat.adapter;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.models.RequestMessages;
public class MessageListAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<RequestMessages> mListMessages;
	
	public MessageListAdapter(Context context,
			List<RequestMessages> listMessages) {
		this.mContext = context;
		this.mListMessages = listMessages;
	}

	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListMessages.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListMessages.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final RequestMessages singleMessage = mListMessages.get(position);
		final ViewHolder holder; 
		if(convertView == null)
		{
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.message_list_item, parent, false);
			holder.message_text = (TextView) convertView.findViewById(R.id.message_text);
			holder.messageLayout = (LinearLayout) convertView.findViewById(R.id.messageLayout);
			holder.message_time = (TextView) convertView.findViewById(R.id.message_time);
			
			convertView.setTag(holder);
		}else
			holder = (ViewHolder) convertView.getTag();
		String message = singleMessage.getmMessageContent();
		if (message.equals("")) {
			convertView.setVisibility(View.GONE);		
		}
		LayoutParams layoutParams = (LayoutParams) holder.messageLayout .getLayoutParams();
		if(singleMessage.ismIsSocietyMessage()){
			holder.message_text.setText(singleMessage.getmMessageContent());
			holder.message_time.setText(singleMessage.getmMessageDateTime());
			holder.messageLayout.setBackgroundResource(R.drawable.message_background_grey);
			layoutParams.gravity = Gravity.LEFT;
			layoutParams.setMargins(5, 5, 30, 0);			
		}else{
			holder.message_text.setText(singleMessage.getmMessageContent());
			holder.message_time.setText(singleMessage.getmMessageDateTime());
			holder.messageLayout.setBackgroundResource(R.drawable.message_background_green);
			layoutParams.gravity = Gravity.RIGHT;
			layoutParams.setMargins(30, 5, 5, 0);		
		}
		holder.messageLayout.setLayoutParams(layoutParams);
		
		return convertView;
	}
	
	private static class ViewHolder
	{
		TextView message_text;
		TextView message_time;
		LinearLayout messageLayout;
	}

}
