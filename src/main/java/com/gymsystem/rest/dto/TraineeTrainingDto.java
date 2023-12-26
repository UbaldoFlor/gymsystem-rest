package com.gymsystem.rest.dto;

import java.sql.Date;

public class TraineeTrainingDto {
	
	private String trainingName;
	private Date trainingDate;
	private String trainingType;
	private Long trainingDuration;
	private String trainerName;
	
	public TraineeTrainingDto(String trainingName, Date trainingDate, String trainingType, Long trainingDuration,
			String trainerName) {
		this.trainingName = trainingName;
		this.trainingDate = trainingDate;
		this.trainingType = trainingType;
		this.trainingDuration = trainingDuration;
		this.trainerName = trainerName;
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
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	public Long getTrainingDuration() {
		return trainingDuration;
	}
	public void setTrainingDuration(Long trainingDuration) {
		this.trainingDuration = trainingDuration;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
}
