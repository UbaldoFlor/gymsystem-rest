package com.gymsystem.rest.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gymsystem.rest.dao.TrainingTypeDao;
import com.gymsystem.rest.model.TrainingType;

@Repository
@Transactional
public class TrainingTypeDaoImpl implements TrainingTypeDao {

	private SessionFactory sessionFactory;

	@Autowired
    public TrainingTypeDaoImpl(LocalSessionFactoryBean sessionFactoryBean) {
        this.sessionFactory = sessionFactoryBean.getObject();
    }
	
	@Override
	public List<TrainingType> getAllTrainingTypes() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TrainingType> criteriaQuery = criteriaBuilder.createQuery(TrainingType.class);
        Root<TrainingType> root = criteriaQuery.from(TrainingType.class);
        criteriaQuery.select(root);
        TypedQuery<TrainingType> query = session.createQuery(criteriaQuery);
        return query.getResultList();
	}

	@Override
	public TrainingType getTrainingTypeById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTrainingType(TrainingType trainingType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTrainingType(TrainingType trainingType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTrainingType(long id) {
		// TODO Auto-generated method stub
		
	}
}