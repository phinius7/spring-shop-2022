package ru.senchenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.senchenko.dao.CategoryDao;
import ru.senchenko.error.NotFoundException;
import ru.senchenko.repositories.CategoryRepo;
import ru.senchenko.services.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepo categoryRepo;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo, CategoryService categoryService) {
        this.categoryRepo = categoryRepo;
        this.categoryService = categoryService;
    }

    @GetMapping("/login")
    public String loginPage() {return "login";}

    @GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.readAll());
        return "categories";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new CategoryDao());
        return "add_category";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute CategoryDao categoryDao, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_category";
        }
        categoryService.create(categoryDao);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.readById(id).orElseThrow(NotFoundException::new));
        return "edit_category";
    }

    @PostMapping("/edit")
    public String editCategory(@ModelAttribute CategoryDao categoryDao, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_category";
        }
        categoryService.create(categoryDao);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
