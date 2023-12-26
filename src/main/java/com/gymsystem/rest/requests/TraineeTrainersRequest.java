package com.gymsystem.rest.requests;

import java.util.Set;

public class TraineeTrainersRequest {
	
	private String traineeUsername;
    private Set<String> trainerUsernames;

	public String getTraineeUsername() {
        return traineeUsername;
    }

    public void setTraineeUsername(String traineeUsername) {
        this.traineeUsername = traineeUsername;
    }

    public Set<String> getTrainerUsernames() {
        return trainerUsernames;
    }

    public void setTrainerUsernames(Set<String> trainerUsernames) {
        this.trainerUsernames = trainerUsernames;
    }
}