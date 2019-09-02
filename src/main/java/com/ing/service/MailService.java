package com.ing.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.util.MailWithOTPService;

@Service
public class MailService {

	private static final Logger logger = LoggerFactory.getLogger(MailService.class);

	@Autowired
	MailWithOTPService mailWithOTPService;

	public String testMail(String email) {
		return generateOTPandSendMail(email);
	}

	public String generateOTPandSendMail(String email) {

		logger.info("generateOTPandSendMail for mail id {} " ,email);
		Integer otp = 0;
		String status;
		try {
			otp = 100000 + new Random().nextInt(900000);
			String body = "OTP for ING Transaction " + otp;
			String subject = "ING Bank Transactions";
			mailWithOTPService.sendEmail(email, subject, body);
			status = "SUCCESS";
		} catch (Exception e) {
			logger.info("Error in generating OTP ");
			status = "FAILURE";
		}
		return status;
	}
}
