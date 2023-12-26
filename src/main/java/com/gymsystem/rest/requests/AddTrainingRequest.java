package com.gymsystem.rest.requests;

import java.sql.Date;

public class AddTrainingRequest {
	
	private String traineeUsername;
	private String trainerUsername;
	private String trainingName;
	private Date trainingDate;
	private Long trainingDuration;
	
	// Getters and setters
	public String getTraineeUsername() {
		return traineeUsername;
	}
	public void setTraineeUsername(String traineeUsername) {
		this.traineeUsername = traineeUsername;
	}
	public String getTrainerUsername() {
		return trainerUsername;
	}
	public void setTrainerUsername(String trainerUsername) {
		this.trainerUsername = trainerUsername;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public Date getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}
	public Long getTrainingDuration() {
		return trainingDuration;
	}
	public void setTrainingDuration(Long trainingDuration) {
		this.trainingDuration = trainingDuration;
	}
}
