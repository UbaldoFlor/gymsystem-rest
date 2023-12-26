package com.gymsystem.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gymsystem.rest.requests.ChangeLoginRequest;
import com.gymsystem.rest.requests.LoginRequest;
import com.gymsystem.rest.responses.OkResponse;
import com.gymsystem.rest.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
    public ResponseEntity<OkResponse> logUserIn(@RequestBody LoginRequest loginRequest) {
        // Process the request and check if credentials are correct
        if(userService.checkCredentials(loginRequest)) {
        	OkResponse okResponse = new OkResponse();
            return ResponseEntity.status(HttpStatus.OK).body(okResponse);
        }
        else {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } 
    }
	
	@PutMapping("/login/change")
	public ResponseEntity<OkResponse> changeLogin(@RequestBody ChangeLoginRequest changeLoginRequest) {
        // Process the request and check if credentials are correct to change the password
        if(userService.changePassword(changeLoginRequest.getUsername(), changeLoginRequest.getPassword(),
        		changeLoginRequest.getNewPassword())) {
        	OkResponse okResponse = new OkResponse();
            return ResponseEntity.status(HttpStatus.OK).body(okResponse);
        }
        else {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } 
    }
}
