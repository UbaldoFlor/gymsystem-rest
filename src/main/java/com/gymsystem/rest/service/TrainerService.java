package com.gymsystem.rest.service;

import java.util.List;
import java.util.Set;

import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.requests.TrainerRequest;
import com.gymsystem.rest.requests.TrainerTrainingsRequest;

public interface TrainerService {
	
	public void createTrainer(Long userId, Long trainingTypeId);
	
	public void updateTrainer(Trainer trainer);
	
	public void deleteTrainer(Long id);
	
	public Trainer getTrainerById(Long id);
	
	public List<Trainer> getAllTrainers();

	Trainer getTrainerByUsername(String username, String password);

	void updateTrainerPassword(String username, String password, String newPassword);

	void updateTrainerProfile(String username, String password, Long newTrainingTypeId);
	
	void changeTrainerStatus(String username, String password);
	
	void changeTrainerStatus(String username);
	
	Set<Training> getTrainings(String username, String password);
	
	Set<Training> getTrainings(TrainerTrainingsRequest request);
	
	public Trainer registerTrainer(TrainerRequest trainerRequest);

	public Trainer getTrainerByUsername(String username);

	public Trainer updateTrainerProfile(String username, Long specialization);
}