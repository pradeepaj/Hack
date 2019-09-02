package com.ing.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.util.MailWithOTPService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class MailServiceTest {
	

	@Mock
	MailWithOTPService api;

	@InjectMocks
	MailService mailService;
	

	@Test
	public void testGenerateOTPandSendMail() {
		
		Mockito.doNothing().when(api).sendEmail("dhanashekara123@gmail.com","ING Transactions","OTP for abc");
		String actual = mailService.generateOTPandSendMail("dhanashekara123@gmail.com");
		
		assertEquals("SUCCESS", actual);
		
	}
	
	@Test(expected = Exception.class)
	public void exceptionTest() throws Exception {
		
		Mockito.doNothing().when(api).sendEmail("dhanashekara123@gmail.com","ING Transactions","OTP for abc");
		mailService.generateOTPandSendMail("abc");
		throw new Exception("");
	}
}
