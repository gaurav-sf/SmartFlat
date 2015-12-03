package com.grs.product.smartflat.apicall;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.grs.product.smartflat.models.RequestMessages;
import com.grs.product.smartflat.models.SocietyDetails;
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
		singleMessage.setmIsSocietyMessage(Boolean.parseBoolean(json.getString("Is_Society_Message")));
		singleMessage.setmMessageDateTime(json.getString("Message_DateTime"));
		singleMessage.setmSocietyCode(json.getString("Society_Code"));
		
		return singleMessage;		
	}

}
