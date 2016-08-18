package com.bms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bms.model.DepartmentDAO;
import com.bms.model.DepartmentEditor;
import com.bms.model.Covering;
import com.bms.model.CoveringDAO;
import com.bms.model.Department;
import com.bsm.exception.CoveringException;
import com.bsm.exception.DeparmentException;

@Controller
public class CoveringController extends WebMvcConfigurerAdapter {

	// trims white spaces from form input
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// trims white spaces from form input
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);

		binder.registerCustomEditor(Department.class, new DepartmentEditor());
	}

	@RequestMapping(value = "/covering", method = RequestMethod.GET)
	public String showForm(Covering covering, Model viewModel) {
		DepartmentDAO deparmentDAO = new DepartmentDAO();
		List<Department> departmentsList = new ArrayList<Department>();
		try {
			departmentsList = deparmentDAO.selectAllDeparments();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		viewModel.addAttribute("departmentsList", departmentsList);

		return "covering";
	}

	@RequestMapping(value = "/covering", method = RequestMethod.POST)
	public String checkProductInfo(@Valid Covering covering, BindingResult bindingResult, Model viewModel) {

		DepartmentDAO deparmentDAO = new DepartmentDAO();
		List<Department> departmentsList = new ArrayList<Department>();
		try {
			departmentsList = deparmentDAO.selectAllDeparments();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		viewModel.addAttribute("departmentsList", departmentsList);

		if (bindingResult.hasErrors()) {
			return "covering";
		}

		try {
			CoveringDAO coveringDAO = new CoveringDAO();
			coveringDAO.addCovering(covering);
			viewModel.addAttribute("success", "Въведохте успешно Покритието: <br>" + covering.getName());
		} catch (CoveringException e) {
			viewModel.addAttribute("errorName", "Покритие с това име съществува");
			e.printStackTrace();
			return showForm(covering, viewModel);
		}
		return "covering";
	}

}
