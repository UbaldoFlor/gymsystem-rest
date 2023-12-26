package com.gymsystem.rest.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymsystem.rest.dto.TrainingTypeDto;
import com.gymsystem.rest.model.TrainingType;

public class TrainingTypeResponse {
	
	private List<TrainingType> trainingTypes;
	private List<TrainingTypeDto> trainingTypesDto;
	
	public TrainingTypeResponse(List<TrainingType> trainingTypes) {
		this.trainingTypes = trainingTypes;
		this.trainingTypesDto = createDtoTrainingTypes();
	}

	private List<TrainingTypeDto> createDtoTrainingTypes() {
		List<TrainingTypeDto> trainings = new ArrayList<>();
		for(TrainingType trainingType : trainingTypes) {
			String trainingTypeName = trainingType.getTrainingTypeName();
			Long trainingTypeId = trainingType.getId();
			TrainingTypeDto trainingTypeDto = new TrainingTypeDto(trainingTypeName, trainingTypeId);
			trainings.add(trainingTypeDto);
		}
		
		return trainings;
	}
	
	// Getters and setters
	
	@JsonIgnore
	public List<TrainingType> getTrainingTypes() {
		return trainingTypes;
	}

	public void setTrainingTypes(List<TrainingType> trainingTypes) {
		this.trainingTypes = trainingTypes;
	}

	public List<TrainingTypeDto> getTrainingTypesDto() {
		return trainingTypesDto;
	}

	public void setTrainingTypesDto(List<TrainingTypeDto> trainingTypesDto) {
		this.trainingTypesDto = trainingTypesDto;
	}
}
