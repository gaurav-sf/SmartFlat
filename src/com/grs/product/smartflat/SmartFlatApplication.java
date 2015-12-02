package com.grs.product.smartflat;

import com.grs.product.smartflat.database.SmartFlatDatabase;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SmartFlatApplication extends Application{
	
	private static SmartFlatApplication singleton;
	private static SharedPreferences sharedpreferences;
	private static String SMARTFLATSHAREDPREFERENCES = "SmartFlatSP";
	
	public static SmartFlatApplication getInstance() {
		return singleton;
	}
	
	protected void init(){
		singleton = this;
		sharedpreferences = getSharedPreferences(SMARTFLATSHAREDPREFERENCES, Context.MODE_PRIVATE);
	}
	
	@Override
	public void onCreate() {
		this.init();
		SmartFlatDatabase.getInstance();
		super.onCreate();
	}
	
	public static void saveSocietyCodeInSharedPreferences(String societycode){
		   SharedPreferences.Editor editor = sharedpreferences.edit();           
           editor.putString("SocietyCode", societycode);
           editor.commit();	
	}
	
	public static String getSocietyCodeFromSharedPreferences(){		
		String restoredText = sharedpreferences.getString("SocietyCode", null);		
		return restoredText;	
	}
	
	public static void saveFlatOwnerCodeInSharedPreferences(String flatOwnerCode){
		SharedPreferences.Editor editor = sharedpreferences.edit();           
        editor.putString("FlatOwnerCode", flatOwnerCode);
        editor.commit();	
	}
	
	public static String getFlatOwnerCodeFromSharedPreferences(){		
		String restoredText = sharedpreferences.getString("FlatOwnerCode", null);		
		return restoredText;	
	}
	
	public static void saveFlatOwnerAccessCodeInSharedPreferences(String flatOwnerCode){
		SharedPreferences.Editor editor = sharedpreferences.edit();           
        editor.putString("FlatOwnerAccessCode", flatOwnerCode);
        editor.commit();	
	}
	
	public static String getFlatOwnerAccessCodeFromSharedPreferences(){		
		String restoredText = sharedpreferences.getString("FlatOwnerAccessCode", null);		
		return restoredText;	
	}
	
	public static void saveRequestLastSyncTime(String time){
		SharedPreferences.Editor editor = sharedpreferences.edit();           
        editor.putString("RequestLastSyncTime", time);
        editor.commit();	
	}
	
	public static String getRequestLastSyncTime(){		
		String restoredText = sharedpreferences.getString("RequestLastSyncTime", null);		
		return restoredText;	
	}
	
	public static void saveNoticeLastSyncTime(String time){
		SharedPreferences.Editor editor = sharedpreferences.edit();           
        editor.putString("NoticeLastSyncTime", time);
        editor.commit();	
	}
	
	public static String getNoticeLastSyncTime(){		
		String restoredText = sharedpreferences.getString("NoticeLastSyncTime", null);		
		return restoredText;	
	}
	
	public static void saveFlatOwnerPushTokenInSharedPreferences(String flatOwnerCode){
		SharedPreferences.Editor editor = sharedpreferences.edit();           
        editor.putString("FlatOwnerPushToken", flatOwnerCode);
        editor.commit();	
	}
	
	public static String getFlatOwnerPushTokenFromSharedPreferences(){		
		String restoredText = sharedpreferences.getString("FlatOwnerPushToken", null);		
		return restoredText;	
	}

}
