package by.yakunina.copy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/login-error")
    public String returnError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
