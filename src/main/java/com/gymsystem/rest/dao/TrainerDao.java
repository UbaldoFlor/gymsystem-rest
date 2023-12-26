package com.gymsystem.rest.dao;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;

@Repository
public interface TrainerDao {

	List<Trainer> getAllTrainers();

	Trainer getTrainerById(long id);

	public Trainer createTrainer(Long userId, Long trainingTypeId);

	void updateTrainer(Trainer trainer);

	void deleteTrainer(long id);

	Trainer getTrainerByUsername(String username, String password);

	void updateTrainerPassword(String username, String password, String newPassword);

	void updateTrainerProfile(String username, String password, Long newTrainingTypeId);
	
	void changeTrainerStatus(String username, String password);
	
	Set<Training> getTrainings(String username, String password);

	Trainer getTrainerByUsername(String username);

	Trainer updateTrainerProfile(String username, Long newTrainingTypeId);

	void changeTrainerStatus(String username);

	Set<Training> getTrainings(String username, Date periodFrom, Date periodTo, String traineeName);
}