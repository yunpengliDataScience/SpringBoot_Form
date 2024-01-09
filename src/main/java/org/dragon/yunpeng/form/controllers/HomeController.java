package org.dragon.yunpeng.form.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String redirect() {

		// Redirect to another controller method
		return "redirect:/forms";
	}
}
