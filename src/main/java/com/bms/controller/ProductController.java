package com.bms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bms.model.DeparmentDAO;
import com.bms.model.Department;
import com.bms.model.Product;
import com.bsm.exception.DeparmentException;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@RequestMapping(method = RequestMethod.GET)
	public String product(@ModelAttribute Product product, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		DeparmentDAO deparmentDAO = new DeparmentDAO();
		List<Department> departments = new ArrayList<Department>();

		// if (request.getSession().getAttribute("user") == null) {
		// return "error";
		// }
		try {
			departments = deparmentDAO.selectAllDeparments();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		model.addAttribute("product", new Product());
		model.addAttribute("deparments", departments);

		return "product";
	}
}
