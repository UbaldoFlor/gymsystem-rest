package com.gymsystem.rest.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.gymsystem.rest.model.Trainer;
import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.requests.TrainerProfileRequest;
import com.gymsystem.rest.requests.TrainerRequest;
import com.gymsystem.rest.requests.TrainerStatusRequest;
import com.gymsystem.rest.requests.TrainerTrainingsRequest;
import com.gymsystem.rest.responses.CredentialsResponse;
import com.gymsystem.rest.responses.OkResponse;
import com.gymsystem.rest.responses.TrainerProfileResponse;
import com.gymsystem.rest.responses.TrainerTrainingsResponse;
import com.gymsystem.rest.service.TrainerService;

@RestController
public class TrainerController {
	
	@Autowired
	private TrainerService trainerService;
	
	@PostMapping("/trainers")
    public ResponseEntity<CredentialsResponse> registerTrainer(@RequestBody TrainerRequest trainerRequest) {
        // Process the request and save the trainer record
        Trainer trainer = trainerService.registerTrainer(trainerRequest);

        // Create the response object
        CredentialsResponse response = new CredentialsResponse(trainer.getUser().getUsername(),
        											   trainer.getUser().getPassword());

        // Return the response with a 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	@GetMapping("/trainers/profile")
    public ResponseEntity<TrainerProfileResponse> getTrainerProfile(@RequestBody String request) throws JsonMappingException, JsonProcessingException {
    	// Get the trainer profile
    	ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request);
        String username = jsonNode.get("username").asText();
        Trainer trainer = trainerService.getTrainerByUsername(username);
        if(trainer != null) {
        	// Create the response object
            TrainerProfileResponse response = new TrainerProfileResponse(trainer.getUser().getFirstName(),
            															trainer.getUser().getLastName(),
            															trainer.getTrainingType().getId(),
            															trainer.getUser().getIsActive(),
            															trainer.getTrainees());
            // Return the response with a '302 Found' status
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@PutMapping("/trainers/profiles/update")
    public ResponseEntity<TrainerProfileResponse> updateTrainerProfile(@RequestBody TrainerProfileRequest trainerProfileRequest) {
    	String username = trainerProfileRequest.getUsername();
    	Long specialization = trainerProfileRequest.getSpecialization();    	
    	Trainer trainer = trainerService.updateTrainerProfile(username, specialization);

    	if(trainer != null) {
        	// Create the response object
            TrainerProfileResponse response = new TrainerProfileResponse(trainer.getUser().getUsername(),
            															trainer.getUser().getFirstName(),
            															trainer.getUser().getLastName(),
            															trainer.getTrainingType().getId(),
            															trainer.getUser().getIsActive(),
            															trainer.getTrainees());
            // Return the response with a '302 Found' status
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@PatchMapping("/trainers/change-status")
    public ResponseEntity<OkResponse> changeTrainerStatus(@RequestBody TrainerStatusRequest trainerStatusRequest) {
		trainerService.changeTrainerStatus(trainerStatusRequest.getUsername());
		OkResponse response = new OkResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/trainers/trainings")
    public ResponseEntity<TrainerTrainingsResponse> getTrainerTrainingsList(@RequestBody TrainerTrainingsRequest request) {
    	// Get the trainer profile
    	Set<Training> trainings = trainerService.getTrainings(request);
    	
        if(!trainings.isEmpty()) {
        	// Create the response object
        	TrainerTrainingsResponse response = new TrainerTrainingsResponse(trainings);
            // Return the response with a '200 OK' status
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
