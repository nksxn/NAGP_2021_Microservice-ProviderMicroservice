package com.nagarro.nagp.providers.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Provider {

	private String firstName;

	private String lastName;

	private String expertise;

	private String phone;
	
	private String email;

	private boolean active;

	private String creationTime;

	private String modifiedTime;

	private String username;

	private String password;

}
