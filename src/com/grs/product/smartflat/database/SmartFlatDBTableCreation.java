package com.grs.product.smartflat.database;

import com.grs.product.smartflat.database.SmartFlatDBTables.TableContactDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerFamilyDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerQueryDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerRequestDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerVehicleDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableMessageDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableNames;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableSocietyDetails;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableSocietyNotices;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableVisitorDetails;

public class SmartFlatDBTableCreation {

	public static final String TABLE_FLAT_OWNER_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.FLAT_OWNER_DETAILS
			+"( " 
			+ TableFlatOwnerDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerDetails.USERNAME + " TEXT, "
			+ TableFlatOwnerDetails.PASSWORD + " TEXT, "
			+ TableFlatOwnerDetails.SECURITY_QUESTION + " TEXT, "
			+ TableFlatOwnerDetails.ANSWER + " TEXT, "
			+ TableFlatOwnerDetails.FLAT_OWNER_NAME + " TEXT, "
			+ TableFlatOwnerDetails.GENDER+ " TEXT, "
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
			+ TableFlatOwnerFamilyDetails.GENDER+ " TEXT, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_DOB + " TEXT, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_AGE + " INTEGER, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_CONTACT_NO + " TEXT, "
			+ TableFlatOwnerFamilyDetails.FAMILY_MEMBER_EMAIL_ID + " TEXT, "
			+ TableFlatOwnerFamilyDetails.NEED_LOGIN + " BOOLEAN);";
	
	public static final String TABLE_VEHICLE_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.VEHICLE_DETAILS
			+"( "
			+ TableFlatOwnerVehicleDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_TYPE + " TEXT, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_NUMBER + " TEXT, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_COMPANY + " TEXT, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_MODEL + " TEXT, "
			+ TableFlatOwnerVehicleDetails.VEHICLE_COLOR + " TEXT);";
/*	
	public static final String TABLE_COMPLAINT_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.COMPLAINT_DETAILS
			+"( "
			+ TableFlatOwnerComplaintDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_NUMBER + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_STATUS + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_PRIORITY + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_TYPE + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_RAISED_DATETIME + " TEXT, "
			+ TableFlatOwnerComplaintDetails.COMPLAINT_DETAILS + " TEXT);";*/
	
	public static final String TABLE_REQUEST_COMPLAINT_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.REQUEST_DETAILS
			+"( "
			+ TableFlatOwnerRequestDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_PRIORITY + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_TYPE + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_CATEGORY + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME + " TEXT, "
			+ TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DETAILS + " TEXT);";
	
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
	
	public static final String TABLE_CONTACT_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.CONTACT_DETAILS
			+"( "
			+ TableContactDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableContactDetails.CONTACT_NAME + " TEXT, "
			+ TableContactDetails.CONTACT_NUMBER + " TEXT, "
			+ TableContactDetails.CONTACT_EMAIL_ID + " TEXT, "
			+ TableContactDetails.CONTACT_OCCUPATION + " TEXT);";
	
	public static final String TABLE_MESSAGE_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.MESSAGE_DETAILS
			+"( "
			+ TableMessageDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableMessageDetails.MESSAGE_NUMBER + " TEXT, "
			+ TableMessageDetails.MESSAGE_CONTENT + " TEXT, "
			+ TableMessageDetails.REQUEST_NUMBER + " TEXT, "
			+ TableMessageDetails.FLAT_OWNER_CODE + " TEXT, "
			+ TableMessageDetails.SOCIETY_CODE + " TEXT, "
			+ TableMessageDetails.IS_SOCIETY_MESSAGE + " BOOLEAN, "
			+ TableMessageDetails.MESSAGE_DATETIME + " TEXT);";
	
	public static final String TABLE_VISITOR_DETAILS_CREATION_QUERY = "Create table if not exists "+TableNames.VISITOR_DETAILS
			+"( "
			+ TableVisitorDetails.ID + " INTEGER PRIMARY KEY, "
			+ TableVisitorDetails.VISITOR_CODE + " TEXT, "
			+ TableVisitorDetails.VISITOR_NAME + " TEXT, "
			+ TableVisitorDetails.VISITOR_CONTACT_NO + " TEXT, "
			+ TableVisitorDetails.VISIT_PURPOSE + " TEXT, "
			+ TableVisitorDetails.VISITOR_VEHICLE_NO + " TEXT, "
			+ TableVisitorDetails.VISITOR_IN_TIME + " TEXT, "
			+ TableVisitorDetails.VISITOR_OUT_TIME + " TEXT, "
			+ TableVisitorDetails.NO_OF_VISITORS + " INTEGER);";
	}
