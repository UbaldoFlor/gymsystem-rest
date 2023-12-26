package com.gymsystem.rest.requests;

public class TraineeStatusRequest {
	
	private String username;
	private boolean isActive;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
