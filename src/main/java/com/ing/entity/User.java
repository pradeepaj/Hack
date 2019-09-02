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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String phoneNumber;
	private String email;
	private String password;
	private String role;
	
	

}
