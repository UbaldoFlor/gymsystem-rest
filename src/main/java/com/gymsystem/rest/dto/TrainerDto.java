package com.gymsystem.rest.dto;

public class TrainerDto {
	
	private String username;
	private String firstName;
	private String lastName;
	private Long specialization;
	
	public TrainerDto(String username, String firstName, String lastName, Long specialization) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialization = specialization;
	}
	
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
}
