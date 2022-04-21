package ru.senchenko.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.senchenko.dao.CategoryDao;
import ru.senchenko.error.NotFoundException;
import ru.senchenko.repositories.CategoryRepo;
import ru.senchenko.services.CategoryService;

@Controller
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryRepo categoryRepo;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo, CategoryService categoryService) {
        this.categoryRepo = categoryRepo;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.readAll());
        return "categories";
    }

    @GetMapping("/category/add")
    public String addCategory(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("category", new CategoryDao());
        return "category_form";
    }

    @GetMapping("/category/{id}/edit")
    public String editCategory(@PathVariable Integer id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("category", categoryService.readById(id).orElseThrow(NotFoundException::new));
        return "category_form";
    }

    @GetMapping("/category/{id}/delete")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

    @PostMapping("/category")
    public String upsertCategory(Model model, RedirectAttributes redirectAttributes, CategoryDao categoryDao) {
        try {
            categoryService.create(categoryDao);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating product", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (categoryDao.getId() == null) {
                return "redirect:/category/add";
            }
            return "redirect:/category/" + categoryDao.getId() + "/edit";
        }
        return "redirect:/categories";
    }
}
