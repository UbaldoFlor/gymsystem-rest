package com.gymsystem.rest.requests;

public class TrainerProfileRequest {
	
	private String username;
	private String firstName;
	private String lastName;
	private Long specialization;
	private boolean isActive;
	
	// Getters and setters
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getSpecialization() {
		return specialization;
	}
	public void setSpecialization(Long specialization) {
		this.specialization = specialization;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
