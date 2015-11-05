package com.grs.product.smartflat.database;

import android.database.Cursor;

import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.models.SocietyDetails;

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
}
