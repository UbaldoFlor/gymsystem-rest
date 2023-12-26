package com.gymsystem.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.rest.dao.TrainingTypeDao;
import com.gymsystem.rest.model.TrainingType;
import com.gymsystem.rest.service.TrainingTypeService;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService{

	@Autowired
	TrainingTypeDao trainingTypeDao;
	
	@Override
	public void createTrainingType(TrainingType trainingType) {
		trainingTypeDao.createTrainingType(trainingType);
	}

	@Override
	public void updateTrainingType(TrainingType trainingType) {
		trainingTypeDao.updateTrainingType(trainingType);
	}

	@Override
	public void deleteTrainingType(Long id) {
		trainingTypeDao.deleteTrainingType(id);;
	}

	@Override
	public TrainingType getTrainingTypeById(Long id) {
		return trainingTypeDao.getTrainingTypeById(id);
	}

	@Override
	public List<TrainingType> getAllTrainingTypes() {
		return trainingTypeDao.getAllTrainingTypes();
	}
}