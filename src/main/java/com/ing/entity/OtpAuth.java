package com.ing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OtpAuth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int othGen;
	private int otp;
}
