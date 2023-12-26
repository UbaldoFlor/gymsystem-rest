package com.gymsystem.rest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gymsystem.rest.model.TrainingType;

@Repository
public interface TrainingTypeDao {

	List<TrainingType> getAllTrainingTypes();

	TrainingType getTrainingTypeById(long id);

	void createTrainingType(TrainingType trainingType);

	void updateTrainingType(TrainingType trainingType);

	void deleteTrainingType(long id);

}