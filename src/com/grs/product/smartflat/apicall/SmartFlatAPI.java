package com.grs.product.smartflat.apicall;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.models.RequestDetails;
import com.grs.product.smartflat.models.SocietyDetails;
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

	public SocietyDetails getSocietyDetails(String societyCode)
			throws SmartFlatError{
		return getSocietyDetailsCall(societyCode);
	}
	
	public Response getFlatOwnerRegistration(FlatOwnerDetails flatOwnerDetails) 
			throws SmartFlatError{
		return getFlatOwnerRegistrationCall(flatOwnerDetails);
		
	}
	
	public Response sendRequestAndComplaintDetails(RequestDetails requestDetails) 
			throws SmartFlatError{
		return sendRequestAndComplaintDetailsCall(requestDetails);
	}
	

	private Response getLoginCall(String username, String password)
			throws SmartFlatError{
		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("username", username));
			object.add(new BasicNameValuePair("password",password));
			object.add(new BasicNameValuePair("societyCode",SmartFlatApplication.getSocietyCodeFromSharedPreferences()));
			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));

			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "FlatOwnerLogin.php";
			JSONObject objJson = serverConnecter.getJSONFromUrl(URL, object);
			JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
			return objectjson.getStatus(objJson);	

		} 
		catch (JSONException e) 
		{
			throw new SmartFlatError("Server error occured. Please try again later", "Server Error");
		}
		catch (Exception e)
		{
			throw new SmartFlatError("Please try again later", "Server Error");
		}
	}

	private SocietyDetails getSocietyDetailsCall(String societyCode)
			throws SmartFlatError
			{
		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("societyCode", societyCode));
			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));

			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "getSocietyDetails.php";
			JSONObject jsonResponse = serverConnecter.getJSONFromUrl(URL, object);
			JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
			return objectjson.getSocietyDetails(jsonResponse);		 

		} 
		catch (JSONException e) 
		{
			throw new SmartFlatError("Server error occured. Please try again later", "Server Error");
		}
		catch (Exception e)
		{
			throw new SmartFlatError("Please try again later", "Server Error");
		}
			}
	
	private Response getFlatOwnerRegistrationCall(FlatOwnerDetails flatOwnerDetails) 
			throws SmartFlatError{	

		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("username", flatOwnerDetails.getmUsername()));
			object.add(new BasicNameValuePair("password",flatOwnerDetails.getmPassword()));
			object.add(new BasicNameValuePair("securityQuestion",flatOwnerDetails.getmSecurityQuestion()));
			object.add(new BasicNameValuePair("answer",flatOwnerDetails.getmAnswer()));
			object.add(new BasicNameValuePair("flatOwnerName",flatOwnerDetails.getmFlatOwnerName()));
			object.add(new BasicNameValuePair("flatOwnerDOB",flatOwnerDetails.getmFlatOwnerDOB()));
			object.add(new BasicNameValuePair("flatOwnerAge",flatOwnerDetails.getmFlatOwnerAge()));
			object.add(new BasicNameValuePair("flatOwnerContactNo",flatOwnerDetails.getmFlatOwnerContactNo()));
			object.add(new BasicNameValuePair("flatOwnerEmailId",flatOwnerDetails.getmFlatOwnerEmailId()));
			object.add(new BasicNameValuePair("buildingName",flatOwnerDetails.getmBuildingName()));
			object.add(new BasicNameValuePair("floorNo",flatOwnerDetails.getmFloorNo()));
			object.add(new BasicNameValuePair("flatNo",flatOwnerDetails.getmFlatno()));
			object.add(new BasicNameValuePair("NoOfFamilyMember",flatOwnerDetails.getmNoofFamilyMembers()+""));
			object.add(new BasicNameValuePair("NoOfVehicle",flatOwnerDetails.getmNoofVehicles()+""));
			object.add(new BasicNameValuePair("societyCode",flatOwnerDetails.getmSocietyCode()));
			object.add(new BasicNameValuePair("flatOwnerCode",flatOwnerDetails.getmFlatOwnerCode()));
			object.add(new BasicNameValuePair("gender",flatOwnerDetails.getmGender()));
			
			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "registerFlatOwner.php";
			JSONObject objJson = serverConnecter.getJSONFromUrl(URL, object);
			JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
			return objectjson.getStatus(objJson);	

		} 
		catch (JSONException e) 
		{
			throw new SmartFlatError("Server error occured. Please try again later", "Server Error");
		}
		catch (Exception e)
		{
			throw new SmartFlatError("Please try again later", "Server Error");
		}
	
	}
	
	private Response sendRequestAndComplaintDetailsCall(RequestDetails requestDetails) throws SmartFlatError
	{
		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("category", requestDetails.getmRequestCategory()));
			object.add(new BasicNameValuePair("type", requestDetails.getmRequestType()));
			object.add(new BasicNameValuePair("priority", requestDetails.getmRequestPriority()));
			object.add(new BasicNameValuePair("raisedBy", SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
			object.add(new BasicNameValuePair("details", requestDetails.getmRequestDetails()));
			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));

			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "saveFlatOwnerComplaintsAndRequests.php";
			JSONObject jsonResponse = serverConnecter.getJSONFromUrl(URL, object);
			JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
			return objectjson.getStatus(jsonResponse);		 

		} 
		catch (JSONException e) 
		{
			throw new SmartFlatError("Server error occured. Please try again later", "Server Error");
		}
		catch (Exception e)
		{
			throw new SmartFlatError("Please try again later", "Server Error");
		}		
	}

}
