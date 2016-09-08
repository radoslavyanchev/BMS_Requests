package com.bms.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.bms.model.StatusRequest;
import com.bms.model.StatusRequestDAO;
import com.bsm.exception.StatusRequestException;

@Controller
public class StatusRequestController extends WebMvcConfigurerAdapter {

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String showForm(StatusRequest statusReques, Model viewModel) {

		return "status";
	}

	@RequestMapping(value = "/status", method = RequestMethod.POST)
	public String checkDepartmentInfo(@Valid StatusRequest statusReques, BindingResult bindingResult, Model viewModel) {

		if (bindingResult.hasErrors()) {
			return "status";
		}
		StatusRequestDAO statusRequestDAO = new StatusRequestDAO();
		try {
			statusRequestDAO.addStatusRequest(statusReques);
		} catch (StatusRequestException e) {
			viewModel.addAttribute("errorName", "Състояние с това име съществува");
			e.printStackTrace();
			return showForm(statusReques, viewModel);
		}

		if (bindingResult.hasErrors()) {
			return "status";
		}
		viewModel.addAttribute("success", "Въведохте успешно Състояние: <br>" + statusReques.getName());
		return "status";
	}

}
