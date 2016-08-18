package com.bms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Department {

	int id;

	@Size(min = 2, message = "Името на отдела не може да съдържа по малко от 2 символа")
	@NotNull(message = "Полето не може да бъде празно.")
	private String name;

	public Department() {
		super();
	}

	// Create department form DB
	public Department(int id, String name) {
		this.id = id;
		setName(name);
	}

	// Create department in DB
	public Department(String name) {
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

	public String toString() {
		return String.valueOf(this.id);
	}
}
