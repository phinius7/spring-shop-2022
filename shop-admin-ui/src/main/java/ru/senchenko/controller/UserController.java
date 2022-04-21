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
import ru.senchenko.dao.UserDao;
import ru.senchenko.error.NotFoundException;
import ru.senchenko.services.RoleService;
import ru.senchenko.services.UserService;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<UserDao> users = userService.readAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("user", new UserDao());
        model.addAttribute("roles", roleService.readAll());
        return "user_form";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable Integer id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("user", userService.readById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleService.readAll());
        return "user_form";
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("/user")
    public String upsertUser(Model model, RedirectAttributes redirectAttributes, UserDao userDao) {
        try {
            userService.create(userDao);
        } catch (Exception e) {
            logger.error("Problem with creating or updating user", e);
            redirectAttributes.addFlashAttribute("error", true);
            if (userDao.getId() == null) {
                return "redirect:/user/add";
            }
            return "redirect:/user/" + userDao.getId() + "/edit";
        }
        return "redirect:/users";
    }
}
