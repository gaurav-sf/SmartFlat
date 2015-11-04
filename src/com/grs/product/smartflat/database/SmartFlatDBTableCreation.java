package com.grs.product.smartflat.database;

import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerComplaintDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerFamilyDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerQueryDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerRequestDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerVehicleDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableNames;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableSocietyDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableSocietyNotices;

public class SmartFlatDBTableCreation {

	public static final String TABLE_FLAT_OWNER_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.FLAT_OWNER_DETAILS
			+"( " 
			+ TableFlatOwnerDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerDetails.USERNAME + " TEXT, "
			+ TableFlatOwnerDetails.PASSWORD + " TEXT, "
			+ TableFlatOwnerDetails.SECURITY_QUESTION + " TEXT, "
			+ TableFlatOwnerDetails.ANSWER + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_OWNER_NAME + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_OWNER_DOB+ " TEXT, "
			+ TableFlatOwnerDetails.FLAT_OWNER_AGE + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_OWNER_CONTACT_NO + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_OWNER_EMAIL_ID + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_BUILDING_NAME + " TEXT, "
			+ TableFlatOwnerDetails.FLOOR_NO + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_NO + " TEXT, "
			+ TableFlatOwnerDetails.SOCIETY_CODE + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_OWNER_CREATED_DATETIME + " TEXT, "
			+ TableFlatOwnerDetails.NO_OF_FAMILY_MEMBER + " INTEGER, "
			+ TableFlatOwnerDetails.NO_OF_VEHICLE + " INTEGER, "
			+ TableFlatOwnerDetails.FLAT_OWNER_CODE + " TEXT, "
			+ TableFlatOwnerDetails.PUSH_TOKEN + " TEXT, "
			+ TableFlatOwnerDetails.ACCESS_TOKEN + " TEXT,"
			+ TableFlatOwnerDetails.FLAT_OWNER_LATITUDE + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_OWNER_LONGITUDE + " TEXT );";
	
	public static final String TABLE_SOCIETY_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.SOCIETY_DETAILS
			+"( "
			+ TableSocietyDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableSocietyDetails.SOCIETY_CODE + " TEXT, "
			+ TableSocietyDetails.SOCIETY_OWNER_NAME + " TEXT, "
			+ TableSocietyDetails.SOCIETY_OWNER_ADDRESS_LINE1 + " TEXT, "
			+ TableSocietyDetails.SOCIETY_OWNER_ADDRESS_LINE2 + " TEXT, "
			+ TableSocietyDetails.SOCIETY_OWNER_CITY + " TEXT, "
			+ TableSocietyDetails.SOCIETY_OWNER_STATE + " TEXT, "
			+ TableSocietyDetails.SOCIETY_OWNER_PIN + " TEXT, "
			+ TableSocietyDetails.SOCIETY_OWNER_CONTACT_NO + " TEXT, "
			+ TableSocietyDetails.SOCIETY_OWNER_EMAIL_ID + " TEXT, "
			+ TableSocietyDetails.SOCIETY_NAME + " TEXT, "
			+ TableSocietyDetails.BUILDING_NAME + " TEXT, "
			+ TableSocietyDetails.TOTAL_FLOOR_NUMBER + " INTEGER, "
			+ TableSocietyDetails.SOCIETY_ADDRESS_LINE1 + " TEXT, "
			+ TableSocietyDetails.SOCIETY_ADDRESS_LINE2 + " TEXT, "
			+ TableSocietyDetails.SOCIETY_ADDRESS_CITY + " TEXT, "
			+ TableSocietyDetails.SOCIETY_ADDRESS_STATE + " TEXT, "
			+ TableSocietyDetails.SOCIETY_ADDRESS_PIN + " TEXT ); ";
	
	public static final String TABLE_FAMILY_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.FAMILY_DETAILS
			+"( "
			+ TableFlatOwnerFamilyDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerFamilyDetails.FLAT_OWNER_CODE + " TEXT, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_NAME + " TEXT, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_RELATION + " TEXT, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_DOB + " TEXT, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_AGE + " INTEGER, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_CONTACT_NO + " TEXT, "
			+ TableFlatOwnerFamilyDetails.NEED_LOGIN + " BOOLEAN);";
	
	public static final String TABLE_VEHICLE_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.VEHICLE_DETAILS
			+"( "
			+ TableFlatOwnerVehicleDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_TYPE + " INTEGER, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_NUMBER + " TEXT, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_COMPANY + " TEXT, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_MODEL + " TEXT, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_COLOR + " TEXT);";
	
	public static final String TABLE_COMPLAINT_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.COMPAINTS_DETAILS
			+"( "
			+ TableFlatOwnerComplaintDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_NUMBER + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_STATUS + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_TYPE + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_RAISED_DATETIME + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_DETAILS + " TEXT);";
	
	public static final String TABLE_REQUEST_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.REQUEST_DETAILS
			+"( "
			+ TableFlatOwnerRequestDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerRequestDetails.REQUEST_NUMBER + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_PRIORITY + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_STATUS + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_TYPE + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_DATETIME + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_DETAILS + " TEXT);";
	
	public static final String TABLE_QUERY_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.QUERY_DETAILS
			+"( "
			+ TableFlatOwnerQueryDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerQueryDetails.QUERY_NUMBER + " TEXT, "
			+ TableFlatOwnerQueryDetails.QUERY_STATUS + " TEXT, "
			+ TableFlatOwnerQueryDetails.QUERY_DATETIME + " TEXT, "
			+ TableFlatOwnerQueryDetails.QUERY_DETAILS + " TEXT);";
	
	public static final String TABLE_SOCIETY_NOTICES_CREATION_QUERY = "Create table if not exists "+TableNames.SOCIETY_NOTICES
			+"( "
			+ TableSocietyNotices.ID + " INTEGER PRIMARY KEY, "
			+ TableSocietyNotices.NOTICE_NUMBER + " TEXT, "
			+ TableSocietyNotices.NOTICE_PRIORITY + " TEXT, "
			+ TableSocietyNotices.NOTICE_DATETIME + " TEXT, "
			+ TableSocietyNotices.NOTICE_DETAILS + " TEXT);";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
