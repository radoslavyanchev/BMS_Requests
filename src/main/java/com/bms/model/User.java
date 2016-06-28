package com.bms.model;

import com.bsm.exception.UsernameException;

public class User {
	private int id;
	private String name;
	private Department deparment;
	private String password;

	public User(int id, String name, Department deparment, String password) {
		this.id = id;
		try {
			setName(name);
		} catch (UsernameException e) {
			e.getMessage();
			e.printStackTrace();
		}
		setDepartment(deparment);
		setPassword(password);
	}

	public User(String name, Department deparment, String password) {
		try {
			setName(name);
		} catch (UsernameException e) {
			e.getMessage();
			e.printStackTrace();
		}
		setDepartment(deparment);
		setPassword(password);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws UsernameException {
		if (name != null) {
			if (name.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)")) {
				this.name = name;
			}
		} else {
			throw new UsernameException("Your username is incorrect.Please try again!");
		}
	}

	public Department getDepartment() {
		return deparment;
	}

	public void setDepartment(Department deparment) {
		if (deparment != null) {
			this.deparment = deparment;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null) {
			this.password = password;
		}
	}

}
