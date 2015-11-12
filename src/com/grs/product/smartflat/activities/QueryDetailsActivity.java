package com.grs.product.smartflat.activities;

import com.grs.product.smartflat.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class QueryDetailsActivity extends Activity {
	private Bundle extras;
	private TextView mTextViewQueryNo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_details);
		extras = getIntent().getExtras();
		String queryno = extras.getString("queryno");
		mTextViewQueryNo = (TextView) findViewById(R.id.textViewQueryNumber);
		mTextViewQueryNo.setText(queryno);
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
