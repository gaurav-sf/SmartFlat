package com.grs.product.smartflat.apicall;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.Param;
import android.content.Context;

public class SmartFlatAPI {

	final Context mContext;

	public  SmartFlatAPI(Context context) {
		this.mContext = context;
	}

	public Response getLogin(String username, String password)
			throws SmartFlatError{
		return getLoginCall(username, password);
	}

	private Response getLoginCall(String username, String password)
			throws SmartFlatError{
		try{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("username", username);
			jsonObject.put("password", password);
			HttpPost postMethod = new HttpPost(Param.baseURL
					+ "authenticateuser");
			String jsonResponse = null;
			postMethod.setEntity(new StringEntity(jsonObject.toString()));
			postMethod.addHeader("Content-Type", "application/json");
			postMethod.setHeader("Accept", "application/json");
			HttpResponse response = HttpClientSingleton.getHttpClientInstace().execute(postMethod);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
			{
				HttpEntity resEntity = response.getEntity();
				jsonResponse = EntityUtils.toString(resEntity);
				JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
				return objectjson.getStatus(jsonResponse);		
			}else {
				throw new SmartFlatError(
						response.getStatusLine().getReasonPhrase(),
						"Server Error");
			}
		} catch (ClientProtocolException e) {
			throw new SmartFlatError("Server is unavailable.", "Server Error");
		} catch (ConnectTimeoutException e) {
			throw new SmartFlatError("Server timeout.", "Server Error");
		} catch (SocketTimeoutException e) {
			throw new SmartFlatError("Server timeout.", "Server Error");
		} catch (MalformedURLException e) {
			throw new SmartFlatError(e.toString(), "MalformedURLException");
		} catch (IOException e) {
			throw new SmartFlatError("Server is unavailable.", "Server Error");
		} catch (JSONException e) {
			//throw new SmartFlatError("Error parsing data return from server", "JSON Parser");
			throw new SmartFlatError("Server error occured. Please try again later", "Server Error");
		}
	}

}
