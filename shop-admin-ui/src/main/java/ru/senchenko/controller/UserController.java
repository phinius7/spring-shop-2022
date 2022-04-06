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
import ru.senchenko.dao.UserDao;
import ru.senchenko.error.NotFoundException;
import ru.senchenko.services.RoleService;
import ru.senchenko.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<UserDao> users = userService.readAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDao());
        model.addAttribute("roles", roleService.readAll());
        return "add_user";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute UserDao user, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_user";
        }
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.readById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleService.readAll());
        return "edit_user";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute UserDao user, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
