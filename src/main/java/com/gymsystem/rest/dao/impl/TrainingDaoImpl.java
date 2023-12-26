package com.gymsystem.rest.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gymsystem.rest.dao.TrainingDao;
import com.gymsystem.rest.model.Trainee;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.model.TrainingType;

@Repository
@Transactional
public class TrainingDaoImpl implements TrainingDao {

	private SessionFactory sessionFactory;

	@Autowired
    public TrainingDaoImpl(LocalSessionFactoryBean sessionFactoryBean) {
        this.sessionFactory = sessionFactoryBean.getObject();
    }
	
	@Override
	public List<Training> getAllTrainings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Training getTrainingById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTraining(String trainingName, Date trainingDate, Long trainingDuration, Long... optionalParams) {
		Session session = sessionFactory.getCurrentSession();
		Training training = new Training();
		training.setTrainingName(trainingName);
		training.setTrainingDate(trainingDate);
		training.setTrainingDuration(trainingDuration);
		if(optionalParams.length > 0) {
			Trainee trainee = session.get(Trainee.class, optionalParams[0]);
	        Trainer trainer = session.get(Trainer.class, optionalParams[1]);
	        TrainingType trainingType = session.get(TrainingType.class, optionalParams[2]);
	        training.setTrainee(trainee);
			training.setTrainer(trainer);
			training.setTrainingType(trainingType);
		}
		session.persist(training);
	}
	
	@Override
	public Training createTraining(Trainee trainee, Trainer trainer, String trainingName, Date trainingDate, Long trainingDuration) {
		Session session = sessionFactory.getCurrentSession();
		Training training = new Training();
		training.setTrainee(trainee);
		training.setTrainer(trainer);
		training.setTrainingName(trainingName);
		training.setTrainingDate(trainingDate);
		training.setTrainingDuration(trainingDuration);
		session.persist(training);
		return training;
	}

	@Override
	public void updateTraining(Training training) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTraining(long id) {
		// TODO Auto-generated method stub
		
	}
}