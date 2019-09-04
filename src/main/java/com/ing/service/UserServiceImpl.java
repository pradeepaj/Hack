package com.ing.service;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TwilioSmsSender twilioSmsSender;
	@Autowired
	MailWithOTPService mailWithOTPService;

	@Override
	public OtpResDTO sendOtp(OtpReqDTO req) {

		Integer otp;
		Random rand = new Random();
		otp = 1000 + rand.nextInt(9999);
		String message = "OTP Verification : " + otp;

		SmsRequest smsRequest = new SmsRequest(req.getPhoneNumber(), message);

		twilioSmsSender.sendSms(smsRequest);
		generateOTPandSendMail(req.getEmail());

		OtpAuth otpAuth = new OtpAuth();
		otpAuth.setOtp(otp);
		authRepository.save(otpAuth);
		OtpResDTO otpResDTO = new OtpResDTO();
		otpResDTO.setStatus("SUCCESS");
		otpResDTO.setOtpGen(otpAuth.getOthGen());
		otpResDTO.setMesaage("OTP Sent Successfully");
		otpResDTO.setStatusCode(HttpStatus.CREATED.value());

		return otpResDTO;
	}

	@Override
	public RegisterResDTO authentication(RegisterReqDTO req) {
		Optional<OtpAuth> auth = authRepository.findById(req.getOtpGen());
		if (auth.isPresent()) {
			User user = new User();
			BeanUtils.copyProperties(req, user);
			userRepository.save(user);
			String message = "Your Registration is Successfull. User Id:" + user.getUserId();

			SmsRequest smsRequest = new SmsRequest(req.getPhoneNumber(), message);

			twilioSmsSender.sendSms(smsRequest);
			
			RegisterResDTO resDTO = new RegisterResDTO();
			resDTO.setUserId(user.getUserId());
			resDTO.setStatus("SUCCESS");
			resDTO.setMesaage("OTP Sent Successfully");
			resDTO.setStatusCode(HttpStatus.OK.value());
			return resDTO;
		} else {
			throw new EnterValidCredentials("Please Enter Valid OTP");
		}

	}

	@Override
	public void generateOTPandSendMail(String email) {

		logger.info("generateOTPandSendMail for mail id {} ", email);
		Integer otp = 0;
		try {
			otp = 100000 + new Random().nextInt(900000);
			String body = "OTP for ING Transaction " + otp;
			String subject = "ING Bank Transactions";
			mailWithOTPService.sendEmail(email, subject, body);
		} catch (Exception e) {
			logger.info("Error in generating OTP {}",e);
		}
	}

}
