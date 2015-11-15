package com.grs.product.smartflat.apicall;

import org.json.JSONException;
import org.json.JSONObject;

import com.grs.product.smartflat.models.SocietyDetails;
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
	
	public SocietyDetails getSocietyDetails(String jsonString) throws JSONException{
		
		JSONObject json;
		json = new JSONObject(jsonString);
		
		SocietyDetails societyDetails = new SocietyDetails();
		societyDetails.setmSocietyCode(!json.isNull("societyCode")?json.getString("societyCode"):"");
		societyDetails.setmSocietyOwnerName(!json.isNull("ownerName")?json.getString("ownerName"):"");
		societyDetails.setmSocietyOwnerAddressLine1(!json.isNull("ownerAddressLine1")?json.getString("ownerAddressLine1"):"");
		societyDetails.setmSocietyOwnerAddressLine2(!json.isNull("ownerAddressLine2")?json.getString("ownerAddressLine2"):"");
		societyDetails.setmSocietyOwnerCity(!json.isNull("ownerCity")?json.getString("ownerCity"):"");
		societyDetails.setmSocietyOwnerState(!json.isNull("ownerState")?json.getString("ownerState"):"");
		societyDetails.setmSocietyOwnerPIN(!json.isNull("ownerPin")?json.getString("ownerPin"):"");
		societyDetails.setmSocietyOwnerContactNo(!json.isNull("ownerContactNo")?json.getString("ownerContactNo"):"");
		societyDetails.setmSocietyOwnerEmailId(!json.isNull("ownerEmailId")?json.getString("ownerEmailId"):"");
		societyDetails.setmSocietyName(!json.isNull("societyName")?json.getString("societyName"):"");
		societyDetails.setmBuildingName(!json.isNull("buildingNo")?json.getString("buildingNo"):"");
		societyDetails.setmTotalFloorNumber(!json.isNull("buildingNo")?json.getInt("buildingNo"):0);
		societyDetails.setmSocietyAddressLine1(!json.isNull("societyAddressLine1")?json.getString("societyAddressLine1"):"");
		societyDetails.setmSocietyAddressLine2(!json.isNull("societyAddressLine2")?json.getString("societyAddressLine2"):"");
		societyDetails.setmSocietyAddressCity(!json.isNull("societyCity")?json.getString("societyCity"):"");
		societyDetails.setmSocietyAddressState(!json.isNull("societyState")?json.getString("societyState"):"");
		societyDetails.setmSocietyAddressPIN(!json.isNull("societyPin")?json.getString("societyPin"):"");
		
		return societyDetails;
		
	}

}
