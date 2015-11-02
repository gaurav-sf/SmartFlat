package com.grs.product.smartflat.error;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class SmartFlatError extends Exception {

	
	private static final long serialVersionUID = 1L;
	public String errorMessage;
	public String errorType;

	public SmartFlatError(String errorMessage, String errorType) 
	{
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorType = errorType;
		Log.e("ErrorHandler", this.errorMessage);
	}
	public SmartFlatError(String errorMessage, String errorType, String error) 
	{
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorType = errorType;
		Log.e("ErrorHandler", error);
	}
	public SmartFlatError(String message) 
	{
		super(message);
		try 
		{
			JSONObject objectJSON = new JSONObject(message);
			String jsonMessage = "";
			if (!objectJSON.isNull("error")) 
			{
				jsonMessage = objectJSON.getString("error");
				JSONObject messageJSON = new JSONObject(jsonMessage);
				if (!messageJSON.isNull("code"))
					this.errorType = messageJSON.get("code").toString();
				if (!messageJSON.isNull("reason"))
					//this.errorMessage = messageJSON.get("reason").toString();
				this.errorMessage = "Currently application is not responding , please try again.";

				Log.e("ErrorHandler", this.errorMessage);

			}

		} 
		catch (JSONException e) 
		{
			Log.e("ErrorHandler JSONException", e.toString());
		}

	}

	public String toString() 
	{
		return errorMessage;
	}

}
