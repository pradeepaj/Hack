package com.ing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.dto.OtpReqDTO;
import com.ing.dto.OtpResDTO;
import com.ing.dto.RegisterReqDTO;
import com.ing.dto.RegisterResDTO;
import com.ing.dto.SmsRequest;
import com.ing.service.UserService;
import com.ing.util.SmsService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

    private final SmsService service;

    @Autowired
    public UserController(SmsService service) {
        this.service = service;
    }

    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
    }
    
    @PostMapping("/otp")
    public ResponseEntity<OtpResDTO> sendOtp(@RequestBody OtpReqDTO req){
    	return new ResponseEntity<>(userService.sendOtp(req), HttpStatus.CREATED);
    }
    
    @PostMapping("/register")
    public ResponseEntity<RegisterResDTO> authentication(@RequestBody RegisterReqDTO req){
    	return new ResponseEntity<>(userService.authentication(req), HttpStatus.CREATED);
    }
    
}
