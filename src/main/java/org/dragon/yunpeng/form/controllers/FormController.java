package org.dragon.yunpeng.form.controllers;

import org.dragon.yunpeng.form.entities.Form;
import org.dragon.yunpeng.form.repositories.FormRepository;
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

	private final FormRepository formRepository;

	@Autowired
	public FormController(FormRepository formRepository) {
		this.formRepository = formRepository;
	}

	@GetMapping("/forms")
	public String listUsers(Model model) {
		model.addAttribute("forms", formRepository.findAll());
		return "form-list";
	}

	@GetMapping("/forms/new")
	public String newForm(Model model) {
		model.addAttribute("form", new Form());
		return "form-detail";
	}

	@PostMapping("/forms/save")
	public String saveForm(@ModelAttribute Form form, BindingResult result) {

		if (result.hasErrors()) {
			return "form-detail";
		}

		formRepository.save(form);
		return "redirect:/forms";
	}

	@GetMapping("/forms/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		Form form = formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
		model.addAttribute("form", form);
		return "form-detail";
	}

	@GetMapping("/forms/delete/{id}")
	public String deleteForm(@PathVariable Long id) {
		formRepository.deleteById(id);
		return "redirect:/forms";
	}

}
