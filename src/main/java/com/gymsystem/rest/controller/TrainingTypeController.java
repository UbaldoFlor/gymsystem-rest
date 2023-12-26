package com.gymsystem.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymsystem.rest.model.TrainingType;
import com.gymsystem.rest.responses.TrainingTypeResponse;
import com.gymsystem.rest.service.TrainingTypeService;

@RestController
public class TrainingTypeController {

	@Autowired
	private TrainingTypeService trainingTypeService;
	
	@GetMapping("/training-types")
    public ResponseEntity<TrainingTypeResponse> getTrainingTypes() { 	
    	List<TrainingType> trainingTypes = trainingTypeService.getAllTrainingTypes();

    	if(trainingTypes.size() != 0) {
        	// Create the response object
    		TrainingTypeResponse response = new TrainingTypeResponse(trainingTypes);
            // Return the response with a '302 Found' status
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
