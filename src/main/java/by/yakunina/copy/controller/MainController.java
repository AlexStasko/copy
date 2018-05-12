package by.yakunina.copy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String returnIndex(Model model) {
        model.addAttribute("title", "Hello");
        return "index";
    }

}
