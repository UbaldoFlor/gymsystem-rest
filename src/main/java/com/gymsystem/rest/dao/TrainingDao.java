package com.gymsystem.rest.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gymsystem.rest.model.Trainee;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;

@Repository
public interface TrainingDao {

	List<Training> getAllTrainings();

	Training getTrainingById(long id);

	void createTraining(String trainingName, Date trainingDate, Long trainingDuration, Long... optionalParams);

	void updateTraining(Training training);

	void deleteTraining(long id);
	
	public Training createTraining(Trainee trainee, Trainer trainer, String trainingName, Date trainingDate, Long trainingDuration);
}