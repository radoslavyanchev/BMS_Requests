package com.bms.controller;

import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/index")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "index";
	}	

}
