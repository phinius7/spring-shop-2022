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
import ru.senchenko.dao.ProductDao;
import ru.senchenko.error.NotFoundException;
import ru.senchenko.repositories.ProductRepo;
import ru.senchenko.services.CategoryService;
import ru.senchenko.services.ProductService;

@Controller
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepo productRepo;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductRepo productRepo, ProductService productService, CategoryService categoryService) {
        this.productRepo = productRepo;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.readAll());
        return "products";
    }

    @GetMapping("/product/add")
    public String addProduct(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("product", new ProductDao());
        model.addAttribute("categories", categoryService.readAll());
        return "product_form";
    }

    @GetMapping("/product/{id}/edit")
    public String editProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("product", productService.readById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("categories", categoryService.readAll());
        return "product_form";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @PostMapping("/product")
    public String upsertProduct(Model model, RedirectAttributes redirectAttributes, ProductDao product) {
        try {
            productService.create(product);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating product", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (product.getId() == null) {
                return "redirect:/product/add";
            }
            return "redirect:/product/" + product.getId() + "/edit";
        }
        return "redirect:/products";
    }
}
