package by.yakunina.copy.controller;

import by.yakunina.copy.model.Service;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.service.OrderService;
import by.yakunina.copy.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
public class ServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

    @Resource
    private ServiceService serviceService;
    @Resource
    private OrderService orderService;

    @GetMapping("/service")
    public String getService(Model model) {
        model.addAttribute("service", new Service());
        return "service";
    }

    @PostMapping("/service")
    public String saveService(@ModelAttribute Service newService, @RequestParam("orderId") String orderId,
                              @RequestParam("fileId") String fileId, Model model) {
        LOGGER.info("Attempt to save new service [{}]", newService);
        newService.setFileId(new EntityId(fileId));
        EntityId id = serviceService.createService(newService);
        orderService.addService(orderId, newService);
        model.addAttribute("orderId", orderId);
        return "/service-continue";
    }

    @GetMapping("/service/{orderId}/save")
    public String saveOrder(@PathVariable("orderId") String orderId) {
        LOGGER.info("attempt to save order with id [{}]", orderId);
        return "redirect:/";
    }
}
