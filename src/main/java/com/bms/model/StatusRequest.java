package com.bms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StatusRequest {
	private int id;

	@Size(min = 4, message = "Името на STATUS не може да съдържа по малко от 4 символа")
	@NotNull(message = "Полето не може да бъде празно.")
	private String name;
	
	public StatusRequest() {
		super();
	}	

	// Creat Status from DB
	public StatusRequest(int id, String name) {
		this.id = id;
		setName(name);
	}

	// Creat Status in DB
	public StatusRequest(String name) {
		this.id = id;
		setName(name);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
