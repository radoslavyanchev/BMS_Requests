package com.bms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Covering {
	private int id;

	@Size(min = 3, message = "Името на Покритието не може да съдържа по малко от 3 символа")
	@NotNull(message = "Полето не може да бъде празно.")
	private String name;

	@NotNull(message = "Не сте избрали отдел.")
	private Department department;

	public Covering() {
		super();
	}

	// Create covering from DB
	public Covering(int id, String name, Department deparment) {
		this.id = id;
		setName(name);
		setDepartment(deparment);
	}

	// Create new covering in DB
	public Covering(String name, Department deparment) {
		setName(name);
		setDepartment(deparment);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department deparment) {
		this.department = deparment;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "ID: '" + this.id + "', Name: '" + this.name + "', Department: " + this.department;
	}
}
