package com.gymsystem.rest.requests;

public class TrainerRequest {
	
	private String firstName;
	private String lastName;
	private Long specialization;
	
	// Getters and setters
	
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
}
