package com.gymsystem.rest.responses;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymsystem.rest.dto.TrainerDto;
import com.gymsystem.rest.model.Trainer;

public class TraineeProfileResponse {
	
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String address;
	private boolean isActive;
	private Set<Trainer> trainers;
	private List<TrainerDto> dtoTrainers;

	public TraineeProfileResponse(String firstName, String lastName, 
			Date dateOfBirth, String address, boolean isActive, Set<Trainer> trainers) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.isActive = isActive;
		this.trainers = trainers;
		this.dtoTrainers = createDtoTrainers();
	}
	
	private List<TrainerDto> createDtoTrainers() {
		List<TrainerDto> dtoTrainers = new ArrayList<>();
		for(Trainer trainer : this.trainers) {
			String username = trainer.getUser().getUsername();
			String firstName = trainer.getUser().getFirstName();
			String lastName = trainer.getUser().getLastName();
			Long specialization = trainer.getTrainingType().getId();
			TrainerDto trainerDto = new TrainerDto(username, firstName,
					lastName, specialization);
			dtoTrainers.add(trainerDto);
		}
		
		return dtoTrainers;
	}
	
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@JsonIgnore
	public Set<Trainer> getTrainers() {
		return trainers;
	}
	public void setTrainers(Set<Trainer> trainers) {
		this.trainers = trainers;
	}
	
	public List<TrainerDto> getDtoTrainers() {
		return dtoTrainers;
	}

	public void setDtoTrainers(List<TrainerDto> dtoTrainers) {
		this.dtoTrainers = dtoTrainers;
	}
}
