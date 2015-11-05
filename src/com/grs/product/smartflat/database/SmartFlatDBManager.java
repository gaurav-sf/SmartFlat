package com.grs.product.smartflat.database;

import android.database.Cursor;

import com.grs.product.smartflat.models.ComplaintDetails;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.models.NoticeDetails;
import com.grs.product.smartflat.models.QueryDetails;
import com.grs.product.smartflat.models.RequestDetails;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.models.VehicleDetails;

public class SmartFlatDBManager {

	public boolean saveSocietyDetails(SocietyDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveSocietyDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getSocietyDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getSocietyDetails();
		SmartFlatDatabase.getInstance().close();
		return details;	
	}
	
	public boolean saveFlatOwnerDeatils(FlatOwnerDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveFlatOwnerDeatils(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getFlatOwnerDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getFlatOwnerDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveFamilyDetails(FamilyDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveFamilyDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getFamilyDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getFamilyDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveVehicleDetails(VehicleDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveVehicleDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getVehicleDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getVehicleDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveComplaintDetails(ComplaintDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveComplaintDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getAllComplaintDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllComplaintDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveRequestDetails(RequestDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveRequestDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getAllRequestDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllRequestDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveQueryDetails(QueryDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveQueryDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getAllQueryDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllQueryDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
	
	public boolean saveSocietyNoticeDetails(NoticeDetails details){
		boolean isAdded = false;
		SmartFlatDatabase.getInstance().open();
		isAdded = SmartFlatDatabase.getInstance().saveSocietyNoticeDetails(details);
		SmartFlatDatabase.getInstance().close();
		return isAdded;
	}
	
	public Cursor getAllSocietyNoticeDetails(){
		SmartFlatDatabase.getInstance().open();
		Cursor details = SmartFlatDatabase.getInstance().getAllSocietyNoticeDetails();
		SmartFlatDatabase.getInstance().close();
		return details;
	}
}
