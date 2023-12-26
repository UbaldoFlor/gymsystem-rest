package com.gymsystem.rest.controller;

import java.sql.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gymsystem.rest.model.Trainee;
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.requests.TraineeProfileRequest;
import com.gymsystem.rest.requests.TraineeRequest;
import com.gymsystem.rest.requests.TraineeStatusRequest;
import com.gymsystem.rest.requests.TraineeTrainersRequest;
import com.gymsystem.rest.requests.TraineeTrainingsRequest;
import com.gymsystem.rest.responses.CredentialsResponse;
import com.gymsystem.rest.responses.OkResponse;
import com.gymsystem.rest.responses.TraineeProfileResponse;
import com.gymsystem.rest.responses.TraineeTrainersResponse;
import com.gymsystem.rest.responses.TraineeTrainingsResponse;
import com.gymsystem.rest.service.TraineeService;

@RestController
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    @PostMapping("/trainees")
    public ResponseEntity<CredentialsResponse> registerTrainee(@RequestBody TraineeRequest traineeRequest) {
        // Process the request and save the trainee record
        Trainee trainee = traineeService.registerTrainee(traineeRequest);

        // Create the response object
        CredentialsResponse response = new CredentialsResponse(trainee.getUser().getUsername(),
        											   trainee.getUser().getPassword());

        // Return the response with a 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/trainees/profile")
    public ResponseEntity<TraineeProfileResponse> getTraineeProfile(@RequestBody String request) throws JsonMappingException, JsonProcessingException {
    	// Get the trainee profile
    	ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request);
        String username = jsonNode.get("username").asText();
        Trainee trainee = traineeService.getTraineeByUsername(username);
        if(trainee != null) {
        	// Create the response object
            TraineeProfileResponse response = new TraineeProfileResponse(trainee.getUser().getFirstName(),
            															trainee.getUser().getLastName(),
            															trainee.getDateOfBirth(),
            															trainee.getAddress(),
            															trainee.getUser().getIsActive(),
            															trainee.getTrainers());
            // Return the response with a '302 Found' status
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/trainees/profiles/update")
    public ResponseEntity<TraineeProfileResponse> updateTraineeProfile(@RequestBody TraineeProfileRequest traineeProfileRequest) {
    	String username = traineeProfileRequest.getUsername();
    	Date date = traineeProfileRequest.getDateOfBirth();
    	String address = traineeProfileRequest.getAddress();
    	
    	Trainee trainee = traineeService.updateTraineeProfile(username, date, address);

    	if(trainee != null) {
        	// Create the response object
            TraineeProfileResponse response = new TraineeProfileResponse(trainee.getUser().getFirstName(),
            															trainee.getUser().getLastName(),
            															trainee.getDateOfBirth(),
            															trainee.getAddress(),
            															trainee.getUser().getIsActive(),
            															trainee.getTrainers());
            // Return the response with a '302 Found' status
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/trainees/profiles/delete")
    public ResponseEntity<OkResponse> deleteTraineeProfile(@RequestBody String request) throws JsonMappingException, JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request);
        String username = jsonNode.get("username").asText();
        
        if(traineeService.deleteTrainee(username)) {
        	OkResponse response = new OkResponse();
        	return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }
    
    @PatchMapping("/trainees/change-status")
    public ResponseEntity<OkResponse> changeTraineeStatus(@RequestBody TraineeStatusRequest traineeStatusRequest) {
		traineeService.changeTraineeStatus(traineeStatusRequest.getUsername(), traineeStatusRequest.getIsActive());
		OkResponse response = new OkResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
	}
    
    @GetMapping("/trainees/trainings")
    public ResponseEntity<TraineeTrainingsResponse> getTraineeTrainingsList(@RequestBody TraineeTrainingsRequest request) {
    	// Get the trainer profile
    	Set<Training> trainings = traineeService.getTrainings(request);
    	
        if(!trainings.isEmpty()) {
        	// Create the response object
        	TraineeTrainingsResponse response = new TraineeTrainingsResponse(trainings);
            // Return the response with a '200 OK' status
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/trainees/trainers-update")
    public ResponseEntity<TraineeTrainersResponse> updateTrainersList(@RequestBody TraineeTrainersRequest request) {
    	if (request.getTrainerUsernames() == null || request.getTrainerUsernames().isEmpty()) {
            // Handle the case where the trainerUsernames set is null or empty
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Set<Trainer> trainers = traineeService.updateTrainers(request.getTraineeUsername(), request.getTrainerUsernames());
        
        if(trainers.size() > 0) {
        	TraineeTrainersResponse response = new TraineeTrainersResponse(trainers);
        	return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        
    }
}