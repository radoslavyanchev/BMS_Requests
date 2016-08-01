package com.bms.model;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bsm.exception.DeparmentException;

@Component
public class DepartmentEditor  extends PropertyEditorSupport {

	// Converts a String to a Department (when submitting form)
    @Override
    public void setAsText(String text) {
    	DepartmentDAO deparmentDAO = new DepartmentDAO();
        Department d = null;
		try {
			d = deparmentDAO.getDepartmentById(Integer.parseInt(text));
		} catch (NumberFormatException | DeparmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        this.setValue(d);
    }

    // Converts a Department to a String (when displaying form)
    @Override
    public String getAsText() {
    	Department d = new Department();
    	if (this.getValue() != null) {
    		d = (Department) this.getValue();
    	}
        return String.valueOf(d.getId());
    }

}
