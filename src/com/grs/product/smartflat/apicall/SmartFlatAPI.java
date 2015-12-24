package com.grs.product.smartflat.apicall;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.error.SmartFlatError;
import com.grs.product.smartflat.models.ContactDetails;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.models.NoticeDetails;
import com.grs.product.smartflat.models.RequestDetails;
import com.grs.product.smartflat.models.RequestMessages;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.models.VehicleDetails;
import com.grs.product.smartflat.models.VisitorDetails;
import com.grs.product.smartflat.response.Response;
import com.grs.product.smartflat.utils.Param;
import com.grs.product.smartflat.utils.Utilities;

import android.content.Context;

public class SmartFlatAPI {

	final Context mContext;

	public  SmartFlatAPI(Context context) {
		this.mContext = context;
	}

	public Response getLogin(String username, String password, String role,  String ownerCode)
			throws SmartFlatError{
		return getLoginCall(username, password, role, ownerCode);
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
	
	public Response saveFamilyMember(FamilyDetails familyDetails) 
			throws SmartFlatError{
		return saveFamilyMemberCall(familyDetails);
	} 
	
	public Response saveVehicleDetails(VehicleDetails vehicleDetails) 
			throws SmartFlatError{
		return saveVehicleDetailsCall(vehicleDetails);
	} 
	
	public Response sendMesssage(String message, String requestNumber) throws SmartFlatError{
		return sendMessageCall(message, requestNumber);
	}
	
	public Response sendPushToken(String pushToen,String flatOwnerCode, String societyCode) throws SmartFlatError{
		return sendPushTokenCall(pushToen, flatOwnerCode,  societyCode);
	}
	
	public List<RequestMessages> getMessages()
			throws SmartFlatError
	{
		return getMessagesCall();
	}
	
	public List<ContactDetails> getContacts()
			throws SmartFlatError
	{
		return getContactsCall();
	}
	
	public List<VisitorDetails> getVisitors()
			throws SmartFlatError
	{
		return getVisitorsCall();
	}
	
	public Response updatePassword(String newPassword) throws SmartFlatError{
		return updatePasswordCall(newPassword);
	}
	
	public Response getFamilyMemberOrTenantValidation(String flatOwnerCode, String username, String role)
			throws SmartFlatError{
		return getFamilyMemberOrTenantValidationCall(flatOwnerCode, username, role);
	}
	
	public Response registerFamilyMember(FamilyDetails familyDetails) 
			throws SmartFlatError{
		return registerFamilyMemberCall(familyDetails);
	}
	
	public Response getFlatOwnerData(String username, String ownerCode,String accessRole)
			throws SmartFlatError{
		return getFlatOwnerDataCall(username, ownerCode, accessRole);
	}
	
	public List<NoticeDetails> getNotices()
			throws SmartFlatError
	{
		return getNoticesCall();
	}
	
	public Response getSignOut(String role)
			throws SmartFlatError{
		return getSignOutCall(role);
	}

	private Response getLoginCall(String username, String password, String role, String ownerCode)
			throws SmartFlatError{
		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("username", username));
			object.add(new BasicNameValuePair("password",password));
			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));
			String URL = "";
			ServerConnecter serverConnecter = new ServerConnecter();
			if(role.equalsIgnoreCase("Family Member")){
				object.add(new BasicNameValuePair("flatOwnerCode",ownerCode));
				URL = Param.baseURL + "familyMemberLogin.php";
			}else if(role.equalsIgnoreCase("Tenant")){
				object.add(new BasicNameValuePair("flatOwnerCode",ownerCode));
				//URL = Param.baseURL + "familyMemberLogin.php";
			}else{
				object.add(new BasicNameValuePair("societyCode",ownerCode));
			   URL = Param.baseURL + "FlatOwnerLogin.php";
			}
			
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
			object.add(new BasicNameValuePair("societyCode", SmartFlatApplication.getSocietyCodeFromSharedPreferences()));

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
	
	private Response saveFamilyMemberCall(FamilyDetails familyDetails) throws SmartFlatError{

		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("flatOwnerCode", SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
			object.add(new BasicNameValuePair("familyMemberName",familyDetails.getmFamilyMemberName() ));
			object.add(new BasicNameValuePair("familyMemberDOB",familyDetails.getmFamilyMemberDOB() ));
			object.add(new BasicNameValuePair("familyMemberAge", familyDetails.getmFamilyMemberAge()));
			object.add(new BasicNameValuePair("familyMemberContactNo", familyDetails.getmFamilyMemberContactno()));
			object.add(new BasicNameValuePair("familyMemberEmailId", familyDetails.getmFamilyMemberEmailId()));

			object.add(new BasicNameValuePair("gender", familyDetails.getmGender()));

			object.add(new BasicNameValuePair("needLogin", familyDetails.ismNeedLogin()+""));

			
			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));

			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "addFamilyMember.php";
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
	
	private Response saveVehicleDetailsCall(VehicleDetails vehicleDetails) throws SmartFlatError{

		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("flatOwnerCode", SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
			object.add(new BasicNameValuePair("vehicleType", vehicleDetails.getmVehicleType()));
			object.add(new BasicNameValuePair("vehicleNumber", vehicleDetails.getmVehicleNumber()));
			object.add(new BasicNameValuePair("vehicleCompany", vehicleDetails.getmVehicleCompany()));
			object.add(new BasicNameValuePair("vehicleModel", vehicleDetails.getmVehicleModel()));
			object.add(new BasicNameValuePair("vehicleColor", vehicleDetails.getmVehicleColor()));

			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));

			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "addVehiclesDetails.php";
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
	
	private Response sendMessageCall(String message, String requestNumber) throws SmartFlatError{

		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("message", message));
			object.add(new BasicNameValuePair("requestNumber",requestNumber));
			object.add(new BasicNameValuePair("societyCode",SmartFlatApplication.getSocietyCodeFromSharedPreferences()));
			object.add(new BasicNameValuePair("flatOwnerCode",SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
			object.add(new BasicNameValuePair("isSocietyMessage","0"));
			object.add(new BasicNameValuePair("messageDateTime",Utilities.getCurrentDateTime()));

			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));

			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "sendMessage.php";
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
	
	private Response sendPushTokenCall(String pushToken,String flatOwnerCode, String societyCode)
			throws SmartFlatError{
		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("pushToken", pushToken));
			object.add(new BasicNameValuePair("flatOwnerCode",flatOwnerCode));
			object.add(new BasicNameValuePair("societyCode", societyCode));
			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "saveFlatOwnerPushToken.php";
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
	
	private List<RequestMessages> getMessagesCall() 
			throws SmartFlatError
	{
		try
		{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("flatOwnerCode", SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));

			ServerConnecter obj = new ServerConnecter();
			String URL = Param.baseURL + "getFlatOwnerMessages.php";
			JSONObject objJson = obj.getJSONFromUrl(URL, object);
			JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
			return objectjson.getMessages(objJson);

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
	
	private List<ContactDetails> getContactsCall() 
			throws SmartFlatError
	{
		try
		{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("societyCode", SmartFlatApplication.getSocietyCodeFromSharedPreferences()));

			ServerConnecter obj = new ServerConnecter();
			String URL = Param.baseURL + "getContacts.php";
			JSONObject objJson = obj.getJSONFromUrl(URL, object);
			JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
			return objectjson.getContacts(objJson);

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
	
	private List<VisitorDetails> getVisitorsCall() 
			throws SmartFlatError
	{
		try
		{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("faltOwnerCode", SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
			ServerConnecter obj = new ServerConnecter();
			String URL = Param.baseURL + "getVisitorsForFlatOwner.php";
			JSONObject objJson = obj.getJSONFromUrl(URL, object);
			JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
			return objectjson.getVisitors(objJson);
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
	
	private Response updatePasswordCall(String newPassword)
			throws SmartFlatError{
		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("newPassword", newPassword));
			object.add(new BasicNameValuePair("flatOwnerCode",SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
			object.add(new BasicNameValuePair("societyCode",SmartFlatApplication.getSocietyCodeFromSharedPreferences()));
			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));

			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "updateFlatOwnerPassword.php";
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
	
	private Response getFamilyMemberOrTenantValidationCall(String flatOwnerCode, String username, String role)
			throws SmartFlatError{
		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("username", username));
			object.add(new BasicNameValuePair("flatOwnerCode",flatOwnerCode));
			object.add(new BasicNameValuePair("role",role));
			String URL = "";
			ServerConnecter serverConnecter = new ServerConnecter();
			if(role.equalsIgnoreCase("FamilyMember")){
				URL = Param.baseURL + "validateFamilyMember.php";
			}else if(role.equalsIgnoreCase("Tenant")){
				URL = Param.baseURL + "validateTenant.php";
			}
			
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
	
	private Response registerFamilyMemberCall(FamilyDetails familyDetails) throws SmartFlatError{

		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("flatOwnerCode", familyDetails.getmFlatOwnerCode()));
			object.add(new BasicNameValuePair("familyMemberName",familyDetails.getmFamilyMemberName() ));
			object.add(new BasicNameValuePair("familyMemberDOB",familyDetails.getmFamilyMemberDOB() ));
			object.add(new BasicNameValuePair("familyMemberContactNo", familyDetails.getmFamilyMemberContactno()));
			object.add(new BasicNameValuePair("familyMemberEmailId", familyDetails.getmFamilyMemberEmailId()));
			object.add(new BasicNameValuePair("gender", familyDetails.getmGender()));
			object.add(new BasicNameValuePair("username", familyDetails.getmFamilyMemberUsername()));
			object.add(new BasicNameValuePair("password", familyDetails.getmPassword()));
			object.add(new BasicNameValuePair("securityQuestion", familyDetails.getmSecurityQuestion()));
			object.add(new BasicNameValuePair("answer", familyDetails.getmAnswer()));
			
			ServerConnecter serverConnecter = new ServerConnecter();
			String URL = Param.baseURL + "registerFamilyMember.php";
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
	
	private Response getFlatOwnerDataCall(String username, String ownerCode, String accessRole) throws SmartFlatError{

		try{		
			if(accessRole.equalsIgnoreCase("Family Member")){
				ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
				object.add(new BasicNameValuePair("familyMemberCode", username));
				object.add(new BasicNameValuePair("flatOwnerCode",ownerCode));
				
				ServerConnecter serverConnecter = new ServerConnecter();
				String URL = Param.baseURL + "getFamilyMemberData.php";
				JSONObject jsonResponse = serverConnecter.getJSONFromUrl(URL, object);
				JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
				return objectjson.getFamilyMemberLoginData(jsonResponse,username);	
			}else if(accessRole.equalsIgnoreCase("Tenant")){
				//Customize for tenant
				return null;	
			}else{
				ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
				object.add(new BasicNameValuePair("flatOwnerCode", username));
				object.add(new BasicNameValuePair("societyCode",ownerCode));			
				ServerConnecter serverConnecter = new ServerConnecter();
				String URL = Param.baseURL + "getFlatOwnerData.php";
				JSONObject jsonResponse = serverConnecter.getJSONFromUrl(URL, object);
				JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
				return objectjson.getFlatOwnerData(jsonResponse);	
			}
			
			//return objectjson.getFlatOwnerData(jsonResponse);		 

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
	
	private List<NoticeDetails> getNoticesCall() 
			throws SmartFlatError
	{
		try
		{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			object.add(new BasicNameValuePair("faltOwnerCode", SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
			object.add(new BasicNameValuePair("societyCode", SmartFlatApplication.getSocietyCodeFromSharedPreferences()));
			ServerConnecter obj = new ServerConnecter();
			String URL = Param.baseURL + "getNoticesForFlatOwner.php";
			JSONObject objJson = obj.getJSONFromUrl(URL, object);
			JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
			return objectjson.getNotices(objJson);
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
	
	private Response getSignOutCall(String role)
			throws SmartFlatError{
		try{
			ArrayList<NameValuePair> object = new ArrayList<NameValuePair>();
			//object.add(new BasicNameValuePair("totalFloorNo", societyDetails.getmTotalFloorNumber()+""));
			String URL = "";
			ServerConnecter serverConnecter = new ServerConnecter();
			
			if(role.equalsIgnoreCase("Family Member")){
				object.add(new BasicNameValuePair("familyMemeberUsername", SmartFlatApplication.getUserCodeFromSharedPreferences()));
				object.add(new BasicNameValuePair("flatOwnerCode",SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
				URL = Param.baseURL + "signOutFamilyMember.php";
			}
			
			else if(role.equalsIgnoreCase("Tenant")){
			//	object.add(new BasicNameValuePair("flatOwnerCode",ownerCode));
				//URL = Param.baseURL + "familyMemberLogin.php";
			}
			
			else{
				object.add(new BasicNameValuePair("flatOwnerCode",SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences()));
				object.add(new BasicNameValuePair("societyCode",SmartFlatApplication.getSocietyCodeFromSharedPreferences()));
			   URL = Param.baseURL + "signOutFlatOwner.php";
			}
			
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

}
