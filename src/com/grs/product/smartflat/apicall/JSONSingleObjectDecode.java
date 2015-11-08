package com.grs.product.smartflat.apicall;

import org.json.JSONException;
import org.json.JSONObject;

import com.grs.product.smartflat.response.Response;
public class JSONSingleObjectDecode {
	
	public JSONSingleObjectDecode() throws JSONException {
		super();
	}
	
	public Response getStatus(String jsonString) throws JSONException {
		JSONObject json;
		json = new JSONObject(jsonString);
		String status = "";
		if (!json.isNull("status"))
			status = json.getString("status");
		String message = "";
		if (!json.isNull("message"))
			message = json.getString("message");
		String usercode = "";
		if (!json.isNull("usercode"))
			usercode = json.getString("usercode");		
		return new Response(status, message, usercode);
	}

}
