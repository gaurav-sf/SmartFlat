package com.grs.product.smartflat.models;

import java.util.List;

public class RequestDetails {
	
	private String mRequestNumber;
	private String mRequestType;	
	private String mRequestCategory;
	private String mRequestPriority;	
	private String mRequestDateTime;	
	private String mRequestStatus;
	private String mRequestDetails;
	private List<RequestMessages> mMessageList;
	private int mUnreadMessageCount;
	
	public String getmRequestNumber() {
		return mRequestNumber;
	}
	
	public void setmRequestNumber(String mRequestNumber) {
		this.mRequestNumber = mRequestNumber;
	}
	
	public String getmRequestType() {
		return mRequestType;
	}
	
	public void setmRequestType(String mRequestType) {
		this.mRequestType = mRequestType;
	}
	
	public String getmRequestCategory() {
		return mRequestCategory;
	}

	public void setmRequestCategory(String mRequestCategory) {
		this.mRequestCategory = mRequestCategory;
	}

	public String getmRequestPriority() {
		return mRequestPriority;
	}
	
	public void setmRequestPriority(String mRequestPriority) {
		this.mRequestPriority = mRequestPriority;
	}
	
	public String getmRequestDateTime() {
		return mRequestDateTime;
	}
	
	public void setmRequestDateTime(String mRequestDateTime) {
		this.mRequestDateTime = mRequestDateTime;
	}
	
	public String getmRequestStatus() {
		return mRequestStatus;
	}
	
	public void setmRequestStatus(String mRequestStatus) {
		this.mRequestStatus = mRequestStatus;
	}
	
	public String getmRequestDetails() {
		return mRequestDetails;
	}
	
	public void setmRequestDetails(String mRequestDetails) {
		this.mRequestDetails = mRequestDetails;
	}

	public List<RequestMessages> getmMessageList() {
		return mMessageList;
	}

	public void setmMessageList(List<RequestMessages> mMessageList) {
		this.mMessageList = mMessageList;
	}

	public int getmUnreadMessageCount() {
		return mUnreadMessageCount;
	}

	public void setmUnreadMessageCount(int mUnreadMessageCount) {
		this.mUnreadMessageCount = mUnreadMessageCount;
	}

}
