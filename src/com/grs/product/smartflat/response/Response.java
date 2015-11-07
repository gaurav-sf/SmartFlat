package com.grs.product.smartflat.response;

public class Response {
	private String mStatus;
	private String mMessage;
	private String mRole;

	public Response() {
		super();
	}

	public Response(String status, String message, String role) {
		this.mStatus = status;
		this.mMessage = message;
		this.mRole = role;

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
		this.mRole = role;
	}

	public String getRole() {
		return mRole;
	}
}