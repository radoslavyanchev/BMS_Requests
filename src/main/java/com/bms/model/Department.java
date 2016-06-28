package com.bms.model;

import com.bsm.exception.DeparmentException;

public class Department {
	int id;
	String name;

	public Department(int id, String name) {
		this.id = id;
		try {
			setName(name);
		} catch (DeparmentException e) {
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

	public void setName(String name) throws DeparmentException {
		if (name.length() > 1) {
			this.name = name;
		} else {
			throw new DeparmentException("Invalid name for Depatment");
		}
	}

}
