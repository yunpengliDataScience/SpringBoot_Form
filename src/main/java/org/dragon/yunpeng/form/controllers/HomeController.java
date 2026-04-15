package org.dragon.yunpeng.form.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/forwardToForms")
	public String forms() {
		return "redirect:/forms";
	}

	@GetMapping("/programs")
	public String redirectaPrograms() {

		// Redirect to another controller method
		return "programs";
	}
}
