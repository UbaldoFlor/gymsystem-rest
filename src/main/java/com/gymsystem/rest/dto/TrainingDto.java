package com.gymsystem.rest.dto;

import java.sql.Date;

public class TrainingDto {
	
	private String trainingName;
	private Date trainingDate;
	private String trainingType;
	private Long trainingDuration;
	private String traineeName;
	
	public TrainingDto(String trainingName, Date trainingDate, String trainingType, Long trainingDuration,
			String traineeName) {
		this.trainingName = trainingName;
		this.trainingDate = trainingDate;
		this.trainingType = trainingType;
		this.trainingDuration = trainingDuration;
		this.traineeName = traineeName;
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
	public String getTraineeName() {
		return traineeName;
	}
	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}
}
