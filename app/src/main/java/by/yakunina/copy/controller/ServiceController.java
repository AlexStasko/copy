package by.yakunina.copy.controller;

import by.yakunina.copy.model.Order;
import by.yakunina.copy.model.Service;
import by.yakunina.copy.model.ServiceForm;
import by.yakunina.copy.model.ServiceUpdateForm;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.service.EquipmentService;
import by.yakunina.copy.service.MaterialService;
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
    @Resource
    private MaterialService materialService;
    @Resource
    private EquipmentService equipmentService;

    @GetMapping("/service")
    public String getService(Model model) {
        model.addAttribute("service", new ServiceForm());
        model.addAttribute("materials", materialService.getMaterials());
        return "service";
    }

    @PostMapping("/service")
    public String saveService(@ModelAttribute ServiceForm newService, @RequestParam("orderId") String orderId,
                              @RequestParam("fileId") String fileId, Model model) {
        LOGGER.info("Attempt to save new service [{}]", newService);
        Service service = new Service.ServiceBuilder()
                .withFileId(new EntityId(fileId))
                .withComment(newService.getComment())
                .withCopiesNumber(newService.getCopiesNumber())
                .withPages(newService.getPages())
                .withPagesPerSheet(newService.getPagesPerSheet())
                .withMaterial(materialService.findMaterialByName(newService.getMaterial()))
                .build();
        EntityId id = serviceService.createService(service);
        orderService.addService(orderId, service);
        model.addAttribute("orderId", orderId);
        return "/service-continue";
    }

    @GetMapping("/service/{orderId}/save")
    public String saveOrder(@PathVariable("orderId") String orderId) {
        LOGGER.info("attempt to save order with id [{}]", orderId);
        return "redirect:/";
    }

    @GetMapping("/orders/{orderId}/{serviceId}/edit")
    public String getEditOrder(Model model, @PathVariable("serviceId") String serviceId,
                               @PathVariable("orderId") String orderId) {
        LOGGER.info("attempt to edit service with id [{}]", serviceId);
        Order order = orderService.findOrder(orderId);
        Service service = null;
        for (Service s : order.getServices()) {
            if (s.getId().getId().equals(serviceId)) {
                service = s;
            }
        }
        model.addAttribute("serviceId", serviceId);
        model.addAttribute("orderId", orderId);
        model.addAttribute("service", new ServiceUpdateForm());
        model.addAttribute("equipments", equipmentService.findEquipmentsByMaterial(service.getMaterial().getName()));
        return "/service-update";
    }

    @PostMapping("/orders/{orderId}/{serviceId}/save")
    public String editOrder(@PathVariable("serviceId") String serviceId, @PathVariable("orderId") String orderId,
                            @ModelAttribute("service") ServiceUpdateForm serviceUpdateForm) {
        LOGGER.info("attempt to save order with id [{}]", serviceId);
        Order order = orderService.findOrder(orderId);
        Service service = null;
        for (Service s : order.getServices()) {
            if (s.getId().getId().equals(serviceId)) {
                service = s;
            }
        }
        if (service != null) {
            service.setEquipment(equipmentService.findEquipmentByName(serviceUpdateForm.getEquipment()));
        }

        return "redirect:/orders/" + orderId;
    }
}
