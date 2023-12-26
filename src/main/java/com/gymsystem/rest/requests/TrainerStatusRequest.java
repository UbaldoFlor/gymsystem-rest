package com.gymsystem.rest.requests;

public class TrainerStatusRequest {
	
	private String username;
	private boolean isActive;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
