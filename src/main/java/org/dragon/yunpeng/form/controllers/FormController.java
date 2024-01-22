package org.dragon.yunpeng.form.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dragon.yunpeng.form.entities.Form;
import org.dragon.yunpeng.form.entities.FormList;
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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Controller
public class FormController {

	Logger logger = LoggerFactory.getLogger(FormController.class);

	private final FormRepository formRepository;

	@Autowired
	public FormController(FormRepository formRepository) {
		this.formRepository = formRepository;
	}

	@GetMapping("/forms")
	public String listForms(Model model) {
		model.addAttribute("forms", formRepository.findAll());
		return "form-list";
	}

	@GetMapping("/forms/export")
	public String exportForms(Model model) {

		exportData();

		model.addAttribute("forms", formRepository.findAll());
		return "form-list";
	}

	private void exportData() {
		try {

			List<Form> forms = (List<Form>) formRepository.findAll();

			FormList formList = new FormList(forms);
			// Create JAXB context and marshaller
			JAXBContext context = JAXBContext.newInstance(FormList.class);
			Marshaller marshaller = context.createMarshaller();

			// Convert the list to XML and write to a file
			File outputFile = new File("forms.xml");

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(formList, outputFile);

			System.out.println("XML file created at: " + outputFile.getAbsolutePath());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/forms/new")
	public String newForm(Model model, HttpServletRequest request) {

		List<String> codes = readCodeFromFile();

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

		formRepository.save(form);
		return "redirect:/forms";
	}

	@GetMapping("/forms/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		Form form = formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));

		List<String> codes = readCodeFromFile();
		form.setCodes(codes);

		model.addAttribute("form", form);
		return "form-detail";
	}

	@GetMapping("/forms/delete/{id}")
	public String deleteForm(@PathVariable Long id) {
		formRepository.deleteById(id);
		return "redirect:/forms";
	}

	private List<String> readCodeFromFile() {

		List<String> codes = new ArrayList<String>();

		String fileName = "code.txt";

		// Get the current working directory of the java process
		String workingDirectory = System.getProperty("user.dir");
		System.out.println(workingDirectory);

		// Construct the absolute path to the file
		String filePath = workingDirectory + File.separator + fileName;

		// Read the file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
				// Process each line of the file
				System.out.println(line);
				codes.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return codes;
	}
}
