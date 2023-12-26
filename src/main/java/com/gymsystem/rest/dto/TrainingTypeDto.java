package com.gymsystem.rest.dto;

public class TrainingTypeDto {
	
	private String trainingType;
	private Long trainingTypeId;
	
	public TrainingTypeDto(String trainingType, Long trainingTypeId) {
		this.trainingType = trainingType;
		this.trainingTypeId = trainingTypeId;
	}
	
	public Long getTrainingTypeId() {
		return trainingTypeId;
	}
	public void setTrainingTypeId(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	
	
}
