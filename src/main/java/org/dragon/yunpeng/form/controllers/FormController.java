package org.dragon.yunpeng.form.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dragon.yunpeng.form.entities.Form;
import org.dragon.yunpeng.form.services.IFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

	Logger logger = LoggerFactory.getLogger(FormController.class);

	@Autowired
	private IFormService formService;

	@GetMapping("/forms")
	public String listForms(Model model) {
		model.addAttribute("forms", formService.getAllForms());
		return "form-list";
	}

	@GetMapping("/forms/export")
	public String exportForms(Model model) {

		formService.exportData();

		model.addAttribute("forms", formService.getAllForms());
		return "form-list";
	}

	@GetMapping("/forms/new")
	public String newForm(Model model, HttpServletRequest request) {

		List<String> codes = formService.readCodeFromFile();

		Form form = new Form();
		form.setCodes(codes);

		model.addAttribute("form", form);
		return "form-detail";
	}

	@PostMapping("/forms/save")
	public String saveForm(@ModelAttribute Form form, BindingResult result) {

		if (result.hasErrors()) {
			return "form-detail";
		}

		formService.saveForm(form);
		return "redirect:/forms";
	}

	@GetMapping("/forms/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		Form form = formService.getForm(id);

		List<String> codes = formService.readCodeFromFile();
		form.setCodes(codes);

		model.addAttribute("form", form);
		return "form-detail";
	}

	@GetMapping("/forms/delete/{id}")
	public String deleteForm(@PathVariable Long id) {
		formService.deleteForm(id);
		return "redirect:/forms";
	}

}
