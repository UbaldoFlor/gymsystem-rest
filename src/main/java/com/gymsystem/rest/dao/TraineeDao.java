package com.gymsystem.rest.dao;


import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.gymsystem.rest.model.Trainee;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;

@Repository
public interface TraineeDao {

	void deleteTrainee(long id);

	void updateTraineePassword(String username, String password, String newPassword);

	void createTrainee(Date dateOfBirth, String address, Long userId);

	Trainee getTraineeById(long id);

	List<Trainee> getAllTrainees();

	Trainee getTraineeByUsername(String username, String password);

	void updateTraineeProfile(String username, String password, Date date, String address);
	
	void changeTraineeStatus(String username, String password);
	
	Set<Training> getTrainings(String username, String password);
	
	List<Trainer> getTrainersNotAssignedToATrainee(String username, String password);
	
	void deleteTrainer(String username, String password, Long trainerId);

	public Trainee getTraineeByUsername(String username);

	Trainee updateTraineeProfile(String username, Date date, String address);

	public void deleteTrainee(String username);

	boolean checkUsernameExistence(String username);

	void changeTraineeStatus(String username, boolean isActive);

	Set<Training> getTrainings(String username, Date periodFrom, Date periodTo, String trainerName,
			String trainingType);
	
	public Set<Trainer> updateTrainers(String username, Set<String> trainerUsernames);
}