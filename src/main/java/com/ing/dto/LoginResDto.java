package com.ing.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long userId;
	private String userName;
	private String role;
	private String message;
	private int statusCode;
	private String status;
	

}
