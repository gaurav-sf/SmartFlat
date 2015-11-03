package com.grs.product.smartflat.database;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.grs.product.smartflat.SmartFlatApplication;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SmartFlatDatabase {
	private SQLiteDatabase mDb;
	private SQLiteOpenHelper helper;
	private static SmartFlatDatabase instance;
	private int openCount = 0;
	private static final String DATABASE_NAME = "SmartFlat.db";
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

	private void createFlatOwnerDetailsTable(SQLiteDatabase db){
		db.beginTransaction();
		db.execSQL(SmartFlatDBTableCreation.TABLE_FLAT_OWNER_DETAILS_CREATION_QUERY);
		db.setTransactionSuccessful();	
	}
	
	private void createSocietyDetailsTable(SQLiteDatabase db){
		db.beginTransaction();
		db.execSQL(SmartFlatDBTableCreation.TABLE_SOCIETY_DETAILS_CREATION_QUERY);
		db.setTransactionSuccessful();	
	}
	
	private void createFamilyDetailsTable(SQLiteDatabase db){
		db.beginTransaction();
		db.execSQL(SmartFlatDBTableCreation.TABLE_FAMILY_DETAILS_CREATION_QUERY);
		db.setTransactionSuccessful();	
	}
	
	private void createVehicleDetailsTable(SQLiteDatabase db){
		db.beginTransaction();
		db.execSQL(SmartFlatDBTableCreation.TABLE_VEHICLE_DETAILS_CREATION_QUERY);
		db.setTransactionSuccessful();	
	}
	
	private void createComplaintDetailsTable(SQLiteDatabase db){
		db.beginTransaction();
		db.execSQL(SmartFlatDBTableCreation.TABLE_COMPLAINT_DETAILS_CREATION_QUERY);
		db.setTransactionSuccessful();	
	}
	
	private void createRequestDetailsTable(SQLiteDatabase db){
		db.beginTransaction();
		db.execSQL(SmartFlatDBTableCreation.TABLE_REQUEST_DETAILS_CREATION_QUERY);
		db.setTransactionSuccessful();	
	}
	
	private void createQueryDetailsTable(SQLiteDatabase db){
		db.beginTransaction();
		db.execSQL(SmartFlatDBTableCreation.TABLE_QUERY_DETAILS_CREATION_QUERY);
		db.setTransactionSuccessful();	
	}
	
	private void createSocietyNoticesTable(SQLiteDatabase db){
		db.beginTransaction();
		db.execSQL(SmartFlatDBTableCreation.TABLE_SOCIETY_NOTICES_CREATION_QUERY);
		db.setTransactionSuccessful();	
	}


	//inner class
	private class SmartFlatDatabaseHelper extends SQLiteOpenHelper{

		public SmartFlatDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			createFlatOwnerDetailsTable(db);
			createSocietyDetailsTable(db);
			createFamilyDetailsTable(db);
			createVehicleDetailsTable(db);
			createComplaintDetailsTable(db);
			createRequestDetailsTable(db);
			createQueryDetailsTable(db);
			createSocietyNoticesTable(db);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}

}
