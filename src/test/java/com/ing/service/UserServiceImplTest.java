package com.ing.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.maven.artifact.repository.Authentication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.dto.OtpReqDTO;
import com.ing.dto.OtpResDTO;
import com.ing.dto.RegisterReqDTO;
import com.ing.dto.RegisterResDTO;
import com.ing.dto.SmsRequest;
import com.ing.entity.OtpAuth;
import com.ing.entity.User;
import com.ing.exception.EnterValidCredentials;
import com.ing.repository.AuthRepository;
import com.ing.repository.UserRepository;
import com.ing.util.MailWithOTPService;
import com.ing.util.TwilioSmsSender;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceImplTest {

	@Mock
	MailWithOTPService api;

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	TwilioSmsSender twilioSender;

	@Mock
	UserRepository userRepository;

	@Mock
	AuthRepository authRepository;

	OtpReqDTO req = null;
	OtpResDTO otpResDTO = null;
	SmsRequest smsRequest = null;
	OtpAuth otpAuth = null;
	RegisterReqDTO regReqDTO = null;
	RegisterResDTO regResDTO = null;
	User user = null;

	@Before
	public void setUp() {
		req = new OtpReqDTO();
		req.setEmail("dhanashekara123@gmail.com");

		otpResDTO = new OtpResDTO();
		otpResDTO.setStatus("SUCCESS");
		otpResDTO.setMesaage("OTP Sent Successfully");
		otpResDTO.setStatusCode(201);

		smsRequest = new SmsRequest("8882828282", "otp");

		otpAuth = new OtpAuth();
		otpAuth.setOtp(123);
		otpAuth.setOthGen(123);

		regReqDTO = new RegisterReqDTO();
		regReqDTO.setOtpGen(123);
		regReqDTO.setEmail("dhana123@gmail.com");
		
		regResDTO = new RegisterResDTO();
		regResDTO.setStatusCode(201);
		user = new User();
		user.setUserId(1234);
		user.setEmail("dhana123@gmail.com");
		
	}

	@Test
	public void testSendOTP() {

		Mockito.doNothing().when(twilioSender).sendSms(smsRequest);
		lenient().when(authRepository.save(otpAuth)).thenReturn(otpAuth);
		OtpResDTO actual = userService.sendOtp(req);
		assertEquals(201, actual.getStatusCode());
	}

	@Test
	public void testAuthentication() {
		
		when(authRepository.findById(123)).thenReturn(Optional.of(otpAuth));
		when(userRepository.save(user)).thenReturn(user);
		Mockito.doNothing().when(twilioSender).sendSms(smsRequest);
		RegisterResDTO actual = userService.authentication(regReqDTO);
		assertEquals(200, actual.getStatusCode());
	}
	
	@Test(expected = EnterValidCredentials.class)
	public void testInvalidCredException() {
		userService.authentication(regReqDTO);
	}

}
