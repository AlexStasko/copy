package by.yakunina.copy.controller;

import by.yakunina.copy.model.auth.Role;
import by.yakunina.copy.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class RoleController {

    private static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;


    @GetMapping("/role")
    public String rolePage(Model model) {
        model.addAttribute("role", new Role());
        return "role";
    }

    @PostMapping("/role")
    public String createRole(@ModelAttribute Role newRole) {
        LOGGER.info(newRole.getName());
        roleService.save(newRole);
        return "redirect:/";
    }
}
