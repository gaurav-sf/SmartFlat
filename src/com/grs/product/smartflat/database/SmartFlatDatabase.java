package com.grs.product.smartflat.database;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.grs.product.smartflat.SmartFlatApplication;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableNames;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableSocietyDetails;
import com.grs.product.smartflat.models.SocietyDetails;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

	private void createComplaintDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_COMPLAINT_DETAILS_CREATION_QUERY);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}	
	}

	private void createRequestDetailsTable(SQLiteDatabase db){
		try {
			db.beginTransaction();
			db.execSQL(SmartFlatDBTableCreation.TABLE_REQUEST_DETAILS_CREATION_QUERY);
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
				createComplaintDetailsTable(db);
				createRequestDetailsTable(db);
				createQueryDetailsTable(db);
				createSocietyNoticesTable(db);
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

		boolean IsAdded = false;
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
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_LINE2,details.getmSocietyOwnerAddressLine2());
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_CITY,details.getmSocietyAddressCity());
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_STATE,details.getmSocietyAddressState());
		values.put(TableSocietyDetails.SOCIETY_ADDRESS_PIN,details.getmSocietyAddressPIN());	
		try {
			mDb.beginTransaction();

			IsAdded = mDb.insert(TableNames.SOCIETY_DETAILS, null, values) > 0;
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Error in transaction", e.toString());
		} finally {
			mDb.endTransaction();
		}
		return IsAdded;		
	}
	
	public Cursor getSocietyDetails(){
		String selectQuery = "SELECT  * FROM " + TableNames.SOCIETY_DETAILS;
		Cursor cursor = mDb.rawQuery(selectQuery, null);	
		if (cursor != null && cursor.getCount()>0) {
			cursor.moveToNext();
		}
		return cursor;
		
	}

}
