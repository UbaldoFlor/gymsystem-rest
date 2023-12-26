package com.gymsystem.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gymsystem.rest.model.Training;
import com.gymsystem.rest.requests.AddTrainingRequest;
import com.gymsystem.rest.responses.OkResponse;
import com.gymsystem.rest.service.TrainingService;

@RestController
public class TrainingController {

	@Autowired
	private TrainingService trainingService;
	
	@PostMapping("/trainings/add")
    public ResponseEntity<OkResponse> addTraining(@RequestBody AddTrainingRequest addTrainingRequest) {
		Training training = trainingService.createTraining(addTrainingRequest);
		if(training != null) {
			OkResponse response = new OkResponse();
	        return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		
	}
}
