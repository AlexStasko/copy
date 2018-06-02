package by.yakunina.copy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/login-error")
    public String returnError(Model model) {
        model.addAttribute("title", "Error");
        return "login-error";
    }
}
