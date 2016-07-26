package com.bms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bms.model.DepartmentDAO;
import com.bms.model.Department;
import com.bms.model.Product;
import com.bms.model.ProductDAO;
import com.bsm.exception.DeparmentException;
import com.bsm.exception.ProductException;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@RequestMapping(method = RequestMethod.GET)
	public String showProductForm(@ModelAttribute Product product, Model model) {
		DepartmentDAO deparmentDAO = new DepartmentDAO();
		List<Department> departments = new ArrayList<Department>();
		try {
			departments = deparmentDAO.selectAllDeparments();
		} catch (DeparmentException e) {
			e.printStackTrace();
		}
		model.addAttribute("departments", departments);

		return "product";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addNewProduct(@ModelAttribute Product product, Model viewModel,@ModelAttribute("department") int departmentId) {
		DepartmentDAO departmentDAO= new DepartmentDAO();
		ProductDAO productDAO = new ProductDAO();
		
		try {
			if(departmentId==0){
				viewModel.addAttribute("error", "Не е избран отдел!");
			}
			Department department = departmentDAO.getDepartmentById(departmentId);
			product.setDeparment(department);
			productDAO.addProduct(product);
		} catch (ProductException | DeparmentException e) {
			viewModel.addAttribute("error", "Има такъв продукт!");
			e.printStackTrace();
			return "product";
		}
		
		return "product";
	}
}
