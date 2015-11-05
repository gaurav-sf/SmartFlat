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

}
