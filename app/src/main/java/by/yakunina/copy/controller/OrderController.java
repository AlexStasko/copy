package by.yakunina.copy.controller;

import by.yakunina.copy.model.Customer;
import by.yakunina.copy.model.Employee;
import by.yakunina.copy.model.Order;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.support.KeyGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

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

    @GetMapping("/orders")
    public String getOrders(Model model) {


        model.addAttribute("orders", orders);

        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String getOrder(@PathVariable("id") String id, Model model) {
        Order requestedOrder = null;
        for (Order order : orders) {
            if (order.getId().getId().equals(id)) {
                requestedOrder = order;
            }
        }
        if (requestedOrder != null) {
            model.addAttribute("order", requestedOrder);
        } else {
            model.addAttribute("message", "No order with id [" + id + "]");
        }
        return "order";
    }
}
