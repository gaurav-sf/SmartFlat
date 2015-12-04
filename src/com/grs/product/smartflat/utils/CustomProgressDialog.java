package com.grs.product.smartflat.utils;

import com.grs.product.smartflat.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CustomProgressDialog
{
	private static Dialog mDialog = null;

	public CustomProgressDialog() 
	{
		super();
	}

	public static void showProgressDialog(Context mContext, String text, boolean cancellable) 
	{
		removeDialog();
		mDialog = new Dialog(mContext,	android.R.style.Theme_Translucent_NoTitleBar);
		LayoutInflater mInflater = LayoutInflater.from(mContext);
		View layout = mInflater.inflate(R.layout.progress_dialog, null);
		mDialog.setContentView(layout);
		TextView tvProgressDescription = (TextView) layout.findViewById(R.id.progressdescription);
		if (text.equals(""))
		{
			tvProgressDescription.setVisibility(View.GONE);
		}
		else
		{
			tvProgressDescription.setText(text);
		}
		
		if (mDialog != null) 
		{
			try {
				mDialog.show();
				mDialog.setCancelable(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static synchronized void  removeDialog()
	{
		if (mDialog != null) 
		{
			mDialog.dismiss();
			mDialog = null;
		}

	}
}