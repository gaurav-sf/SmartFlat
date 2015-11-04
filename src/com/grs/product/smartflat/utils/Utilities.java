package com.grs.product.smartflat.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.database.Cursor;

import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableSocietyDetails;
import com.grs.product.smartflat.models.SocietyDetails;

public class Utilities {

	public static SocietyDetails getSocietyDetails(){
		SocietyDetails societyDetails = null;
		SmartFlatDBManager dbManager = new SmartFlatDBManager();
		Cursor cursorDeatils = dbManager.getSocietyDetails();

		if(cursorDeatils!=null && cursorDeatils.getCount()>0){
			societyDetails = new SocietyDetails();
			societyDetails.setmSocietyCode(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_CODE)));
			societyDetails.setmSocietyOwnerName(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_OWNER_NAME)));
			societyDetails.setmSocietyOwnerAddressLine1(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_OWNER_ADDRESS_LINE1)));
			societyDetails.setmSocietyOwnerAddressLine2(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_OWNER_ADDRESS_LINE2)));
			societyDetails.setmSocietyOwnerCity(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_OWNER_CITY)));
			societyDetails.setmSocietyOwnerState(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_OWNER_STATE)));
			societyDetails.setmSocietyOwnerPIN(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_OWNER_PIN)));
			societyDetails.setmSocietyOwnerContactNo(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_OWNER_CONTACT_NO)));
			societyDetails.setmSocietyOwnerEmailId(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_OWNER_EMAIL_ID)));
			societyDetails.setmSocietyName(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_NAME)));
			societyDetails.setmBuildingName(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.BUILDING_NAME)));
			societyDetails.setmTotalFloorNumber(cursorDeatils.getInt(cursorDeatils.getColumnIndex(TableSocietyDetails.TOTAL_FLOOR_NUMBER)));
			societyDetails.setmSocietyAddressLine1(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_ADDRESS_LINE1)));
			societyDetails.setmSocietyAddressLine2(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_ADDRESS_LINE2)));
			societyDetails.setmSocietyAddressCity(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_ADDRESS_CITY)));
			societyDetails.setmSocietyAddressState(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_ADDRESS_STATE)));
			societyDetails.setmSocietyAddressPIN(cursorDeatils.getString(cursorDeatils.getColumnIndex(TableSocietyDetails.SOCIETY_ADDRESS_PIN)));

		}		
		return societyDetails;


	}
	
	public static String getUTCDateTime() {

		String dateTime="";
		Date date = new Date();		
		DateFormat converter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		converter.setTimeZone(TimeZone.getTimeZone("UTC"));
		dateTime=converter.format(date);
		
		return dateTime;
	}

}
