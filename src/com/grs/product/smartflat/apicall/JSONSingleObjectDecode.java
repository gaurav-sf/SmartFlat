package com.grs.product.smartflat.apicall;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.models.ContactDetails;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.models.RequestDetails;
import com.grs.product.smartflat.models.RequestMessages;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.models.VehicleDetails;
import com.grs.product.smartflat.models.VisitorDetails;
import com.grs.product.smartflat.response.Response;

public class JSONSingleObjectDecode {

	private final String LOG = JSONSingleObjectDecode.class.getName();

	public JSONSingleObjectDecode() throws JSONException {
		super();
	}

	public Response getStatus(JSONObject json) throws JSONException {		
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

	public SocietyDetails getSocietyDetails(JSONObject json) throws JSONException{

		JSONArray societyDetailsArray = new JSONArray(json.getString("societyDetails"));
		if(societyDetailsArray!=null && societyDetailsArray.length()>0){
			if(societyDetailsArray.getString(0)==null||
					societyDetailsArray.getString(0).equalsIgnoreCase(null)||
					societyDetailsArray.getString(0).equalsIgnoreCase("null")){
				return null;
			}
			JSONObject societyJsonObject = societyDetailsArray.getJSONObject(0);

			SocietyDetails societyDetails = new SocietyDetails();
			societyDetails.setmSocietyCode(!societyJsonObject.isNull("Society_Code")?societyJsonObject.getString("Society_Code"):"");
			societyDetails.setmSocietyOwnerName(!societyJsonObject.isNull("Society_Owner_Name")?societyJsonObject.getString("Society_Owner_Name"):"");
			societyDetails.setmSocietyOwnerAddressLine1(!societyJsonObject.isNull("Society_Owner_Address_Line1")?societyJsonObject.getString("Society_Owner_Address_Line1"):"");
			societyDetails.setmSocietyOwnerAddressLine2(!societyJsonObject.isNull("Society_Owner_Address_Line2")?societyJsonObject.getString("Society_Owner_Address_Line2"):"");
			societyDetails.setmSocietyOwnerCity(!societyJsonObject.isNull("Society_Owner_City")?societyJsonObject.getString("Society_Owner_City"):"");
			societyDetails.setmSocietyOwnerState(!societyJsonObject.isNull("Society_Owner_State")?societyJsonObject.getString("Society_Owner_State"):"");
			societyDetails.setmSocietyOwnerPIN(!societyJsonObject.isNull("Society_Owner_PIN")?societyJsonObject.getString("Society_Owner_PIN"):"");
			societyDetails.setmSocietyOwnerContactNo(!societyJsonObject.isNull("Society_Owner_Contact_number")?societyJsonObject.getString("Society_Owner_Contact_number"):"");
			societyDetails.setmSocietyOwnerEmailId(!societyJsonObject.isNull("Society_Owner_Email_id")?societyJsonObject.getString("Society_Owner_Email_id"):"");
			societyDetails.setmSocietyName(!societyJsonObject.isNull("Society_Name")?societyJsonObject.getString("Society_Name"):"");
			societyDetails.setmBuildingName(!societyJsonObject.isNull("Building_Name")?societyJsonObject.getString("Building_Name"):"");
			societyDetails.setmTotalFloorNumber(!societyJsonObject.isNull("Total_Floor_Number")?societyJsonObject.getInt("Total_Floor_Number"):0);
			societyDetails.setmSocietyAddressLine1(!societyJsonObject.isNull("Society_Address_Line1")?societyJsonObject.getString("Society_Address_Line1"):"");
			societyDetails.setmSocietyAddressLine2(!societyJsonObject.isNull("Society_Address_Line2")?societyJsonObject.getString("Society_Address_Line2"):"");
			societyDetails.setmSocietyAddressCity(!societyJsonObject.isNull("Society_Address_City")?societyJsonObject.getString("Society_Address_City"):"");
			societyDetails.setmSocietyAddressState(!societyJsonObject.isNull("Society_Address_State")?societyJsonObject.getString("Society_Address_State"):"");
			societyDetails.setmSocietyAddressPIN(!societyJsonObject.isNull("Society_Address_PIN")?societyJsonObject.getString("Society_Address_PIN"):"");

			return societyDetails;

		}
		else{
			return null;
		}

	}

	public List<RequestMessages> getMessages(JSONObject json)
			throws JSONException{
		JSONArray messageArray = new JSONArray(json.getString("messages"));
		if(messageArray!=null && messageArray.length()>0)
		{
			if(messageArray.getString(0)==null||
					messageArray.getString(0).equalsIgnoreCase(null)||
					messageArray.getString(0).equalsIgnoreCase("null"))
			{
				return null;
			}
			List<RequestMessages> listMessages= new ArrayList<RequestMessages>();
			for (int i = 0; i < messageArray.length(); i++) 
			{
				listMessages.add(getSingleMessage(messageArray.getJSONObject(i)));
			}
			return listMessages;			
		}
		return null;
	}

	private RequestMessages getSingleMessage(JSONObject json) 
			throws JSONException{

		RequestMessages singleMessage = new RequestMessages();

		singleMessage.setmMessageNumber(json.getString("Message_Number"));
		singleMessage.setmMessageContent(json.getString("Message_Content"));
		singleMessage.setmRequestNumber(json.getString("Request_Number"));
		singleMessage.setmFlatOwnerCode(json.getString("Flat_Owner_Code"));
		if(json.getString("Is_Society_Message").equals("0")){
			singleMessage.setmIsSocietyMessage(false);
		}else{
			singleMessage.setmIsSocietyMessage(true);			
		}
		singleMessage.setmMessageDateTime(json.getString("Message_DateTime"));
		singleMessage.setmSocietyCode(json.getString("Society_Code"));

		return singleMessage;		
	}

	public List<ContactDetails> getContacts(JSONObject json)
			throws JSONException{
		JSONArray contactsArray = new JSONArray(json.getString("contacts"));
		if(contactsArray!=null && contactsArray.length()>0)
		{
			if(contactsArray.getString(0)==null||
					contactsArray.getString(0).equalsIgnoreCase(null)||
					contactsArray.getString(0).equalsIgnoreCase("null"))
			{
				return null;
			}
			List<ContactDetails> listContacts= new ArrayList<ContactDetails>();
			for (int i = 0; i < contactsArray.length(); i++) 
			{
				listContacts.add(getSingleContact(contactsArray.getJSONObject(i)));
			}
			return listContacts;			
		}
		return null;
	}

	private ContactDetails getSingleContact(JSONObject json) 
			throws JSONException{

		ContactDetails singleContact = new ContactDetails();		
		singleContact.setmContactName(json.getString("Contact_Name"));
		singleContact.setmContactNumber(json.getString("Contact_Number"));
		singleContact.setmContactEmailId(json.getString("Contact_Email_ID"));
		singleContact.setmContactOccupation(json.getString("Contact_Occupation"));	

		return singleContact;		
	}

	public List<VisitorDetails> getVisitors(JSONObject json)
			throws JSONException{
		JSONArray visitorsArray = new JSONArray(json.getString("visitors"));
		if(visitorsArray!=null && visitorsArray.length()>0)
		{
			if(visitorsArray.getString(0)==null||
					visitorsArray.getString(0).equalsIgnoreCase(null)||
					visitorsArray.getString(0).equalsIgnoreCase("null"))
			{
				return null;
			}
			List<VisitorDetails> listVisitors= new ArrayList<VisitorDetails>();
			for (int i = 0; i < visitorsArray.length(); i++) 
			{
				listVisitors.add(getSingleVisitor(visitorsArray.getJSONObject(i)));
			}
			return listVisitors;			
		}
		return null;
	}

	private VisitorDetails getSingleVisitor(JSONObject json) 
			throws JSONException{

		VisitorDetails singleVisitor = new VisitorDetails();	
		singleVisitor.setmVisitorCode(json.getString("Visitor_Code"));
		singleVisitor.setmVisitorName(json.getString("Visitor_Name"));
		singleVisitor.setmVisitorInTime(json.getString("Visitor_In_Time"));

		return singleVisitor;		
	}

	public Response getFlatOwnerData(JSONObject json)
			throws JSONException{
		Response response = new Response();
		SocietyDetails societyDetails = new SocietyDetails();
		FlatOwnerDetails flatOwnerDetails = new FlatOwnerDetails();
		List<FamilyDetails> listFamilyDetails = new ArrayList<FamilyDetails>();
		List<VehicleDetails> listVehicleDetails = new ArrayList<VehicleDetails>();
		List<RequestDetails> listRequestComplaint= new ArrayList<RequestDetails>();
		List<RequestMessages> listMessages= new ArrayList<RequestMessages>();
		List<VisitorDetails> listVisitors= new ArrayList<VisitorDetails>();
		String status = "";
		if (!json.isNull("status"))
			status = json.getString("status");
		if(status.equalsIgnoreCase("success"))
		{		
			societyDetails = getSocietyDetails(json);
			if(societyDetails!=null)
			{
				saveSocietyDetailsInDB(societyDetails);
				//Save Society Code in message field
				response.setMessage(societyDetails.getmSocietyCode());
			}
			flatOwnerDetails = getFlatOwnerDetails(json);
			if (flatOwnerDetails!=null){
				saveFlatOwnerDetailsInDB(flatOwnerDetails);	
				//Save Flat Owner code in role field
				response.setRole(flatOwnerDetails.getmFlatOwnerCode());
			}			
			listFamilyDetails = getFamilyDetails(json);
			if (listFamilyDetails!=null)
				saveFamilyDetailsInDB(listFamilyDetails);
			listVehicleDetails =getVehicleDetails(json);
			if (listVehicleDetails!=null)
				saveVehicleInDB(listVehicleDetails);
			listRequestComplaint = getRequestAndComplaint(json);
			if (listRequestComplaint!=null)
				saveRequestDataInDB(listRequestComplaint);
			listMessages = getMessages(json);
			if (listMessages!=null)
				saveMessagesInDB(listMessages);
			listVisitors = getVisitors(json);
			if (listVisitors!=null)
				saveVisitorInLocalDB(listVisitors);
		}
		response.setStatus(status);
		return response;
	}

	private void saveSocietyDetailsInDB(SocietyDetails societyDetails)
	{
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		boolean result = objManager.saveSocietyDetails(societyDetails);
		if(result){
			Log.e(LOG, "Society Details Insertion Successful");
		}	
	}

	public FlatOwnerDetails getFlatOwnerDetails(JSONObject json) throws JSONException 
	{
		JSONArray flatOwnerArray = new JSONArray(json.getString("flatOwnerDetails"));
		if(flatOwnerArray!=null && flatOwnerArray.length()>0)
		{
			if(flatOwnerArray.getString(0)==null||
					flatOwnerArray.getString(0).equalsIgnoreCase(null)||
					flatOwnerArray.getString(0).equalsIgnoreCase("null"))
			{
				return null;
			}
			JSONObject flatOwnerJsonObject = flatOwnerArray.getJSONObject(0);
			
			FlatOwnerDetails flatOwnerDetails = new FlatOwnerDetails();
			flatOwnerDetails.setmFlatOwnerName(flatOwnerJsonObject.getString("Flat_Owner_Name"));
			flatOwnerDetails.setmFlatOwnerContactNo(flatOwnerJsonObject.getString("Flat_Owner_Contact_No"));
			flatOwnerDetails.setmFlatOwnerEmailId(flatOwnerJsonObject.getString("Flat_Owner_Email_Id"));
			flatOwnerDetails.setmBuildingName(flatOwnerJsonObject.getString("Building_Name"));
			flatOwnerDetails.setmFloorNo(flatOwnerJsonObject.getString("Floor_No"));
			flatOwnerDetails.setmFlatno(flatOwnerJsonObject.getString("Flat_No"));
			flatOwnerDetails.setmFlatOwnerCreatedDateTime(flatOwnerJsonObject.getString("Flat_Owner_Created_DateTime"));
			flatOwnerDetails.setmFlatOwnerCode(flatOwnerJsonObject.getString("Flat_Owner_Code"));
			flatOwnerDetails.setmSecurityQuestion(flatOwnerJsonObject.getString("Security_Question"));
			flatOwnerDetails.setmAnswer(flatOwnerJsonObject.getString("Answer"));
			flatOwnerDetails.setmFlatOwnerName(flatOwnerJsonObject.getString("Flat_Owner_Name"));
			flatOwnerDetails.setmGender(flatOwnerJsonObject.getString("Gender"));
			flatOwnerDetails.setmFlatOwnerDOB(flatOwnerJsonObject.getString("Flat_Owner_DOB"));
			flatOwnerDetails.setmSocietyCode(flatOwnerJsonObject.getString("Society_Code"));
			flatOwnerDetails.setmFlatOwnerCreatedDateTime(flatOwnerJsonObject.getString("Flat_Owner_Created_DateTime"));

			return flatOwnerDetails;

		}
		else{
			return null;
		}		
	}

	private void saveFlatOwnerDetailsInDB(FlatOwnerDetails flatOwnerDetails){
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		boolean result = objManager.saveFlatOwnerDeatils(flatOwnerDetails);
		if(result){
			Log.e(LOG, "Flat Owner Details Insertion Successful");
		}
	}

	private List<FamilyDetails> getFamilyDetails(JSONObject json) throws JSONException{

		JSONArray FamilyDetailsArray = new JSONArray(json.getString("familyDetails"));
		if(FamilyDetailsArray!=null && FamilyDetailsArray.length()>0)
		{
			if(FamilyDetailsArray.getString(0)==null||
					FamilyDetailsArray.getString(0).equalsIgnoreCase(null)||
					FamilyDetailsArray.getString(0).equalsIgnoreCase("null"))
			{
				return null;
			}
			List<FamilyDetails> listFamilyDetails = new ArrayList<FamilyDetails>();
			for (int i = 0; i < FamilyDetailsArray.length(); i++) 
			{
				listFamilyDetails.add(getSingleFamilyMember(FamilyDetailsArray.getJSONObject(i)));
			}
			return listFamilyDetails;			
		}	
		return null;
	}

	private FamilyDetails getSingleFamilyMember(JSONObject json) 
			throws JSONException{

		FamilyDetails tempFamilyDetails = new FamilyDetails();
		tempFamilyDetails.setmFamilyMemberName(json.getString("Family_Member_Name"));
		tempFamilyDetails.setmFamilyMemberDOB(json.getString("Family_Member_DOB"));
		tempFamilyDetails.setmFamilyMemberContactno(json.getString("Family_Member_Contact_no"));
		tempFamilyDetails.setmFamilyMemberEmailId(json.getString("Family_Member_Email_Id"));
		tempFamilyDetails.setmGender(json.getString("Gender"));
		if(json.getString("Need_Login").equals("0")){
			tempFamilyDetails.setmNeedLogin(false);
		}else{
			tempFamilyDetails.setmNeedLogin(true);			
		}
		tempFamilyDetails.setmFlatOwnerCode(json.getString("Flat_Owner_Code"));
		tempFamilyDetails.setmFamilyMemberUsername(json.getString("Family_Member_UserName"));

		return tempFamilyDetails;
	}

	private void saveFamilyDetailsInDB(List<FamilyDetails> listFamilyDetails){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		for (int i = 0; i < listFamilyDetails.size(); i++) {
			boolean status = dbManager.saveFamilyDetails(listFamilyDetails.get(i));
			if(status){
				Log.e("Family Member from JSON", "Family Member Added");
			}
		} 	
	}

	private List<VehicleDetails> getVehicleDetails(JSONObject json) throws JSONException{

		JSONArray VehicleDetailsArray = new JSONArray(json.getString("vehicleDetails"));
		if(VehicleDetailsArray!=null && VehicleDetailsArray.length()>0)
		{
			if(VehicleDetailsArray.getString(0)==null||
					VehicleDetailsArray.getString(0).equalsIgnoreCase(null)||
					VehicleDetailsArray.getString(0).equalsIgnoreCase("null"))
			{
				return null;
			}
			List<VehicleDetails> listVehicleDetails = new ArrayList<VehicleDetails>();
			for (int i = 0; i < VehicleDetailsArray.length(); i++) 
			{
				listVehicleDetails.add(getSingleVehicleDetails(VehicleDetailsArray.getJSONObject(i)));
			}
			return listVehicleDetails;			
		}	
		return null;
	}

	private VehicleDetails getSingleVehicleDetails(JSONObject json) 
			throws JSONException{

		VehicleDetails tempDetails = new VehicleDetails();
		tempDetails.setmVehicleType(json.getString("Vehicle_Type"));
		tempDetails.setmVehicleCompany(json.getString("Vehicle_Company"));
		tempDetails.setmVehicleModel(json.getString("Vehicle_Model"));
		tempDetails.setmVehicleNumber(json.getString("Vehicle_Number"));
		tempDetails.setmVehicleColor(json.getString("Vehicle_Color"));

		return tempDetails;
	}

	private void saveVehicleInDB(List<VehicleDetails> listVehicleDetails){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		for (int i = 0; i < listVehicleDetails.size(); i++) {
			boolean status = dbManager.saveVehicleDetails(listVehicleDetails.get(i));
			if(status){
				Log.e("Vehicle Details from JSON", "Vehicle Details Added");
			}
		} 	
	}
	
	public List<RequestDetails> getRequestAndComplaint(JSONObject json) throws JSONException 
	{
		JSONArray requestComplaintArray = new JSONArray(json.getString("complaintDetails"));
		JSONArray requestArray ;
		if(requestComplaintArray!=null && requestComplaintArray.length()>0)
		{
			if(requestComplaintArray.getString(0)==null||
					requestComplaintArray.getString(0).equalsIgnoreCase(null)||
					requestComplaintArray.getString(0).equalsIgnoreCase("null"))
			{
				requestArray = new JSONArray(json.getString("requestDetails"));
				if(requestArray!=null && requestArray.length()>0)
				{
					if(requestArray.getString(0)==null||
							requestArray.getString(0).equalsIgnoreCase(null)||
							requestArray.getString(0).equalsIgnoreCase("null"))
					{
						return null;
					}
					List<RequestDetails> listRequestComplaint= new ArrayList<RequestDetails>();
					for (int i = 0; i < requestArray.length(); i++) 
					{
						listRequestComplaint.add(getSingleRequestDetails(requestArray.getJSONObject(i)));
					}
					return listRequestComplaint;

				}else{
					return null;
				}		
			}			
			List<RequestDetails> listRequestComplaint= new ArrayList<RequestDetails>();
			for (int i = 0; i < requestComplaintArray.length(); i++) 
			{
				listRequestComplaint.add(getSingleComplaintDetails(requestComplaintArray.getJSONObject(i)));
			}
			//Get Request
			requestArray = new JSONArray(json.getString("requestDetails"));				
			if(requestArray!=null && requestArray.length()>0)
			{
				if(requestArray.getString(0)==null||
						requestArray.getString(0).equalsIgnoreCase(null)||
						requestArray.getString(0).equalsIgnoreCase("null"))
				{
					//return null;
				}else{
					for (int i = 0; i < requestArray.length(); i++) 
					{
						listRequestComplaint.add(getSingleRequestDetails(requestArray.getJSONObject(i)));
					}
				}
			}	
			return listRequestComplaint;
		}
		else{
			return null;
		}		
	}

	private RequestDetails getSingleRequestDetails(JSONObject json)
			throws JSONException{
		
		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setmRequestType("Request");
		requestDetails.setmRequestNumber(json.getString("Request_Number"));
		requestDetails.setmRequestStatus(json.getString("Request_Status"));
		requestDetails.setmRequestPriority(json.getString("Request_Priority"));
		requestDetails.setmRequestDetails(json.getString("Request_Details"));
		requestDetails.setmRequestCategory(json.getString("Request_Category"));
		requestDetails.setmRequestDateTime(json.getString("Request_DateTime"));
		return requestDetails;

	}

	private RequestDetails getSingleComplaintDetails(JSONObject json)
			throws JSONException{

		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setmRequestType("Complaint");
		requestDetails.setmRequestNumber(json.getString("Complaint_Number"));
		requestDetails.setmRequestStatus(json.getString("Complaint_Status"));
		requestDetails.setmRequestPriority(json.getString("Complaint_Priority"));
		requestDetails.setmRequestDetails(json.getString("Complaint_Details"));
		requestDetails.setmRequestCategory(json.getString("Complaint_Category"));
		requestDetails.setmRequestDateTime(json.getString("Complaint_Raised_DateTime"));

		return requestDetails;

	}
	
	private void saveRequestDataInDB(List<RequestDetails> listRequestComplaint){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		for (int i = 0; i < listRequestComplaint.size(); i++) {
			boolean status = dbManager.saveRequestDetails(listRequestComplaint.get(i));
			if(status){
				Log.e("Request/Complaint from JSON", "Inserted in DB");
			}
			
		}
	}
	
	private void saveMessagesInDB(List<RequestMessages> listMessages){		
		SmartFlatDBManager objManager = new SmartFlatDBManager();
		for (int i = 0; i < listMessages.size(); i++)
		{
			boolean result = objManager.saveMessage(listMessages.get(i));
			if(result)
			{
				Log.e("Message Details", " Insertion Successful");
			}
		}		
	}
	
	private void saveVisitorInLocalDB(List<VisitorDetails> listVisitor){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		for (int i = 0; i < listVisitor.size(); i++) {
			boolean isAdded = dbManager.saveVisitor(listVisitor.get(i));
			if (isAdded) {
				Log.e("Visitor Details", "Inserted Successfully");
			}
		}
	}
	
	public Response getFamilyMemberLoginData(JSONObject json, String username)
			throws JSONException{
		Response response = new Response();
		SocietyDetails societyDetails = new SocietyDetails();
		FlatOwnerDetails flatOwnerDetails = new FlatOwnerDetails();
		List<FamilyDetails> listFamilyDetails = new ArrayList<FamilyDetails>();
		List<VehicleDetails> listVehicleDetails = new ArrayList<VehicleDetails>();
		List<RequestDetails> listRequestComplaint= new ArrayList<RequestDetails>();
		List<RequestMessages> listMessages= new ArrayList<RequestMessages>();
		List<VisitorDetails> listVisitors= new ArrayList<VisitorDetails>();
		String status = "";
		if (!json.isNull("status"))
			status = json.getString("status");
		if(status.equalsIgnoreCase("success"))
		{		
			societyDetails = getSocietyDetails(json);
			if(societyDetails!=null)
			{
				saveSocietyDetailsInDB(societyDetails);
				//Save Society Code in message field
				response.setMessage(societyDetails.getmSocietyCode());
			}
			flatOwnerDetails = getFlatOwnerDetails(json);
			if (flatOwnerDetails!=null){
				saveFlatOwnerDetailsInFamilyDB(flatOwnerDetails);	
				//Save Flat Owner code in role field
				response.setRole(flatOwnerDetails.getmFlatOwnerCode());
			}			
			listFamilyDetails = getFamilyDetails(json);
			if (listFamilyDetails!=null)
				saveFamilyDetailsInForFamilyMemberDB(listFamilyDetails, username);
			listVehicleDetails =getVehicleDetails(json);
			if (listVehicleDetails!=null)
				saveVehicleInDB(listVehicleDetails);
			listRequestComplaint = getRequestAndComplaint(json);
			if (listRequestComplaint!=null)
				saveRequestDataInDB(listRequestComplaint);
			listMessages = getMessages(json);
			if (listMessages!=null)
				saveMessagesInDB(listMessages);
			listVisitors = getVisitors(json);
			if (listVisitors!=null)
				saveVisitorInLocalDB(listVisitors);
		}
		response.setStatus(status);
		return response;
	}
	
	private void saveFlatOwnerDetailsInFamilyDB(FlatOwnerDetails flatOwnerDetails){

		FamilyDetails tempFamilyDetails = new FamilyDetails();
		tempFamilyDetails.setmFamilyMemberName(flatOwnerDetails.getmFlatOwnerName());
		tempFamilyDetails.setmFamilyMemberDOB(flatOwnerDetails.getmFlatOwnerDOB());
		tempFamilyDetails.setmFamilyMemberContactno(flatOwnerDetails.getmFlatOwnerContactNo());
		tempFamilyDetails.setmFamilyMemberEmailId(flatOwnerDetails.getmFlatOwnerEmailId());
		tempFamilyDetails.setmGender(flatOwnerDetails.getmGender());
		tempFamilyDetails.setmNeedLogin(true);			
		tempFamilyDetails.setmFlatOwnerCode(flatOwnerDetails.getmFlatOwnerCode());
		tempFamilyDetails.setmFamilyMemberUsername(flatOwnerDetails.getmFlatOwnerCode());
		
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		boolean status = dbManager.saveFamilyDetails(tempFamilyDetails);
		if(status)
		{
				Log.e("Family Member from JSON", "Family Member Added");				
		}	
	}
	
	private void saveFamilyDetailsInForFamilyMemberDB(List<FamilyDetails> listFamilyDetails, String username){
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		for (int i = 0; i < listFamilyDetails.size(); i++) {
			if(listFamilyDetails.get(i).getmFamilyMemberUsername().equalsIgnoreCase(username))
			{
				FamilyDetails familyDetails = listFamilyDetails.get(i);
				FlatOwnerDetails flatOwnerDetails = new FlatOwnerDetails();
				flatOwnerDetails.setmFlatOwnerName(familyDetails.getmFamilyMemberName());
				flatOwnerDetails.setmFlatOwnerContactNo(familyDetails.getmFamilyMemberContactno());
				flatOwnerDetails.setmFlatOwnerEmailId(familyDetails.getmFamilyMemberEmailId());
			//	flatOwnerDetails.setmBuildingName();
			//	flatOwnerDetails.setmFloorNo();
			//	flatOwnerDetails.setmFlatno();
			//	flatOwnerDetails.setmFlatOwnerCreatedDateTime();
				flatOwnerDetails.setmFlatOwnerCode(familyDetails.getmFlatOwnerCode());
			//	flatOwnerDetails.setmSecurityQuestion();
			//	flatOwnerDetails.setmAnswer();
				//flatOwnerDetails.setmFlatOwnerName();
				flatOwnerDetails.setmGender(familyDetails.getmGender());
				flatOwnerDetails.setmFlatOwnerDOB(familyDetails.getmFamilyMemberDOB());
				//flatOwnerDetails.setmSocietyCode();
			//	flatOwnerDetails.setmFlatOwnerCreatedDateTime();
				dbManager.saveFlatOwnerDeatils(flatOwnerDetails);
			}else
			{
				boolean status = dbManager.saveFamilyDetails(listFamilyDetails.get(i));
				if(status){
					Log.e("Family Member from JSON", "Family Member Added");
				}
			}

		} 	
	
		
	}

}
