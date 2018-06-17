package by.yakunina.copy.controller;

import by.yakunina.copy.model.Customer;
import by.yakunina.copy.model.Employee;
import by.yakunina.copy.model.Order;
import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.auth.CopyUser;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.service.AccountService;
import by.yakunina.copy.service.CustomerService;
import by.yakunina.copy.service.OrderService;
import by.yakunina.copy.support.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private static List<Order> orders = new ArrayList<>();

    static {
        Customer customer = new Customer.CustomerBuilder()
                .withId(new EntityId(KeyGenerator.getUUID()))
                .withName("Alex")
                .withLastName("Stasko")
                .withAddress("N/A")
                .withPhoneNumber("11111")
                .build();
        Customer customer1 = new Customer.CustomerBuilder()
                .withId(new EntityId(KeyGenerator.getUUID()))
                .withName("Sasha")
                .withLastName("Stasko")
                .withAddress("N/A")
                .withPhoneNumber("11111")
                .build();
        Employee employee = new Employee.EmployeeBuilder()
                .withId(new EntityId(KeyGenerator.getUUID()))
                .withName("Worker")
                .withLastName("Hard")
                .withTitle("Agent")
                .build();

        Order order = new Order();
        order.setId(new EntityId(KeyGenerator.getUUID()));
        order.setCustomer(customer);
        order.setEmployee(employee);

        orders.add(order);

        order = new Order();
        order.setId(new EntityId(KeyGenerator.getUUID()));
        order.setCustomer(customer1);
        order.setEmployee(employee);

        orders.add(order);
    }

    @Resource
    private AccountService accountService;
    @Resource
    private CustomerService customerService;
    @Resource
    private FileController fileController;
    @Resource
    private OrderService orderService;

    @GetMapping("/orders")
    public String getOrders(Model model) {

        List<Order> orders = orderService.findAll();
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
        EntityId id = orderService.createOrder(order);

        model.addAttribute("orderId", id.getId());
        return fileController.listUploadedFiles(model);
    }
}
