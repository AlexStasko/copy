package by.yakunina.copy.controller;

import by.yakunina.copy.model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

    @GetMapping("/service")
    public String getService(Model model) {
        model.addAttribute("service", new Service());
        return "service";
    }

    @PostMapping("/service")
    public String saveService(@ModelAttribute Service newService, RedirectAttributes redirectAttributes) {
        LOGGER.info("Attempt to save new service [{}]", newService);

        return "/service-continue";
    }

    @GetMapping("/service/{orderId}/save")
    public String saveOrder(@PathVariable("orderId") String orderId) {
        LOGGER.info("attempt to save order with id [{}]", orderId);
        return "redirect:/";
    }
}
