package org.dragon.yunpeng.form.controllers;

import org.dragon.yunpeng.form.entities.Category;
import org.dragon.yunpeng.form.entities.SubCategory;
import org.dragon.yunpeng.form.repositories.CategoryRepository;
import org.dragon.yunpeng.form.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DropdownController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@GetMapping("/categories")
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@GetMapping("/subcategories/{categoryId}")
	public List<SubCategory> getSubCategories(@PathVariable Long categoryId) {
		
		System.out.println("Hi="+categoryId);
		return subCategoryRepository.findByCategoryId(categoryId);
	}
}
