package com.grs.product.smartflat.database;

import com.grs.product.smartflat.models.SocietyDetails;

public class SmartFlatDBManager {

	public boolean saveSocietyDetails(SocietyDetails details){
		SmartFlatDatabase.getInstance().open();
		SmartFlatDatabase.getInstance().saveSocietyDetails(details);
		SmartFlatDatabase.getInstance().close();
		return false;
	}
}
