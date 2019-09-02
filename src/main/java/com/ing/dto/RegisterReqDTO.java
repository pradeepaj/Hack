package com.ing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterReqDTO {
	private String userName;
	private String phoneNumber;
	private String email;
	private String password;
	private String role;
	private int otpGen;
	private int otp;
}
