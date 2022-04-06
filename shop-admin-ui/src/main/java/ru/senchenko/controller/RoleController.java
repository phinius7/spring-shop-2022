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
import ru.senchenko.dao.RoleDao;
import ru.senchenko.error.NotFoundException;
import ru.senchenko.services.RoleService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllRoles(Model model) {
        List<RoleDao> roles = roleService.readAll();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @GetMapping("/add")
    public String addRole(Model model) {
        model.addAttribute("role", new RoleDao());
        return "add_role";
    }

    @PostMapping("/add")
    public String addRole(@Valid @ModelAttribute RoleDao role, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_role";
        }
        roleService.create(role);
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable Integer id, Model model) {
        model.addAttribute("role", roleService.readById(id).orElseThrow(NotFoundException::new));
        return "edit_role";
    }

    @PostMapping("/edit")
    public String editRole(@ModelAttribute RoleDao role, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_role";
        }
        roleService.create(role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Integer id) {
        roleService.delete(id);
        return "redirect:/roles";
    }
}
