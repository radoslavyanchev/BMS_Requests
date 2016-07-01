package com.bms.model;

import com.bsm.exception.StatusRequestExceptions;

public class StatusRequest {
	private int id;
	private String name;

	// Creat Status from DB
	public StatusRequest(int id, String name) {
		this.id = id;
		try {
			setName(name);
		} catch (StatusRequestExceptions e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	// Creat Status in DB
	public StatusRequest(String name) {
		this.id = id;
		try {
			setName(name);
		} catch (StatusRequestExceptions e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws StatusRequestExceptions {
		if (name.length() > 5) {
			this.name = name;
		} else {
			throw new StatusRequestExceptions("Invalid name for StatusRequest");
		}
	}

}
