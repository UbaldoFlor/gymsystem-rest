package com.gymsystem.rest.service.impl;


import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.rest.dao.TraineeDao;
import com.gymsystem.rest.model.Trainee;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.model.User;
import com.gymsystem.rest.requests.TraineeRequest;
import com.gymsystem.rest.requests.TraineeTrainingsRequest;
import com.gymsystem.rest.service.TraineeService;
import com.gymsystem.rest.service.UserService;


@Service
public class TraineeServiceImpl implements TraineeService{

	@Autowired
	private TraineeDao traineeDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void createTrainee(Date dateOfBirth, String address, Long userId) {
		traineeDao.createTrainee(dateOfBirth, address, userId);
	}

	@Override
	public void updateTraineePassword(String username, String password, String newPassword) {
		traineeDao.updateTraineePassword(username, password, newPassword);
	}

	@Override
	public void deleteTrainee(Long id) {
		traineeDao.deleteTrainee(id);;
	}

	@Override
	public boolean deleteTrainee(String username) {
		traineeDao.deleteTrainee(username);
		if(traineeDao.checkUsernameExistence(username))
			return true;
		
		return false;
	}
	
	@Override
	public Trainee getTraineeById(Long id) {
		return traineeDao.getTraineeById(id);
	}

	@Override
	public List<Trainee> getAllTrainees() {
		return traineeDao.getAllTrainees();
	}

	@Override
	public Trainee getTraineeByUsername(String username, String password) {
		return traineeDao.getTraineeByUsername(username, password);
	}

	@Override
	public void updateTraineeProfile(String username, String password, Date date, String address) {
		traineeDao.updateTraineeProfile(username, password, date, address);
	}

	@Override
	public Trainee updateTraineeProfile(String username, Date date, String address) {
		return traineeDao.updateTraineeProfile(username, date, address);
	}
	
	@Override
	public void changeTraineeStatus(String username, String password) {
		traineeDao.changeTraineeStatus(username, password);
	}

	@Override
	public void changeTraineeStatus(String username, boolean isActive) {
		traineeDao.changeTraineeStatus(username, isActive);
	}
	
	@Override
	public Set<Training> getTrainings(String username, String password) {
		return traineeDao.getTrainings(username, password);
	}

	@Override
	public List<Trainer> getTrainersNotAssignedToATrainee(String username, String password) {
		return traineeDao.getTrainersNotAssignedToATrainee(username, password);
	}

	@Override
	public void deleteTrainer(String username, String password, Long trainerId) {
		traineeDao.deleteTrainer(username, password, trainerId);
	}
	
	@Override
	public Trainee registerTrainee(TraineeRequest traineeRequest) {
		User user = new User(traineeRequest.getFirstName(), traineeRequest.getLastName(), true);
		userService.createUser(user);
		Date date = Date.valueOf(traineeRequest.getDateOfBirth());
		traineeDao.createTrainee(date, traineeRequest.getAddress(), user.getId());
		Trainee trainee = traineeDao.getTraineeByUsername(user.getUsername(), user.getPassword());
		System.out.println("Trainee created:\n" + trainee);
		return trainee;
	}

	@Override
	public Trainee getTraineeByUsername(String username) {
		return traineeDao.getTraineeByUsername(username);
	}

	@Override
	public Set<Training> getTrainings(TraineeTrainingsRequest request) {
		String username = request.getUsername();
		Date periodFrom = request.getPeriodFrom();
		Date periodTo = request.getPeriodTo();
		String trainerName = request.getTrainerName();
		String trainingType = request.getTrainingType();
		return traineeDao.getTrainings(username, periodFrom, periodTo, trainerName, trainingType);
	}

	@Override
	public Set<Trainer> updateTrainers(String username, Set<String> trainerUsernames) {
		return traineeDao.updateTrainers(username, trainerUsernames);
	}
}