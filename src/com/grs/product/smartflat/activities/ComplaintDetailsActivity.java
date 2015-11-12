package com.grs.product.smartflat.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.grs.product.smartflat.R;

public class ComplaintDetailsActivity extends Activity {
	private Bundle extras;
	private TextView mTextViewComplaintNo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complaint_details);
		extras = getIntent().getExtras();
		String complaintno = extras.getString("complaintno");
		mTextViewComplaintNo = (TextView) findViewById(R.id.textViewComplaintNumber);
		mTextViewComplaintNo.setText(complaintno);
		findViewById(R.id.buttonClose).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
	}
}
