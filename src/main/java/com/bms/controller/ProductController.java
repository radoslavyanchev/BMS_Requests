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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bms.model.DepartmentDAO;
import com.bms.model.DepartmentEditor;
import com.bms.model.Department;
import com.bms.model.Product;
import com.bms.model.ProductDAO;
import com.bsm.exception.DeparmentException;
import com.bsm.exception.ProductException;

@Controller
public class ProductController extends WebMvcConfigurerAdapter {

	// trims white spaces from form input
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// trims white spaces from form input
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);

		binder.registerCustomEditor(Department.class, new DepartmentEditor());
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String showForm(Product product, Model viewModel) {
		DepartmentDAO deparmentDAO = new DepartmentDAO();
		List<Department> departmentsList = new ArrayList<Department>();
		try {
			departmentsList = deparmentDAO.selectAllDeparments();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		viewModel.addAttribute("departmentsList", departmentsList);

		return "product";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String checkProductInfo(@Valid Product product, BindingResult bindingResult, Model viewModel) throws UnsupportedEncodingException {
		product.setName(new String (product.getName().getBytes ("iso-8859-1"), "UTF-8"));
		DepartmentDAO deparmentDAO = new DepartmentDAO();
		List<Department> departmentsList = new ArrayList<Department>();
		try {
			departmentsList = deparmentDAO.selectAllDeparments();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		viewModel.addAttribute("departmentsList", departmentsList);

		if (bindingResult.hasErrors()) {
			return "product";
		}

		try {
			ProductDAO productDAO = new ProductDAO();
			productDAO.addProduct(product);
			viewModel.addAttribute("success", "Въведохте успешно Продукта: <br>" + product.getName());
		} catch (ProductException e) {
			viewModel.addAttribute("errorName", "Продукт с това име съществува");
			e.printStackTrace();
			return showForm(product, viewModel);
		}

		return "product";
	}

}
