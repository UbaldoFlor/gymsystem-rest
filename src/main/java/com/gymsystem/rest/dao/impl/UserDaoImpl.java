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

import com.gymsystem.rest.dao.UserDao;
import com.gymsystem.rest.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;

	@Autowired
    public UserDaoImpl(LocalSessionFactoryBean sessionFactoryBean) {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        TypedQuery<User> query = session.createQuery(criteriaQuery);
        return query.getResultList();
	}

	@Override
	public User getUserById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(User.class, id);
	}

	@Override
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(user);
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		user = session.find(User.class, user.getId());
		user.setFirstName("Peter");
		session.flush();
	}

	@Override
	public void deleteUser(long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.find(User.class, id);
		session.remove(user);
	}
	
	@Override
	public boolean checkCredentials(String username, String password) {
		List<User> users = getAllUsers();
		for(User user : users) {
			if(username.equals(user.getUsername()) &&
					password.equals(user.getPassword()))
				return true;
		}
		return false;
	}

	@Override
	public boolean changePassword(String username, String password, String newPassword) {
		if(checkCredentials(username, password)) {
			Session session = sessionFactory.getCurrentSession();
			List<User> users = getAllUsers();
			for(int i = 0; i < users.size(); i++) {
				if(username.equals(users.get(i).getUsername()) &&
						password.equals(users.get(i).getPassword())) {
					users.get(i).setPassword(newPassword);
					session.flush();
					return true;
				}
			}
			return false;
		}
		else {
			System.out.println("Username/password is incorrect, please enter correct credentials");
			return false;
		}
	}
}