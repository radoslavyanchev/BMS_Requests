package com.bms.controller;

import java.io.UnsupportedEncodingException;

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

import com.bms.model.Department;
import com.bms.model.DepartmentDAO;
import com.bms.model.DepartmentEditor;
import com.bsm.exception.DeparmentException;

@Controller
public class DepartmentController extends WebMvcConfigurerAdapter {

	// trims white spaces from form input
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// trims white spaces from form input
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);

		binder.registerCustomEditor(Department.class, new DepartmentEditor());
	}

	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public String showForm(Department department, Model viewModel) {

		return "department";
	}

	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public String checkDepartmentInfo(@Valid Department department, BindingResult bindingResult, Model viewModel) throws UnsupportedEncodingException {
		department.setName(new String (department.getName().getBytes ("iso-8859-1"), "UTF-8"));
//		new String (s.getBytes ("iso-8859-1"), "UTF-8");
		System.err.println(department.getName() + "Пешо ти ли си !!!!");
		if (bindingResult.hasErrors()) {
			return "department";
		}
		DepartmentDAO deparmentDAO = new DepartmentDAO();
		try {
			System.out.println(department.getName());
			deparmentDAO.addDepartment(department);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
		} catch (DeparmentException e) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@3");
			viewModel.addAttribute("errorName", "Отдел с това име съществува");
			e.printStackTrace();
			return showForm(department, viewModel);
		}

		if (bindingResult.hasErrors()) {
			return "department";
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@4");
		viewModel.addAttribute("success", "Въведохте успешно Отдела: <br>" + department.getName());
		return showForm(department, viewModel);
	}

}
