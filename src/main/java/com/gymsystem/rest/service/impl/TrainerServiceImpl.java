package com.gymsystem.rest.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.rest.dao.TrainerDao;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.model.User;
import com.gymsystem.rest.requests.TrainerRequest;
import com.gymsystem.rest.requests.TrainerTrainingsRequest;
import com.gymsystem.rest.service.TrainerService;
import com.gymsystem.rest.service.UserService;

@Service
public class TrainerServiceImpl implements TrainerService{
	
	@Autowired
	private TrainerDao trainerDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void createTrainer(Long userId, Long trainingTypeId) {
		trainerDao.createTrainer(userId, trainingTypeId);
	}

	@Override
	public void updateTrainer(Trainer trainer) {
		trainerDao.updateTrainer(trainer);
	}

	@Override
	public void deleteTrainer(Long id) {
		trainerDao.deleteTrainer(id);;
	}

	@Override
	public Trainer getTrainerById(Long id) {
		return trainerDao.getTrainerById(id);
	}

	@Override
	public List<Trainer> getAllTrainers() {
		return trainerDao.getAllTrainers();
	}
	
	@Override
	public Trainer getTrainerByUsername(String username, String password) {
        return trainerDao.getTrainerByUsername(username, password);
    }
	
	@Override
	public Trainer getTrainerByUsername(String username) {
        return trainerDao.getTrainerByUsername(username);
    }

	@Override
	public void updateTrainerPassword(String username, String password, String newPassword) {
		trainerDao.updateTrainerPassword(username, password, newPassword);
	}

	@Override
	public void updateTrainerProfile(String username, String password, Long newTrainingTypeId) {
		trainerDao.updateTrainerProfile(username, password, newTrainingTypeId);
	}
	
	@Override
	public Trainer updateTrainerProfile(String username, Long newTrainingTypeId) {
		return trainerDao.updateTrainerProfile(username, newTrainingTypeId);
	}

	@Override
	public void changeTrainerStatus(String username, String password) {
		trainerDao.changeTrainerStatus(username, password);
	}

	@Override
	public void changeTrainerStatus(String username) {
		trainerDao.changeTrainerStatus(username);
	}
	
	@Override
	public Set<Training> getTrainings(String username, String password) {
		return trainerDao.getTrainings(username, password);
	}
	
	@Override
	public Set<Training> getTrainings(TrainerTrainingsRequest request){
		String username = request.getUsername();
		Date periodFrom = request.getPeriodFrom();
		Date periodTo = request.getPeriodTo();
		String traineeName = request.getTraineeName();
		return trainerDao.getTrainings(username, periodFrom, periodTo, traineeName);
	}

	@Override
	public Trainer registerTrainer(TrainerRequest trainerRequest) {
		User user = new User(trainerRequest.getFirstName(), trainerRequest.getLastName(), true);
		userService.createUser(user);
		Trainer trainer = trainerDao.createTrainer(user.getId(), trainerRequest.getSpecialization());
		System.out.println("Trainer created:\n" + trainer);
		return trainer;
	}
	
}