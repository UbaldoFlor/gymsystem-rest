package com.gymsystem.rest.dao.impl;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gymsystem.rest.dao.TrainerDao;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.model.TrainingType;
import com.gymsystem.rest.model.User;

@Repository
@Transactional
public class TrainerDaoImpl implements TrainerDao {

	private SessionFactory sessionFactory;

	@Autowired
    public TrainerDaoImpl(LocalSessionFactoryBean sessionFactoryBean) {
        this.sessionFactory = sessionFactoryBean.getObject();
    }
	
	@Override
	public List<Trainer> getAllTrainers() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Trainer> criteriaQuery = criteriaBuilder.createQuery(Trainer.class);
        Root<Trainer> root = criteriaQuery.from(Trainer.class);
        criteriaQuery.select(root);
        TypedQuery<Trainer> query = session.createQuery(criteriaQuery);
        return query.getResultList();
	}

	@Override
	public Trainer getTrainerById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Trainer.class, id);
	}

	@Override
	public Trainer createTrainer(Long userId, Long trainingTypeId) {
		Session session = sessionFactory.getCurrentSession();

        // Get the existing User and TrainingType entities based on their IDs
        User user = session.get(User.class, userId);
        TrainingType trainingType = session.get(TrainingType.class, trainingTypeId);

        // Create a new Trainer entity and set the User and TrainingType
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setTrainingType(trainingType);

        // Persist the Trainer entity
        session.persist(trainer);
        
        return trainer;
	}

	@Override
	public void updateTrainer(Trainer trainer) {
		Session session = sessionFactory.getCurrentSession();
		trainer = session.find(Trainer.class, trainer.getId());
		trainer.setId(10L);
		session.flush();
	}

	@Override
	public void deleteTrainer(long id) {
		Session session = sessionFactory.getCurrentSession();
		Trainer trainer = session.find(Trainer.class, id);
		session.remove(trainer);
	}

	@Override
	public Trainer getTrainerByUsername(String username, String password) {
		if(matchingPasswordAndUsername(username, password)) {
			// Getting user for the requested username
			Session session = sessionFactory.getCurrentSession();
		    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		    Root<User> root = criteriaQuery.from(User.class);
		    Predicate usernamePredicate = criteriaBuilder.equal(root.get("username"), username);
		    criteriaQuery.select(root).where(usernamePredicate);
		    TypedQuery<User> query = session.createQuery(criteriaQuery);
		    List<User> users = query.getResultList();
		    User user = users.get(0);
			return user.getTrainer();
		}
		else {
			System.out.println("The username/password provided doesn't match, " +
							   "please enter correct credentials.");
			return null;
		}
	}
	
	@Override
	public Trainer getTrainerByUsername(String username) {
		if(checkUsernameExistence(username)) {
			// Getting user for the requested username
			Session session = sessionFactory.getCurrentSession();
		    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		    Root<User> root = criteriaQuery.from(User.class);
		    Predicate usernamePredicate = criteriaBuilder.equal(root.get("username"), username);
		    criteriaQuery.select(root).where(usernamePredicate);
		    TypedQuery<User> query = session.createQuery(criteriaQuery);
		    List<User> users = query.getResultList();
		    User user = users.get(0);
			return user.getTrainer();
		}
		else {
			System.out.println("The username provided doesn't match, " +
							   "please enter correct username.");
			return null;
		}
	}
	
	@Override
	public void updateTrainerPassword(String username, String password, String newPassword) {
		if(matchingPasswordAndUsername(username, password)) {
			Session session = sessionFactory.getCurrentSession();
			Trainer trainer = getTrainerByUsername(username, password);
			User user = session.find(User.class, trainer.getUser().getId());
			user.setPassword(newPassword);
			session.flush();
		}else {
			System.out.println("The username/password provided doesn't match, " +
					   "please enter correct credentials.");
		}
	}
	
	@Override
	public void updateTrainerProfile(String username, String password, Long newTrainingTypeId) {
		if(matchingPasswordAndUsername(username, password)) {
			Session session = sessionFactory.getCurrentSession();
	        TrainingType trainingType = session.get(TrainingType.class, newTrainingTypeId);
	        Trainer trainer = getTrainerByUsername(username, password);
	        // Create a new Trainer entity and set the User and TrainingType
	        trainer = session.find(Trainer.class, trainer.getId());
	        trainer.setTrainingType(trainingType);
			session.flush();
		}else {
			System.out.println("The username/password provided doesn't match, " +
					   "please enter correct credentials.");
		}
	}
	
	@Override
	public Trainer updateTrainerProfile(String username, Long newTrainingTypeId) {
		if(checkUsernameExistence(username)) {
			Session session = sessionFactory.getCurrentSession();
	        TrainingType trainingType = session.get(TrainingType.class, newTrainingTypeId);
	        Trainer trainer = getTrainerByUsername(username);
	        // Create a new Trainer entity and set the User and TrainingType
	        trainer = session.find(Trainer.class, trainer.getId());
	        trainer.setTrainingType(trainingType);
			session.flush();
			return trainer;
		}else {
			System.out.println("The username/password provided doesn't match, " +
					   "please enter correct credentials.");
			return null;
		}
	}
	
	@Override
	public void changeTrainerStatus(String username, String password) {
		if(matchingPasswordAndUsername(username, password)) {
			Session session = sessionFactory.getCurrentSession();
	        Trainer trainer = getTrainerByUsername(username, password);
	        // Create a new Trainer entity and set the User and TrainingType
	        User user = session.find(User.class, trainer.getUser().getId());
	        if(user.getIsActive())
	        	user.setIsActive(false);
	        else
	        	user.setIsActive(true);
			session.flush();
		}else {
			System.out.println("The username/password provided doesn't match, " +
					   "please enter correct credentials.");
		}
	}
	
	@Override
	public void changeTrainerStatus(String username) {
		if(checkUsernameExistence(username)) {
			Session session = sessionFactory.getCurrentSession();
	        Trainer trainer = getTrainerByUsername(username);
	        // Create a new Trainer entity and set the User and TrainingType
	        User user = session.find(User.class, trainer.getUser().getId());
	        if(user.getIsActive())
	        	user.setIsActive(false);
	        else
	        	user.setIsActive(true);
			session.flush();
		}else {
			System.out.println("The username provided doesn't match, " +
					   "please enter correct username.");
		}
	}
	
	@Override
	public Set<Training> getTrainings(String username, String password) {
		if(matchingPasswordAndUsername(username, password)) {
			Trainer trainer = getTrainerByUsername(username, password);
			return trainer.getTrainings();
		}
		else {
			System.out.println("The username/password provided doesn't match, " +
					   "please enter correct credentials.");
			return null;	
		}
	}
	
	@Override
	public Set<Training> getTrainings(String username, Date periodFrom, Date periodTo, String traineeName) {
		if(checkUsernameExistence(username)) {
			Trainer trainer = getTrainerByUsername(username);
			Set<Training> trainings = trainer.getTrainings();
			Set<Training> filteredTrainings = new HashSet<>();
			for(Training training : trainings) {
				if(training.getTrainingDate().after(periodFrom) &&
						training.getTrainingDate().before(periodTo) &&
						traineeName.equals(training.getTrainee().getUser().getFirstName())) {
					
					filteredTrainings.add(training);
				}
					
			}
			
			return filteredTrainings;
		}
		else {
			System.out.println("The username provided doesn't match, " +
					   "please enter correct username.");
			return null;	
		}
	}
	
	public boolean checkUsernameExistence(String username) {
		Session session = sessionFactory.getCurrentSession();
	    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
	    Root<User> root = criteriaQuery.from(User.class);
	    Predicate usernamePredicate = criteriaBuilder.equal(root.get("username"), username);
	    criteriaQuery.select(root).where(usernamePredicate);
	    TypedQuery<User> query = session.createQuery(criteriaQuery);
	    List<User> users = query.getResultList();
	    
	    if(users.isEmpty())
	    	return false;
	    
	    return true;
	}
	
	private boolean matchingPasswordAndUsername(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
	    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
	    Root<User> root = criteriaQuery.from(User.class);
	    // Adding predicates to the query to match the username and password
	    Predicate usernamePredicate = criteriaBuilder.equal(root.get("username"), username);
	    Predicate passwordPredicate = criteriaBuilder.equal(root.get("password"), password);
	    criteriaQuery.select(root).where(usernamePredicate, passwordPredicate);
	    TypedQuery<User> query = session.createQuery(criteriaQuery);
	    // Executing the query and check if any matching records are found
	    List<User> resultList = query.getResultList();
	    return !resultList.isEmpty();
	}
}