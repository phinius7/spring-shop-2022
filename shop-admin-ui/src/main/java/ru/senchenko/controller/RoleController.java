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
import ru.senchenko.dao.RoleDao;
import ru.senchenko.error.NotFoundException;
import ru.senchenko.services.RoleService;

import java.util.List;

@Controller
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public String showAllRoles(Model model) {
        List<RoleDao> roles = roleService.readAll();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @GetMapping("/role/add")
    public String addRole(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("role", new RoleDao());
        return "role_form";
    }

    @GetMapping("/role/{id}/edit")
    public String editRole(@PathVariable Integer id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("role", roleService.readById(id).orElseThrow(NotFoundException::new));
        return "role_form";
    }

    @GetMapping("/role/{id}/delete")
    public String deleteRole(@PathVariable Integer id) {
        roleService.delete(id);
        return "redirect:/roles";
    }

    @PostMapping("/role")
    public String upsertRole(Model model, RedirectAttributes redirectAttributes, RoleDao roleDao) {
        try {
            roleService.create(roleDao);
        } catch (Exception e) {
            logger.error("Problem with creating or updating role", e);
            redirectAttributes.addFlashAttribute("error", true);
            if (roleDao.getId() == null) {
                return "redirect:/role/add";
            }
            return "redirect:/role/" + roleDao.getId() + "/edit";
        }
        return "redirect:/roles";
    }
}
