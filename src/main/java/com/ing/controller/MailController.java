package com.ing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.service.MailService;

@RestController
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@PostMapping("/mail")
	public String sendMail(String email){
		
		return mailService.testMail(email);
	}

}
