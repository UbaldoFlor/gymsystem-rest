package com.gymsystem.rest.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trainer")
public class Trainer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
    private Long id;

	@ManyToOne
    @JoinColumn(name="Specialization")
    private TrainingType trainingType;
    
    @OneToOne
    @JoinColumn(name="UserID")
    private User user;
    
    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
    private Set<Training> trainings = new HashSet<>();

    @ManyToMany(mappedBy = "trainers", fetch = FetchType.EAGER)
    private Set<Trainee> trainees;

	public Trainer(){
    	
    }
    
    public Trainer(Long specializationId, Long userId) {
    	this.trainingType.setId(specializationId);
    	this.user.setId(userId);
    }
    
    //getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainingType getTrainingType() {
	    return trainingType;
	}

	public void setTrainingType(TrainingType trainingType) {
	    this.trainingType = trainingType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}
	
	public Set<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(Set<Trainee> trainees) {
		this.trainees = trainees;
	}

	@Override
	public String toString() {
		return "ID: " + String.valueOf(this.id) + "\n" +
				"Training Type ID: " + this.trainingType.getId() + "\n" +
				"User ID: " + this.user.getId();
	}
}