package com.gymsystem.rest.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.rest.dao.TrainingDao;
import com.gymsystem.rest.model.Trainee;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.requests.AddTrainingRequest;
import com.gymsystem.rest.service.TraineeService;
import com.gymsystem.rest.service.TrainerService;
import com.gymsystem.rest.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService{
	
	@Autowired
	TrainingDao trainingDao;
	@Autowired
	TraineeService traineeService;
	@Autowired
	TrainerService trainerService;

	@Override
	public void updateTraining(Training training) {
		trainingDao.updateTraining(training);
	}

	@Override
	public void deleteTraining(Long id) {
		trainingDao.deleteTraining(id);;
	}

	@Override
	public Training getTrainingById(Long id) {
		return trainingDao.getTrainingById(id);
	}

	@Override
	public List<Training> getAllTTrainings() {
		return trainingDao.getAllTrainings();
	}

	@Override
	public void createTraining(String trainingName, Date trainingDate, Long trainingDuration, Long... optionalParams) {
		trainingDao.createTraining(trainingName, trainingDate, trainingDuration, optionalParams);
	}

	@Override
	public Training createTraining(AddTrainingRequest addTrainingRequest) {
		Trainee trainee = traineeService.getTraineeByUsername(addTrainingRequest.getTraineeUsername());
		Trainer trainer = trainerService.getTrainerByUsername(addTrainingRequest.getTrainerUsername());
		Training training = trainingDao.createTraining(trainee, trainer, addTrainingRequest.getTrainingName(),
								   addTrainingRequest.getTrainingDate(), addTrainingRequest.getTrainingDuration());
		
		return training;
	}
}