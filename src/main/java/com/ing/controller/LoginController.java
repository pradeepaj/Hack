package com.ing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.dto.LoginReqDto;
import com.ing.dto.LoginResDto;
import com.ing.service.LoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping("/user/login")
	public ResponseEntity<LoginResDto> userLogin(@RequestBody LoginReqDto loginDto) {

		return new ResponseEntity<>(loginService.userLogin(loginDto), HttpStatus.OK);
	}

}
