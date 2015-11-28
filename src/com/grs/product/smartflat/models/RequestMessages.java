package com.grs.product.smartflat.models;

public class RequestMessages {
	
	private String mMessageNumber;
	private String mMessageContent;
	private String mRequestNumber;
	private String mSocietyCode;
	private String mFlatOwnerCode;	
	private String mMessageDateTime;
	private boolean mIsSocietyMessage;
	
	public String getmMessageNumber() {
		return mMessageNumber;
	}
	
	public void setmMessageNumber(String mMessageNumber) {
		this.mMessageNumber = mMessageNumber;
	}
	
	public String getmMessageContent() {
		return mMessageContent;
	}
	
	public void setmMessageContent(String mMessageContent) {
		this.mMessageContent = mMessageContent;
	}
	
	public String getmRequestNumber() {
		return mRequestNumber;
	}
	
	public void setmRequestNumber(String mRequestNumber) {
		this.mRequestNumber = mRequestNumber;
	}
	
	public String getmSocietyCode() {
		return mSocietyCode;
	}
	public void setmSocietyCode(String mSocietyCode) {
		this.mSocietyCode = mSocietyCode;
	}
	
	public String getmFlatOwnerCode() {
		return mFlatOwnerCode;
	}
	
	public void setmFlatOwnerCode(String mFlatOwnerCode) {
		this.mFlatOwnerCode = mFlatOwnerCode;
	}
	
	public String getmMessageDateTime() {
		return mMessageDateTime;
	}
	
	public void setmMessageDateTime(String mMessageDateTime) {
		this.mMessageDateTime = mMessageDateTime;
	}

	public boolean ismIsSocietyMessage() {
		return mIsSocietyMessage;
	}

	public void setmIsSocietyMessage(boolean mIsSocietyMessage) {
		this.mIsSocietyMessage = mIsSocietyMessage;
	}


}
