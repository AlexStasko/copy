package by.yakunina.copy.controller;

import by.yakunina.copy.model.Customer;
import by.yakunina.copy.model.Employee;
import by.yakunina.copy.model.Order;
import by.yakunina.copy.model.OrderUpdateStatusForm;
import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.auth.CopyUser;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.service.AccountService;
import by.yakunina.copy.service.CustomerService;
import by.yakunina.copy.service.EmployeeService;
import by.yakunina.copy.service.OrderService;
import by.yakunina.copy.support.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private AccountService accountService;
    @Resource
    private CustomerService customerService;
    @Resource
    private FileController fileController;
    @Resource
    private OrderService orderService;
    @Resource
    private EmployeeService employeeService;

    @GetMapping("/orders")
    public String getOrders(Model model, Authentication authentication) {
        List<Order> orders;

        CopyUser user = (CopyUser) authentication.getPrincipal();

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            Account account = accountService.authenticate(user.getUsername());
            Customer customer = customerService.findCustomer(account.getUserId());
            orders = orderService.findOrdersByUserId(customer.getId().getId());
        } else {
            orders = orderService.findAll();
        }
        LOGGER.info("Attempt to get orders [{}]", orders);
        model.addAttribute("orders", orders);

        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String getOrder(@PathVariable("id") String id, Model model) {
        Order requestedOrder = orderService.findOrder(id);
        if (requestedOrder != null) {
            model.addAttribute("order", requestedOrder);
        } else {
            model.addAttribute("message", "No order with id [" + id + "]");
        }
        return "order";
    }

    @GetMapping("/orders/create")
    public String createOrder(Model model, Authentication authentication) {
        Order order = new Order();

        CopyUser user = (CopyUser) authentication.getPrincipal();

        String username = user.getUsername();
        LOGGER.info("User [{}] is authenticated", username);
        Account account = accountService.authenticate(username);
        LOGGER.info("Account:[{}]", account);
        Customer customer = customerService.findCustomer(account.getUserId());
        LOGGER.info("User [{}] tries to create order", customer);
        order.setCustomer(customer);
        order.setStatus("в режиме создания");
        EntityId id = orderService.createOrder(order);

        model.addAttribute("orderId", id.getId());
        return fileController.listUploadedFiles(model);
    }

    @GetMapping("/orders/{orderId}/edit")
    public String getOrderEditPage(@PathVariable("orderId") String orderId, Model model) {
        Order order = orderService.findOrder(orderId);
        model.addAttribute("order", order);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("orderForm", new OrderUpdateStatusForm());
        return "order-update";
    }

    @PostMapping("/orders/{orderId}/edit")
    public String editOrder(@PathVariable("orderId") String orderId, @ModelAttribute OrderUpdateStatusForm orderForm) {
        Order order = orderService.findOrder(orderId);
        Employee employee = employeeService.findEmployee(orderForm.getEmployeeId());
        order.setEmployee(employee);
        order.setStatus(orderForm.getStatus());
        if (orderForm.getStatus().equals("Выполнен") && (order.getExecutionDate() == null)) {
            order.setExecutionDate(LocalDate.now());
        } else if (orderForm.getStatus().equals("Выдан") && (order.getDeliveryDate() == null)) {
            order.setDeliveryDate(LocalDate.now());
        }
        orderService.update(order);

        return "redirect:/orders/" + orderId;
    }

    @PostMapping("/orders/continue")
    public String continueOrdering(RedirectAttributes redirectAttributes, @RequestParam("orderId") String orderId) {

        redirectAttributes.addFlashAttribute("orderId", orderId);
        return "redirect:/files";
    }
}
