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
import ru.senchenko.dao.ProductDao;
import ru.senchenko.error.NotFoundException;
import ru.senchenko.repositories.ProductRepo;
import ru.senchenko.services.CategoryService;
import ru.senchenko.services.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepo productRepo;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductRepo productRepo, ProductService productService, CategoryService categoryService) {
        this.productRepo = productRepo;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.readAll());
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductDao());
        model.addAttribute("categories", categoryService.readAll());
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute ProductDao productDao, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_product";
        }
        productService.create(productDao);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.readById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("categories", categoryService.readAll());
        return "edit_product";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute ProductDao productDao, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_product";
        }
        productService.create(productDao);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
