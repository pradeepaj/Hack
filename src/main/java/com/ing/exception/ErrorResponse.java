package com.ing.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private int statusCode;
	private String status;

}