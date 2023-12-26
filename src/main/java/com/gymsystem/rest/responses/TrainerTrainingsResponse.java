package com.gymsystem.rest.responses;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymsystem.rest.dto.TrainingDto;
import com.gymsystem.rest.model.Training;

public class TrainerTrainingsResponse {
	
	private Set<Training> originalTrainings;
	private Set<TrainingDto> trainings;
	
	public TrainerTrainingsResponse(Set<Training> originalTrainings) {
		this.originalTrainings = originalTrainings;
		this.trainings = populateTrainings();
	}
	
	private Set<TrainingDto> populateTrainings(){
		Set<TrainingDto> trainings = new HashSet<>();
		
		for(Training training : this.originalTrainings) {
			String trainingName = training.getTrainingName();
			Date trainingDate = training.getTrainingDate();
			String trainingType = training.getTrainingType().getTrainingTypeName();
			Long trainingDuration = training.getTrainingDuration();
			String traineeName = training.getTrainee().getUser().getFirstName();
			TrainingDto trainingDto = new TrainingDto(trainingName, trainingDate, 
					trainingType, trainingDuration, traineeName);
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
	public Set<TrainingDto> getTrainings() {
		return trainings;
	}
	public void setTrainings(Set<TrainingDto> trainings) {
		this.trainings = trainings;
	}
	
	
}
