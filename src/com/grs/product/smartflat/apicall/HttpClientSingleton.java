package com.grs.product.smartflat.apicall;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClientSingleton {

	public static final int JSON_CONNECTION_TIMEOUT = 40000;
	public static final int JSON_SOCKET_TIMEOUT = 40000;
	private static HttpClientSingleton instance;
	private HttpParams httpParameters;
	private HttpClient  httpclient;

	private void setTimeOut(HttpParams params) {
		HttpConnectionParams.setConnectionTimeout(params,
				JSON_CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, JSON_SOCKET_TIMEOUT);
	}

	private HttpClientSingleton() {
		httpParameters = new BasicHttpParams();
		setTimeOut(httpParameters);
		httpclient = new DefaultHttpClient(httpParameters);
	}

	public static HttpClient getHttpClientInstace() {
		if (instance == null)
		{
			synchronized (HttpClientSingleton.class) 
			{
				if(instance == null)
					instance = new HttpClientSingleton();
			}
		}
		return instance.httpclient;
	}
}
