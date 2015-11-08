package com.grs.product.smartflat.response;

public class Response {
	private String mStatus;
	private String mMessage;
	private String mUserCode;

	public Response() {
		super();
	}

	public Response(String status, String message, String userCode) {
		this.mStatus = status;
		this.mMessage = message;
		this.mUserCode = userCode;

	}


	public void setStatus(String status) {
		this.mStatus = status;
	}

	public String getStatus() {
		return mStatus;
	}

	public void setMessage(String message) {
		this.mMessage = message;
	}

	public String getMessage() {
		return mMessage;
	}

	public void setRole(String role) {
		this.mUserCode = role;
	}

	public String getRole() {
		return mUserCode;
	}
}