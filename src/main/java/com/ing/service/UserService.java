package com.ing.service;

import com.ing.dto.OtpReqDTO;
import com.ing.dto.OtpResDTO;
import com.ing.dto.RegisterReqDTO;
import com.ing.dto.RegisterResDTO;

public interface UserService {

	OtpResDTO sendOtp(OtpReqDTO req);
	RegisterResDTO authentication(RegisterReqDTO req);
	public void generateOTPandSendMail(String email);
}
