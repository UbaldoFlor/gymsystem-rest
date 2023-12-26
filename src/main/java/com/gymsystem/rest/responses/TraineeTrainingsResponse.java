package com.gymsystem.rest.responses;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymsystem.rest.dto.TraineeTrainingDto;
import com.gymsystem.rest.model.Training;

public class TraineeTrainingsResponse {
	
	private Set<Training> originalTrainings;
	private Set<TraineeTrainingDto> trainings;
	
	public TraineeTrainingsResponse(Set<Training> originalTrainings) {
		this.originalTrainings = originalTrainings;
		this.trainings = populateTrainings();
	}
	
	private Set<TraineeTrainingDto> populateTrainings(){
		Set<TraineeTrainingDto> trainings = new HashSet<>();
		
		for(Training training : this.originalTrainings) {
			String trainingName = training.getTrainingName();
			Date trainingDate = training.getTrainingDate();
			String trainingType = training.getTrainingType().getTrainingTypeName();
			Long trainingDuration = training.getTrainingDuration();
			String trainerName = training.getTrainer().getUser().getFirstName();
			TraineeTrainingDto trainingDto = new TraineeTrainingDto(trainingName, trainingDate, 
					trainingType, trainingDuration, trainerName);
			trainings.add(trainingDto);
		}
		
		return trainings;
	}
	
	@JsonIgnore
	public Set<Training> getOriginalTrainings() {
		return originalTrainings;
	}
	public void setOriginalTrainings(Set<Training> originalTrainings) {
		this.originalTrainings = originalTrainings;
	}
	public Set<TraineeTrainingDto> getTrainings() {
		return trainings;
	}
	public void setTrainings(Set<TraineeTrainingDto> trainings) {
		this.trainings = trainings;
	}
}
