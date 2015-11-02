package com.grs.product.smartflat.apicall;

import com.grs.product.smartflat.error.SmartFlatError;


public interface AsyncTaskCompleteListener<T>
{
    public abstract void onStarted(); 
	public abstract void onTaskComplete(T result);
	public abstract void onStoped(); 
	public abstract void onStopedWithError(SmartFlatError e);
}
