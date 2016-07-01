package com.bms.model;

import com.bsm.exception.CoveringException;
import com.bsm.exception.DeparmentException;

public class Covering {
	private int id;
	private String name;
	private Department deparment;
	
	//Create covering from DB
	public Covering(int id, String name, Department deparment) {
		this.id = id;
		try {
			setDeparment(deparment);
			setName(name);
		} catch (CoveringException | DeparmentException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	// Create new covering in DB
	public Covering(String name, Department deparment) {
		try {
			setDeparment(deparment);
			setName(name);
		} catch (CoveringException | DeparmentException e) {
			e.getMessage();
			e.printStackTrace();
		}

	}

	private String getName() {
		return name;
	}

	private void setName(String name) throws CoveringException {
		if (name != null) {
			this.name = name;
		} else {
			throw new CoveringException("Invalid name for covering");
		}
	}

	public Department getDeparment() {
		return deparment;
	}

	private void setDeparment(Department deparment) throws DeparmentException {
		if (deparment != null) {
			this.deparment = deparment;
		} else {
			throw new DeparmentException("Invalid deparment in for cocering.");
		}
	}

	public int getId() {
		return id;
	}

}
