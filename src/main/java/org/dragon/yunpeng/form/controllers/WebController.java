package org.dragon.yunpeng.form.controllers;

import java.util.List;

import org.dragon.yunpeng.form.entities.Category;
import org.dragon.yunpeng.form.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/dropDown")
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "dropDown";
    }
}
