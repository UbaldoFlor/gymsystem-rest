package com.gymsystem.rest.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.gymsystem.rest.model.Trainee;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.requests.TraineeRequest;
import com.gymsystem.rest.requests.TraineeTrainingsRequest;

public interface TraineeService {
	
	public void createTrainee(Date dateOfBirth, String address, Long userId);
	
	public void updateTraineePassword(String username, String password, String newPassword);
	
	public void deleteTrainee(Long id);
	
	public boolean deleteTrainee(String username);
	
	public Trainee getTraineeById(Long id);
	
	public List<Trainee> getAllTrainees();

	public Trainee getTraineeByUsername(String username, String password);
	
	public Trainee getTraineeByUsername(String username);

	public void updateTraineeProfile(String username, String password, Date date, String address);
	
	public Trainee updateTraineeProfile(String username, Date date, String address);
	
	public void changeTraineeStatus(String username, String password);
	
	public Set<Training> getTrainings(String username, String password);
	
	List<Trainer> getTrainersNotAssignedToATrainee(String username, String password);
	
	void deleteTrainer(String username, String password, Long trainerId);
	
	public Trainee registerTrainee(TraineeRequest traineeRequest);

	public void changeTraineeStatus(String username, boolean isActive);

	public Set<Training> getTrainings(TraineeTrainingsRequest request);
	
	public Set<Trainer> updateTrainers(String username, Set<String> trainerUsernames);
}