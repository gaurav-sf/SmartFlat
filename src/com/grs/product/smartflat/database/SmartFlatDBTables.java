package com.grs.product.smartflat.database;

import android.provider.BaseColumns;

public class SmartFlatDBTables {

	private SmartFlatDBTables(){

	}
	
	public static final class TableNames{
		public static final String FLAT_OWNER_DETAILS = "Flat_Owner_Details";
		public static final String SOCIETY_DETAILS = "Society_Details";
		public static final String FAMILY_DETAILS = "Family_Details";
		public static final String VEHICLE_DETAILS = "Vehicle_Details";
		public static final String COMPLAINT_DETAILS = "Complaint_Details";
		public static final String REQUEST_DETAILS = "Request_Complaint_Details";
		public static final String QUERY_DETAILS = "Query_Details";
		public static final String SOCIETY_NOTICES= "Society_Notices";
		
	}

	public static final class TableFlatOwnerDetails implements BaseColumns
	{
		private TableFlatOwnerDetails(){}
		public static final String ID = "ID";
		public static final String USERNAME = "Username";	
		public static final String PASSWORD = "Password";	
		public static final String SECURITY_QUESTION = "Security_Question";	
		public static final String ANSWER = "Answer";	
		public static final String FLAT_OWNER_NAME = "Flat_Owner_Name"	;
		public static final String FLAT_OWNER_DOB = "Flat_Owner_DOB";
		public static final String GENDER = "Gender";
		public static final String FLAT_OWNER_AGE = "Flat_Owner_Age";	
		public static final String FLAT_OWNER_CONTACT_NO = "Flat_Owner_Contact_No";
		public static final String FLAT_OWNER_EMAIL_ID = "Flat_Owner_Email_id";
		public static final String FLAT_BUILDING_NAME = "Building_Name";	
		public static final String FLOOR_NO = "Floor_No";	
		public static final String FLAT_NO = "Flat_no";	
		public static final String SOCIETY_CODE = "Society_Code";	
		public static final String FLAT_OWNER_CREATED_DATETIME = "Flat_Owner_Created_DateTime";	
		public static final String NO_OF_FAMILY_MEMBER = "No_of_Family_Members";	
		public static final String NO_OF_VEHICLE = "No_of_Vehicles";	
		public static final String FLAT_OWNER_CODE = "Flat_Owner_Code";	
		public static final String PUSH_TOKEN = "Push_Token";	
		public static final String ACCESS_TOKEN = "Access_Token";	
		public static final String FLAT_OWNER_LATITUDE = "Latitude";	
		public static final String FLAT_OWNER_LONGITUDE = "Longitude";			

	}

	public static final class TableSocietyDetails implements BaseColumns{
		private TableSocietyDetails(){}
		public static final String ID = "ID";	
		public static final String SOCIETY_CODE = "Society_Code";	
		public static final String SOCIETY_OWNER_NAME = "Society_Owner_Name";	
		public static final String SOCIETY_OWNER_ADDRESS_LINE1 = "Society_Owner_Address_Line1";
		public static final String SOCIETY_OWNER_ADDRESS_LINE2  = "Society_Owner_Address_Line2";	
		public static final String SOCIETY_OWNER_CITY = "Society_Owner_City";	
		public static final String SOCIETY_OWNER_STATE = "Society_Owner_State";	
		public static final String SOCIETY_OWNER_PIN = "Society_Owner_PIN";	
		public static final String SOCIETY_OWNER_EMAIL_ID = "Society_Owner_Email_id";	
		public static final String SOCIETY_OWNER_CONTACT_NO = "Society_Owner_Contact_No";	
		public static final String SOCIETY_NAME = "Society_Name";	
		public static final String BUILDING_NAME = "Building_Name";	
		public static final String TOTAL_FLOOR_NUMBER = "Total_Floor_Number";	
		public static final String SOCIETY_ADDRESS_LINE1 = "Society_Address_Line1";	
		public static final String SOCIETY_ADDRESS_LINE2 = "Society_Address_Line2";	
		public static final String SOCIETY_ADDRESS_CITY = "Society_Address_City";	
		public static final String SOCIETY_ADDRESS_STATE = "Society_Address_State";	
		public static final String SOCIETY_ADDRESS_PIN = "Society_Address_PIN";	
	}

	public static final class TableFlatOwnerFamilyDetails implements BaseColumns
	{
		private TableFlatOwnerFamilyDetails(){}
		public static final String ID = "ID";
		public static final String FLAT_OWNER_CODE = "Flat_Owner_Code";
		public static final String FAMILY_MEMBER_NAME = "Family_Member_Name";
		public static final String FAMILY_MEMBER_RELATION = "Family_Member_Relation";
		public static final String FAMILY_MEMBER_DOB = "Family_Member_DOB";
		public static final String FAMILY_MEMBER_AGE = "Family_Member_Age";
		public static final String GENDER = "Gender";
		public static final String FAMILY_MEMBER_CONTACT_NO = "Family_Member_Contact_no";
		public static final String FAMILY_MEMBER_EMAIL_ID = "Family_Member_Email_Id";
		public static final String NEED_LOGIN = "Need_Login";
	}

	public static final class TableFlatOwnerVehicleDetails implements BaseColumns
	{
		private TableFlatOwnerVehicleDetails(){}
		public static final String ID = "ID";
		public static final String VEHICLE_TYPE = "Vehicle_Type";	
		public static final String VEHICLE_NUMBER = "Vehicle_Number";	
		public static final String VEHICLE_COMPANY = "Vehicle_Company";
		public static final String VEHICLE_MODEL = "Vehicle_Model";	
		public static final String VEHICLE_COLOR = "Vehicle_Color";																	
	}
/*
	public static final class TableFlatOwnerComplaintDetails implements BaseColumns
	{
		private TableFlatOwnerComplaintDetails(){}
		public static final String ID = "ID";	
		public static final String COMPLAINT_NUMBER = "Complaint_Number";	
		public static final String COMPLAINT_TYPE = "Complaint_Type";
		public static final String COMPLAINT_PRIORITY = "Complaint_Priority";
		public static final String COMPLAINT_STATUS = "Complaint_Status";	
		public static final String COMPLAINT_RAISED_DATETIME = "Complaint_Raised_DateTime";	
		public static final String COMPLAINT_DETAILS = "Complaint_Details";

	}*/

	public static final class TableFlatOwnerRequestDetails implements BaseColumns
	{
		private TableFlatOwnerRequestDetails(){}
		public static final String ID = "ID";
		public static final String REQUEST_COMPLAINT_NUMBER = "Request_Complaint_Number";
		public static final String REQUEST_COMPLAINT_TYPE = "Request_Complaint_Type";	
		public static final String REQUEST_COMPLAINT_PRIORITY = "Request_Complaint_Priority";	
		public static final String REQUEST_COMPLAINT_DATETIME = "Request_Complaint_DateTime";	
		public static final String REQUEST_COMPLAINT_STATUS = "Request_Complaint_Status";
		public static final String REQUEST_COMPLAINT_DETAILS = "Request_Complaint_Details";
		public static final String REQUEST_COMPLAINT_CATEGORY = "Request_Complaint_Category";

	}

	public static final class TableFlatOwnerQueryDetails implements BaseColumns
	{
		private TableFlatOwnerQueryDetails(){}
		public static final String ID = "ID";	
		public static final String QUERY_NUMBER = "Query_Number";	
		public static final String QUERY_DATETIME = "Query_DateTime";	
		public static final String QUERY_STATUS = "Query_Status";	
		public static final String QUERY_DETAILS = "Query_Details";

	}

	public static final class TableSocietyNotices implements BaseColumns
	{
		private TableSocietyNotices(){}
		public static final String ID = "ID";	
		public static final String NOTICE_NUMBER = "Notice_Number";	
		public static final String SOCIETY_CODE = "Society_Code";
		public static final String NOTICE_DATETIME = "Notice_DateTime";	
		public static final String NOTICE_PRIORITY = "Notice_Priority";	
		public static final String NOTICE_DETAILS = "Notice_Details";																	
	}

}
