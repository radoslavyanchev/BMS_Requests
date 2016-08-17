package com.bms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bsm.exception.DeparmentException;
import com.bsm.exception.ProductException;

public class Product {
	private int id;

	@Size(min = 3, message = "Името на продукта не може да съдържа по малко от 3 символа P")
	@NotNull(message = "Не сте попълнили името на продукта")
	private String name;

	@NotNull(message = "Не сте избрали отдел")
	private Department department;

	public Product() {
		super();
	}
	 // Create product from DB
	 public Product(int id, String name, Department deparment) {
	 this.id = id;
	 setName(name);
	 setDepartment(department);
	 }

	// Create new product in DB
	public Product(String name, Department department) {
		setName(name);
		setDepartment(department);
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

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String toString() {
		return "ID: '" + this.id + "', Name: '" + this.name + "', Department: " + this.department;
	}
}
