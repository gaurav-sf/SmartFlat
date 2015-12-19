package com.grs.product.smartflat.database;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.grs.product.smartflat.SmartFlatApplication;
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
import com.grs.product.smartflat.models.ContactDetails;
import com.grs.product.smartflat.models.FamilyDetails;
import com.grs.product.smartflat.models.FlatOwnerDetails;
import com.grs.product.smartflat.models.NoticeDetails;
import com.grs.product.smartflat.models.QueryDetails;
import com.grs.product.smartflat.models.RequestDetails;
import com.grs.product.smartflat.models.RequestMessages;
import com.grs.product.smartflat.models.SocietyDetails;
import com.grs.product.smartflat.models.VehicleDetails;
import com.grs.product.smartflat.models.VisitorDetails;

public class SmartFlatDatabase {
	private SQLiteDatabase mDb;
	private SQLiteOpenHelper helper;
	private static SmartFlatDatabase instance;
	private int openCount = 0;
	private static final String DATABASE_NAME = "SmartFlat";
	private static final int DATABASE_VERSION = 1; //

	public static SmartFlatDatabase getInstance() 
	{
		if (instance == null) 
		{
			instance = new SmartFlatDatabase(SmartFlatApplication.getInstance().getApplicationContext());
		}
		return instance;
	}

	private SmartFlatDatabase(Context context){
		helper = new SmartFlatDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		helper.getWritableDatabase();
	}

	public synchronized void open() 
	{
		if (mDb == null || !mDb.isOpen()) {
			mDb = helper.getWritableDatabase();
		}
		openCount++;
	}

	public synchronized void close() 
	{
		openCount--;
		if (openCount <= 0) 
		{
			openCount = 0;
			helper.close();
		}
	}


	//This method will be used while altering the tables
	private boolean checkIfColumnsExist(SQLiteDatabase db, String tableName,
			String[] columns) {
		Cursor cur = db.query(tableName, null, null, null, null, null, null);
		String[] dbColumns = cur.getColumnNames();
		Set<String> dbColumnNameSet = new HashSet<String>(
				Arrays.asList(dbColumns));
		Set<String> columnsToFindSet = new HashSet<String>(
				Arrays.asList(columns));
		columnsToFindSet.removeAll(dbColumnNameSet);

		return !(columnsToFindSet.size() > 0);
	}

	private void createFlatOwnerDetailsTable(SQLiteDatabase db)
	{
		try 
		{
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_FLAT_OWNER_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}
	}

	private void createSocietyDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_SOCIETY_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	private void createFamilyDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_FAMILY_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	private void createVehicleDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_VEHICLE_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	/*	private void createComplaintDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_COMPLAINT_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}*/

	private void createRequestDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_REQUEST_COMPLAINT_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	private void createQueryDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_QUERY_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	private void createSocietyNoticesTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_SOCIETY_NOTICES_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	private void createContactDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_CONTACT_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	private void createMessageDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_MESSAGE_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	private void createVisitorDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_VISITOR_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	//inner class
	public class SmartFlatDatabaseHelper extends SQLiteOpenHelper{

		public SmartFlatDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			try {
				createFlatOwnerDetailsTable(db);
				createSocietyDetailsTable(db);
				createFamilyDetailsTable(db);
				createVehicleDetailsTable(db);
				//createComplaintDetailsTable(db);
				createRequestDetailsTable(db);
				createQueryDetailsTable(db);
				createSocietyNoticesTable(db);
				createContactDetailsTable(db);
				createMessageDetailsTable(db);
				createVisitorDetailsTable(db);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onCreate(db);
		}

	}

	public boolean saveSocietyDetails(SocietyDetails details)
	{
		boolean isAdded = false;
		ContentValues values = new ContentValues();
		values.put(TableSocietyDetails.SOCIETY_CODE,details.getmSocietyCode());
		values.put(TableSocietyDetails.SOCIETY_OWNER_NAME,details.getmSocietyOwnerName());
		values.put(TableSocietyDetails.SOCIETY_OWNER_ADDRESS_LINE1,details.getmSocietyOwnerAddressLine1());
		values.put(TableSocietyDetails.SOCIETY_OWNER_ADDRESS_LINE2,details.getmSocietyOwnerAddressLine2());
		values.put(TableSocietyDetails.SOCIETY_OWNER_CITY,details.getmSocietyOwnerCity());
		values.put(TableSocietyDetails.SOCIETY_OWNER_STATE,details.getmSocietyOwnerState());
		values.put(TableSocietyDetails.SOCIETY_OWNER_PIN,details.getmSocietyOwnerPIN());
		values.put(TableSocietyDetails.SOCIETY_OWNER_CONTACT_NO,details.getmSocietyOwnerContactNo());
		values.put(TableSocietyDetails.SOCIETY_OWNER_EMAIL_ID,details.getmSocietyOwnerEmailId());
		values.put(TableSocietyDetails.SOCIETY_NAME,details.getmSocietyName());
		values.put(TableSocietyDetails.BUILDING_NAME,details.getmBuildingName());
		values.put(TableSocietyDetails.TOTAL_FLOOR_NUMBER,details.getmTotalFloorNumber());
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_LINE1,details.getmSocietyAddressLine1());
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_LINE2,details.getmSocietyAddressLine2());
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_CITY,details.getmSocietyAddressCity());
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_STATE,details.getmSocietyAddressState());
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_PIN,details.getmSocietyAddressPIN());	
		try {
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.SOCIETY_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			mDb.endTransaction();
		}
		return isAdded;		
	}

	public Cursor getSocietyDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.SOCIETY_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;		
	}

	public boolean saveFlatOwnerDeatils(FlatOwnerDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();
		values.put(TableFlatOwnerDetails.USERNAME, details.getmUsername());
		values.put(TableFlatOwnerDetails.PASSWORD, details.getmPassword());
		values.put(TableFlatOwnerDetails.SECURITY_QUESTION, details.getmSecurityQuestion());
		values.put(TableFlatOwnerDetails.ANSWER, details.getmAnswer());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_NAME, details.getmFlatOwnerName());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_DOB, details.getmFlatOwnerDOB());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_AGE, details.getmFlatOwnerAge());
		values.put(TableFlatOwnerDetails.GENDER, details.getmGender());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_CONTACT_NO, details.getmFlatOwnerContactNo());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_EMAIL_ID, details.getmFlatOwnerEmailId());
		values.put(TableFlatOwnerDetails.FLAT_BUILDING_NAME, details.getmBuildingName());
		values.put(TableFlatOwnerDetails.FLOOR_NO, details.getmFloorNo());		
		values.put(TableFlatOwnerDetails.FLAT_NO, details.getmFlatno());
		values.put(TableFlatOwnerDetails.SOCIETY_CODE, details.getmSocietyCode());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_CREATED_DATETIME, details.getmFlatOwnerCreatedDateTime());
		values.put(TableFlatOwnerDetails.NO_OF_FAMILY_MEMBER, details.getmNoofFamilyMembers());
		values.put(TableFlatOwnerDetails.NO_OF_VEHICLE, details.getmNoofVehicles());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_CODE, details.getmFlatOwnerCode());
		values.put(TableFlatOwnerDetails.PUSH_TOKEN, details.getmPushToken());
		values.put(TableFlatOwnerDetails.ACCESS_TOKEN, details.getmAccessToken());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_LATITUDE, details.getmLatitude());
		values.put(TableFlatOwnerDetails.FLAT_OWNER_LONGITUDE, details.getmLongitude());

		try {
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.FLAT_OWNER_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			mDb.endTransaction();
		}	
		return isAdded;
	}


	public Cursor getFlatOwnerDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.FLAT_OWNER_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;		
	}

	public boolean saveFamilyDetails(FamilyDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();

		values.put(TableFlatOwnerFamilyDetails.FLAT_OWNER_CODE, details.getmFlatOwnerCode());
		values.put(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_NAME, details.getmFamilyMemberName());
		values.put(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_DOB, details.getmFamilyMemberDOB());
		values.put(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_RELATION, details.getmFamilyMemberRelation());
		values.put(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_CONTACT_NO, details.getmFamilyMemberContactno());
		values.put(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_AGE, details.getmFamilyMemberAge());
		values.put(TableFlatOwnerFamilyDetails.FAMILY_MEMBER_USERNAME, details.getmFamilyMemberUsername());
		values.put(TableFlatOwnerFamilyDetails.NEED_LOGIN, details.ismNeedLogin());

		try {
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.FAMILY_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			mDb.endTransaction();
		}	
		return isAdded;
	}

	public Cursor getFamilyDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.FAMILY_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;		
	}

	public boolean saveVehicleDetails(VehicleDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();		
		values.put(TableFlatOwnerVehicleDetails.VEHICLE_TYPE, details.getmVehicleType());
		values.put(TableFlatOwnerVehicleDetails.VEHICLE_NUMBER, details.getmVehicleNumber());
		values.put(TableFlatOwnerVehicleDetails.VEHICLE_COMPANY, details.getmVehicleCompany());
		values.put(TableFlatOwnerVehicleDetails.VEHICLE_MODEL, details.getmVehicleModel());
		values.put(TableFlatOwnerVehicleDetails.VEHICLE_COLOR, details.getmVehicleColor());

		try {
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.VEHICLE_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			mDb.endTransaction();
		}	
		return isAdded;
	}

	public Cursor getVehicleDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.VEHICLE_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;	
	}

	/*	public boolean saveComplaintDetails(ComplaintDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();		
		values.put(TableFlatOwnerComplaintDetails.COMPLAINT_NUMBER, details.getmComplaintNumber());
		values.put(TableFlatOwnerComplaintDetails.COMPLAINT_TYPE, details.getmComplaintType());
		values.put(TableFlatOwnerComplaintDetails.COMPLAINT_STATUS, details.getmComplaintStatus());
		values.put(TableFlatOwnerComplaintDetails.COMPLAINT_DETAILS, details.getmComplaintDetails());
		values.put(TableFlatOwnerComplaintDetails.COMPLAINT_RAISED_DATETIME, details.getmComplaintRaisedDateTime());

		try {
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.COMPLAINT_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			mDb.endTransaction();
		}	
		return isAdded;
	}*/

	public Cursor getAllComplaintDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.COMPLAINT_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public boolean saveRequestDetails(RequestDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();		
		values.put(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER, details.getmRequestNumber());
		values.put(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_TYPE, details.getmRequestType());
		values.put(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_CATEGORY, details.getmRequestCategory());
		values.put(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_PRIORITY, details.getmRequestPriority());
		values.put(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS, details.getmRequestStatus());
		values.put(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DETAILS, details.getmRequestDetails());
		values.put(TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME, details.getmRequestDateTime());
		try {
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.REQUEST_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			mDb.endTransaction();
		}	
		return isAdded;
	}

	public Cursor getAllRequestDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.REQUEST_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public boolean saveQueryDetails(QueryDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();		
		values.put(TableFlatOwnerQueryDetails.QUERY_NUMBER, details.getmQueryNumber());
		values.put(TableFlatOwnerQueryDetails.QUERY_STATUS, details.getmQueryStatus());
		values.put(TableFlatOwnerQueryDetails.QUERY_DETAILS, details.getmQueryDetails());
		values.put(TableFlatOwnerQueryDetails.QUERY_DATETIME, details.getmQueryDateTime());
		try {
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.QUERY_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			mDb.endTransaction();
		}	
		return isAdded;
	}

	public Cursor getAllQueryDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.QUERY_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public boolean saveSocietyNoticeDetails(NoticeDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();		
		values.put(TableSocietyNotices.NOTICE_NUMBER, details.getmNoticeNumber());
		values.put(TableSocietyNotices.NOTICE_TO, details.getmNoticeTo());
		values.put(TableSocietyNotices.NOTICE_SUBJECT, details.getmNoticeSubject());
		values.put(TableSocietyNotices.NOTICE_MESSAGE, details.getmNoticeMessage());
		values.put(TableSocietyNotices.NOTICE_DATETIME, details.getmNoticeDateTime());
		try {			
			if(getSingleNotice(details.getmNoticeNumber()).getCount()<=0)
		{
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.SOCIETY_NOTICES, null, values) > 0;
			mDb.setTransactionSuccessful();
			mDb.endTransaction();
		}
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			//mDb.endTransaction();
		}	
		return isAdded;
	}
	
	private Cursor getSingleNotice(String noticeNumber){
		String selectQuery = "SELECT  * FROM " + TableNames.SOCIETY_NOTICES + " WHERE " + TableSocietyNotices.NOTICE_NUMBER +"= '"+ noticeNumber+"'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getAllSocietyNoticeDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.SOCIETY_NOTICES;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	/*	public Cursor getRaisedComplaintDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.COMPLAINT_DETAILS + " WHERE "+TableFlatOwnerComplaintDetails.COMPLAINT_STATUS +" IN ('Raised','Processed')";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getClosedComplaintDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.COMPLAINT_DETAILS + " WHERE "+TableFlatOwnerComplaintDetails.COMPLAINT_STATUS +" = 'Closed'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}*/

	public Cursor getRaisedRequestDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.REQUEST_DETAILS + " WHERE "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS +" IN ('Raised','Processed') ORDER BY "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_DATETIME + "  DESC";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getRaisedRequestDetailsByType(){
		String selectQuery = "SELECT  * FROM " + TableNames.REQUEST_DETAILS + " WHERE "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS +" IN ('Raised','Processed') ORDER BY "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_TYPE + "  ASC";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getRaisedRequestDetailsByCategory(){
		String selectQuery = "SELECT  * FROM " + TableNames.REQUEST_DETAILS + " WHERE "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS +" IN ('Raised','Processed') ORDER BY "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_CATEGORY + "  ASC";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getRaisedRequestDetailsByPriorityHtoL(){
		String selectQuery = "SELECT  * FROM " + TableNames.REQUEST_DETAILS + " WHERE "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS +" IN ('Raised','Processed') ORDER BY "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_PRIORITY + "  ASC";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getRaisedRequestDetailsByPriorityLtoH(){
		String selectQuery = "SELECT  * FROM " + TableNames.REQUEST_DETAILS + " WHERE "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS +" IN ('Raised','Processed') ORDER BY "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_PRIORITY + "  DESC";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}



	public Cursor getClosedRequestDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.REQUEST_DETAILS + " WHERE "+TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_STATUS +" = 'Closed'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getRaisedQueryDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.QUERY_DETAILS + " WHERE "+TableFlatOwnerQueryDetails.QUERY_STATUS +" IN ('Raised','Processed')";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getClosedQueryDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.QUERY_DETAILS + " WHERE "+TableFlatOwnerQueryDetails.QUERY_STATUS +" = 'Closed'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public Cursor getSinbleRequestDetails(String requestNumber){
		String selectQuery = "SELECT  * FROM " + TableNames.REQUEST_DETAILS + " WHERE " + TableFlatOwnerRequestDetails.REQUEST_COMPLAINT_NUMBER +"= '"+ requestNumber+"'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	public boolean saveMessage(RequestMessages message){
		boolean isAdded = false;
		ContentValues values = new ContentValues();		
		values.put(TableMessageDetails.MESSAGE_NUMBER,message.getmMessageNumber());
		values.put(TableMessageDetails.MESSAGE_CONTENT,message.getmMessageContent());
		values.put(TableMessageDetails.REQUEST_NUMBER,message.getmRequestNumber());
		values.put(TableMessageDetails.IS_SOCIETY_MESSAGE,message.ismIsSocietyMessage());
		values.put(TableMessageDetails.IS_READ,message.ismIsRead());
		values.put(TableMessageDetails.MESSAGE_DATETIME,message.getmMessageDateTime());

		try {
			if(getSingleMessages(message.getmMessageNumber()).getCount()<=0)
			{
				mDb.beginTransaction();
				isAdded = mDb.insert(TableNames.MESSAGE_DETAILS, null, values) > 0;
				mDb.setTransactionSuccessful();	
				mDb.endTransaction();
			}
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
		//	mDb.endTransaction();
		}	
		return isAdded;	
	}

	public Cursor getMessages(String requestNumber){
		String selectQuery = "SELECT  * FROM " + TableNames.MESSAGE_DETAILS + " WHERE " + TableMessageDetails.REQUEST_NUMBER +"= '"+ requestNumber+"' ORDER BY "+TableMessageDetails.MESSAGE_DATETIME + "  ASC";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}

	private Cursor getSingleMessages(String messageNumber){
		String selectQuery = "SELECT  * FROM " + TableNames.MESSAGE_DETAILS + " WHERE " + TableMessageDetails.MESSAGE_NUMBER +"= '"+ messageNumber+"'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}
	
	public boolean saveContact(ContactDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();
		values.put(TableContactDetails.CONTACT_NAME,details.getmContactName());
		values.put(TableContactDetails.CONTACT_NUMBER,details.getmContactNumber());
		values.put(TableContactDetails.CONTACT_EMAIL_ID,details.getmContactEmailId());
		values.put(TableContactDetails.CONTACT_OCCUPATION,details.getmContactOccupation());
	try 
		{
		if(getContact(details.getmContactName(), details.getmContactNumber(), details.getmContactOccupation()).getCount()<=0)
		{
			mDb.beginTransaction();
			isAdded = mDb.insert(TableNames.CONTACT_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
			mDb.endTransaction();
		}
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			//mDb.endTransaction();
		}
		return isAdded;		
	}
	
	public Cursor getAllContacts(){

		String selectQuery = "SELECT  * FROM " + TableNames.CONTACT_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;		
	
	}
	
	private Cursor getContact(String name, String number, String occupation){
		String selectQuery = "SELECT  * FROM " + TableNames.CONTACT_DETAILS + " WHERE " + TableContactDetails.CONTACT_NAME +"= '"+ name+"' AND "+TableContactDetails.CONTACT_NUMBER + "='"+number+"' AND "+
								TableContactDetails.CONTACT_OCCUPATION+"='"+occupation+"'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}
	
	public boolean saveVisitor(VisitorDetails details){
		boolean isAdded = false;
		ContentValues values = new ContentValues();
		values.put(TableVisitorDetails.VISITOR_CODE,details.getmVisitorCode());
		values.put(TableVisitorDetails.VISITOR_NAME,details.getmVisitorName());
		values.put(TableVisitorDetails.VISITOR_IN_TIME,details.getmVisitorInTime());
		values.put(TableVisitorDetails.NO_OF_VISITORS,details.getmNoofVisitors());
		values.put(TableVisitorDetails.VISIT_PURPOSE,details.getmVisitPurpose());
		values.put(TableVisitorDetails.VISITOR_CONTACT_NO,details.getmVisitorContacNo());
		values.put(TableVisitorDetails.VISITOR_VEHICLE_NO,details.getmVisitorVehicleNo());
		try 
		{
			if(getSingleVisitor(details.getmVisitorCode()).getCount()<=0)
			{
				mDb.beginTransaction();
				isAdded = mDb.insert(TableNames.VISITOR_DETAILS, null, values) > 0;
				mDb.setTransactionSuccessful();
				mDb.endTransaction();
			}
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			//mDb.endTransaction();
		}
		return isAdded;	
	}
	
	public Cursor getVisitors(){
		String selectQuery = "SELECT  * FROM " + TableNames.VISITOR_DETAILS +" ORDER BY "+TableVisitorDetails.VISITOR_IN_TIME + "  DESC";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;		
	}
	
	private Cursor getSingleVisitor(String visitorCode){
		String selectQuery = "SELECT  * FROM " + TableNames.VISITOR_DETAILS + " WHERE " + TableVisitorDetails.VISITOR_CODE +"= '"+ visitorCode+"'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}
	
	public void deleteDataFromAllTables()
	{
		deleteFlatOwnerDetailsTable();
		deleteSocietyDetailsTable();
		deleteFamilyDetailsTable();
		deleteVehicleDetailsTable();
		deleteRequestDetailsTable();
		deleteSocietyNoticesTable();
		deleteContactDetailsTable();
		deleteMessageDetailsTable();
		deleteVisitorDetailsTable();
	}
	
	public void deleteDataFromSocietyDetails(){
		deleteSocietyDetailsTable();
	}

	private void deleteFlatOwnerDetailsTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.FLAT_OWNER_DETAILS);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}			
	}

	private void deleteSocietyDetailsTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.SOCIETY_DETAILS);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}					
	}

	private void deleteFamilyDetailsTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.FAMILY_DETAILS);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}					
	}

	private void deleteVehicleDetailsTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.VEHICLE_DETAILS);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}				
	}

	private void deleteRequestDetailsTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.REQUEST_DETAILS);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}					
	}


	private void deleteSocietyNoticesTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.SOCIETY_NOTICES);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}					
	}

	private void deleteContactDetailsTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.CONTACT_DETAILS);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}					
	}

	private void deleteMessageDetailsTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.MESSAGE_DETAILS);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}					
	}

	private void deleteVisitorDetailsTable(){
		try {
			mDb.beginTransaction();
			mDb.execSQL("DELETE  FROM "+ TableNames.VISITOR_DETAILS);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}				
	}
	
	public Cursor getUnreadMessageCountForRequest(String requestNumber){
		String selectQuery = "SELECT  * FROM " + TableNames.MESSAGE_DETAILS + " WHERE " + TableMessageDetails.REQUEST_NUMBER +"= '"+ requestNumber+"' AND "+TableMessageDetails.IS_READ+" = '0'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}
	
	public void setMessagesRead(String requestNumber){
		try {
			mDb.beginTransaction();
			String selectQuery = "UPDATE "+ TableNames.MESSAGE_DETAILS +" SET "+TableMessageDetails.IS_READ +" = '1' WHERE " + TableMessageDetails.REQUEST_NUMBER +"= '"+ requestNumber+"'";
			mDb.execSQL(selectQuery);
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			mDb.endTransaction();
		}					
	}
	
	public Cursor getNoticeDetails(String noticeNumber){
		String selectQuery = "SELECT  * FROM " + TableNames.SOCIETY_NOTICES + " WHERE " + TableSocietyNotices.NOTICE_NUMBER +"= '"+ noticeNumber+"'";
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;			
	}
	

}
