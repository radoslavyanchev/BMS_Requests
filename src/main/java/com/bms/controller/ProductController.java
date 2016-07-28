package com.bms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.omg.CORBA.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bms.model.DepartmentDAO;
import com.bms.model.Department;
import com.bms.model.Product;
import com.bms.model.ProductDAO;
import com.bsm.exception.DeparmentException;
import com.bsm.exception.ProductException;

@Controller
// @RequestMapping(value = "/product")
public class ProductController {

	@RequestMapping(method = RequestMethod.GET, value = "/product")
	public String showProductForm(Model viewModel) {
		DepartmentDAO deparmentDAO = new DepartmentDAO();
		List<Department> departments = new ArrayList<Department>();
		try {
			departments = deparmentDAO.selectAllDeparments();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		viewModel.addAttribute("departments", departments);
		viewModel.addAttribute("product", new Product());

		return "product";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/product")
	public String addNewProduct(@Valid @ModelAttribute Product product, Model viewModel,
			@ModelAttribute("department") int departmentId, BindingResult result) {
		if (result.hasErrors()) {
			viewModel.addAttribute("error", "Не е попълнено име");
			return showProductForm(viewModel);
		}

		DepartmentDAO departmentDAO = new DepartmentDAO();
		ProductDAO productDAO = new ProductDAO();
		// if (product.getName().length() < 3) {
		// viewModel.addAttribute("error", "Не е попълнено име");
		// return showProductForm(viewModel);
		// }
		System.err
				.println(" Влиза в метода @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		if (departmentId == 0) {
			viewModel.addAttribute("error", "Не е избран отдел!");
			return showProductForm(viewModel);
		}

		try {
			Department department = departmentDAO.getDepartmentById(departmentId);
			product.setDeparment(department);
			productDAO.addProduct(product);
		} catch (ProductException | DeparmentException e) {
			viewModel.addAttribute("error", "Има такъв продукт!");
			e.printStackTrace();
			return showProductForm(viewModel);
		}
		viewModel.addAttribute("success", "Въведохте успешно Продукта: <br>" + product.getName());

		return showProductForm(viewModel);
	}
}
