package com.gymsystem.rest.responses;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymsystem.rest.dto.TrainerDto;
import com.gymsystem.rest.model.Trainer;

public class TraineeTrainersResponse {

	private Set<Trainer> originalTrainers;
	private Set<TrainerDto> trainers;
	
	public TraineeTrainersResponse(Set<Trainer> originalTrainers) {
		this.originalTrainers = originalTrainers;
		this.trainers = populateTrainers();
	}
	
	private Set<TrainerDto> populateTrainers(){
		Set<TrainerDto> trainers = new HashSet<>();
		
		for(Trainer trainer : originalTrainers) {
			String username = trainer.getUser().getUsername();
			String firstName = trainer.getUser().getFirstName();
			String lastName = trainer.getUser().getLastName();
			Long specialization = trainer.getTrainingType().getId();
			TrainerDto trainerDto = new TrainerDto(username, firstName,
					lastName, specialization);
			trainers.add(trainerDto);
		}
		
		return trainers;
	}
	
	@JsonIgnore
	public Set<Trainer> getOriginalTrainers() {
		return originalTrainers;
	}

	public void setOriginalTrainers(Set<Trainer> originalTrainers) {
		this.originalTrainers = originalTrainers;
	}
	public Set<TrainerDto> getTrainers() {
		return trainers;
	}
	public void setTrainers(Set<TrainerDto> trainers) {
		this.trainers = trainers;
	}
}
