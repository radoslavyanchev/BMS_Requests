package com.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@RequestMapping(method = RequestMethod.GET)
	public String product(Model model) {
		return "product";
	}
}
