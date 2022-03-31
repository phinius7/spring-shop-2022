package ru.senchenko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("activePage", "0");
        return "index";
    }
}
