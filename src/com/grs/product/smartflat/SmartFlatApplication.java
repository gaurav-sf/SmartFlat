package com.grs.product.smartflat;

import com.grs.product.smartflat.database.SmartFlatDatabase;
import android.app.Application;

public class SmartFlatApplication extends Application{
	
	private static SmartFlatApplication singleton;
	
	public static SmartFlatApplication getInstance() {
		return singleton;
	}
	
	protected void init(){
		singleton = this;
	}
	
	@Override
	public void onCreate() {
		this.init();
		SmartFlatDatabase.getInstance();
		super.onCreate();
	}

}
