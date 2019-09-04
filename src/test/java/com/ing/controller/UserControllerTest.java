package com.ing.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ing.dto.OtpReqDTO;
import com.ing.dto.OtpResDTO;
import com.ing.dto.RegisterReqDTO;
import com.ing.dto.RegisterResDTO;
import com.ing.dto.SmsRequest;
import com.ing.service.UserService;
import com.ing.util.SmsService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class UserControllerTest {

	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	@Mock
	SmsService smsService;
	
	OtpReqDTO req = null;
	OtpResDTO otpResDTO=null;
	RegisterReqDTO regReqDTO=null;
	RegisterResDTO regResDTO=null;
	
	@Before
	public void setUp() {
		
		req = new OtpReqDTO();
		req.setEmail("dhanashekara123@gmail.com");
		req.setPhoneNumber("1234455");
		
		otpResDTO = new OtpResDTO();
		otpResDTO.setStatus("SUCCESS");
		otpResDTO.setMesaage("OTP Sent Successfully");
		otpResDTO.setStatusCode(201);
		
		regReqDTO = new RegisterReqDTO();
		regReqDTO.setOtpGen(123);
		regReqDTO.setEmail("dhana123@gmail.com");
		
		regResDTO = new RegisterResDTO();
		regResDTO.setStatusCode(201);
	}
	
	@Test
	public void testSmsService() {
		
		SmsRequest smsRequest = new SmsRequest("8882828282", "otp");
		Mockito.doNothing().when(smsService).sendSms(smsRequest);
		smsService.sendSms(smsRequest);
		
		
	}
	
	/*@Test
	public void sendOTP() {
		
	//	ResponseEntity sample = new ResponseEntity<OtpResDTO>(null,HttpStatus.OK);
		ResponseEntity<OtpResDTO> expResult = new ResponseEntity<>(otpResDTO, HttpStatus.CREATED);
		when(userService.sendOtp(req)).thenReturn(otpResDTO);
		ResponseEntity<OtpResDTO> actual = userController.sendOtp(req);
		assertEquals(expResult.getStatusCodeValue(), actual.getStatusCodeValue());
	}*/
	
/*	@Test
	public void testAuthentication() {
		
		ResponseEntity<RegisterResDTO> expResult = new ResponseEntity<>(regResDTO, HttpStatus.CREATED);
		when(userService.authentication(regReqDTO)).thenReturn(regResDTO);
		ResponseEntity<RegisterResDTO> actual = userController.authentication(regReqDTO);
		assertEquals(expResult.getStatusCodeValue(), actual.getStatusCodeValue());
		
	}*/

}
