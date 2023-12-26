package com.gymsystem.rest.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymsystem.rest.dto.TraineeDto;
import com.gymsystem.rest.model.Trainee;

public class TrainerProfileResponse {
	
	private String username;
	private String firstName;
	private String lastName;
	private Long specialization;
	private boolean isActive;
	private Set<Trainee> trainees;
	private List<TraineeDto> dtoTrainees;
	
	public TrainerProfileResponse(String username, String firstName, String lastName, Long specialization, boolean isActive,
			Set<Trainee> trainees) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialization = specialization;
		this.isActive = isActive;
		this.trainees = trainees;
		this.dtoTrainees = createDtoTrainees();
	}
	
	public TrainerProfileResponse(String firstName, String lastName, Long specialization, Boolean isActive,
			Set<Trainee> trainees) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialization = specialization;
		this.isActive = isActive;
		this.trainees = trainees;
		this.dtoTrainees = createDtoTrainees();
	}

	private List<TraineeDto> createDtoTrainees() {
		List<TraineeDto> dtoTrainees = new ArrayList<>();
		for(Trainee trainee : this.trainees) {
			String username = trainee.getUser().getUsername();
			String firstName = trainee.getUser().getFirstName();
			String lastName = trainee.getUser().getLastName();
			TraineeDto traineeDto = new TraineeDto(username, firstName, lastName);
			dtoTrainees.add(traineeDto);
		}
		
		return dtoTrainees;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@JsonIgnore
	public Set<Trainee> getTrainees() {
		return trainees;
	}
	public void setTrainees(Set<Trainee> trainees) {
		this.trainees = trainees;
	}
	public List<TraineeDto> getDtoTrainees() {
		return dtoTrainees;
	}
	public void setDtoTrainees(List<TraineeDto> dtoTrainees) {
		this.dtoTrainees = dtoTrainees;
	}
}
