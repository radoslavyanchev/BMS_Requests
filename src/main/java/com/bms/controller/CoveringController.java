package com.bms.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestParam;
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

		CoveringDAO coveringDAO = new CoveringDAO();
		List<Covering> coveringList = new ArrayList<Covering>();
		try {
			coveringList = coveringDAO.selectAllCoverings();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		viewModel.addAttribute("coveringList", coveringList);
		return "covering";
	}

	@RequestMapping(value = "/covering", method = RequestMethod.POST)
	public String checkCoveringInfo(@Valid Covering covering, BindingResult bindingResult, Model viewModel)
			throws UnsupportedEncodingException {
		covering.setName(new String(covering.getName().getBytes("iso-8859-1"), "UTF-8"));
		DepartmentDAO deparmentDAO = new DepartmentDAO();
		List<Department> departmentsList = new ArrayList<Department>();
		try {
			departmentsList = deparmentDAO.selectAllDeparments();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		viewModel.addAttribute("departmentsList", departmentsList);

		if (bindingResult.hasErrors()) {
			return showForm(covering, viewModel);
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
		return showForm(covering, viewModel);
	}

	@RequestMapping(value = "/coveringDelete", method = RequestMethod.POST)
	public String coveringDelete(Covering covering, Model viewModel, @RequestParam("coveringId") int coveringId) {
		try {
			CoveringDAO coveringDAO = new CoveringDAO();
			String coveringName = coveringDAO.selectCoveringNameById(coveringId);
			coveringDAO.deleteCovering(coveringId);
			viewModel.addAttribute("success", "Изтрихте успешно Покритието: " + coveringName + "<br>");
		} catch (CoveringException e) {
			viewModel.addAttribute("errorName", "Няма покритие с това ID");
			e.printStackTrace();
			return showForm(covering, viewModel);
		}

		return showForm(covering, viewModel);
	}

}
