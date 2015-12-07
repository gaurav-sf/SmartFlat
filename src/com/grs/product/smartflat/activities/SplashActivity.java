package com.grs.product.smartflat.activities;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.SmartFlatApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class SplashActivity extends Activity {

	private Animation mZoomIn;
	private TextView mTextViewAppName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
 
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_splash);
        mTextViewAppName = (TextView) findViewById(R.id.tv_title);
        mZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.zoom_in);
        addListner();
        mTextViewAppName.setAnimation(mZoomIn);
       // goToNextActivity();
    }
    
    private void addListner(){
    	mZoomIn.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				goToNextActivity();
			}
		});
    }
    
/*    private void goToNextActivity(){
    	First we check for society Code, if it is not null in SharedPreferences 
    	means society details are there with us so directly we can go to registration or home screen
    	otherwise launch society code screen
    	if(SmartFlatApplication.getSocietyCodeFromSharedPreferences() == null||SmartFlatApplication.getSocietyCodeFromSharedPreferences().equals(null))
    	{
    		Intent goToSocietyCode = new Intent(SplashActivity.this,StartActivity.class);
    		startActivity(goToSocietyCode);
    		finish();
    		
    	}else{
    		//If we have Society code present. Then will check for flat owner code.
    		//If it is there means registration is done so that we can directly go to Login Page or we need to launch registration page
    		if(SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences() == null || SmartFlatApplication.getFlatOwnerCodeFromSharedPreferences().equals(null)){
        		Intent goToRegistration = new Intent(SplashActivity.this,RegistrationStep1Activity.class);
        		startActivity(goToRegistration);
        		finish();
    			
    		}else if(SmartFlatApplication.getFlatOwnerAccessCodeFromSharedPreferences()==null || SmartFlatApplication.getFlatOwnerAccessCodeFromSharedPreferences().equals(null)){
    			//Need to call HomeActivity once it is created.
        		Intent goToLoginScreen = new Intent(SplashActivity.this,LoginActivity.class);
        		startActivity(goToLoginScreen);
        		finish();
    			
    		}else{
        		Intent goToDashboardScreen = new Intent(SplashActivity.this,DashBoardActivity.class);
        		startActivity(goToDashboardScreen);
        		finish();
    			
    		}
    		
    	}
    }*/
    
    private void goToNextActivity(){

    	if(SmartFlatApplication.getFlatOwnerAccessCodeFromSharedPreferences()==null || SmartFlatApplication.getFlatOwnerAccessCodeFromSharedPreferences().equals(null)){    		
    		Intent goToLoginScreen = new Intent(SplashActivity.this,LoginActivity.class);
    		startActivity(goToLoginScreen);
    		finish();
    	}else{
    		Intent goToDashboardScreen = new Intent(SplashActivity.this,DashBoardActivity.class);
    		startActivity(goToDashboardScreen);
    		finish();
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
