package com.grs.product.smartflat.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.text.format.Formatter;

public class NetworkDetector {
	private static NetworkDetector sInstance;
	private final Context mContext;
	private ConnectivityManager connectivity;

	/**
	 * 
	 * @param context
	 */
	private NetworkDetector(Context context) {
		mContext = context.getApplicationContext();
		connectivity = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public static NetworkDetector init(Context context) {
		if (sInstance == null) 
		{
			synchronized (NetworkDetector.class) 
			{
				if(sInstance == null)
			        sInstance = new NetworkDetector(context);
			}
		}
		return sInstance;
	}

	/**
	 * Check Internet Connectivity
	 * 
	 * @return
	 */
	public boolean isNetworkAvailable() {
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			return info != null && info.isConnected();
		}
		return false;
	}

	public boolean isUsingWiFi() {
		if (connectivity != null) {
			NetworkInfo wifiInfo = connectivity
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (wifiInfo.getState() == NetworkInfo.State.CONNECTED
					|| wifiInfo.getState() == NetworkInfo.State.CONNECTING) {
				return true;
			}
		}
		return false;
	}

	public String FormatIP() {
		WifiManager wifiManager = (WifiManager) mContext
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		if (wifiInfo != null) {
			int ipAddress = wifiInfo.getIpAddress();
			return Formatter.formatIpAddress(ipAddress);
		}
		return "";
	}

	public String getIPaddress() {
		String ipAddress = "";
		if (isUsingWiFi()) {
			ipAddress = FormatIP();
		}

		return ipAddress;
	}

	public boolean isLanConnected() {
		if (connectivity != null) {
			NetworkInfo wifiInfo = connectivity
				.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);

			if (wifiInfo.getState() == NetworkInfo.State.CONNECTED
					|| wifiInfo.getState() == NetworkInfo.State.CONNECTING) {
				return true;
			}
		}
		return false;
	}
	
	public int getNetworkType() {
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null) {
				return info.getType();
			}
		}
		return ConnectivityManager.TYPE_DUMMY;
	}
	
	public int getSignalIconLevel() {
		int signalIconLevel =  0;

		if (getNetworkType() == ConnectivityManager.TYPE_WIFI) {
			final WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
			final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
			if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
				signalIconLevel = getWiFiSignalIconLevel(connectionInfo.getRssi());
			}
		}
		
		return signalIconLevel;		
	}
	
	private int getWiFiSignalIconLevel(int signalLevel) {
		int numberOfLevels=3;
		int wifiSignalIconLevel = WifiManager.calculateSignalLevel(signalLevel, numberOfLevels);
		return wifiSignalIconLevel;
	}
	
	public int getWifiSignalLevel() {
		int wifiSignalLevel = 0;
		if (getNetworkType() == ConnectivityManager.TYPE_WIFI) {
			final WifiManager wifiManager = (WifiManager) mContext
					.getSystemService(Context.WIFI_SERVICE);
			final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
			if (connectionInfo != null
					&& !TextUtils.isEmpty(connectionInfo.getSSID())) {
				wifiSignalLevel = connectionInfo.getRssi();
			}
		}
		return wifiSignalLevel;
	}
}
