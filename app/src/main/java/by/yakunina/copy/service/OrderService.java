package by.yakunina.copy.service;

import by.yakunina.copy.model.Order;
import by.yakunina.copy.model.Service;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.support.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private static List<Order> orders = new ArrayList();

    public EntityId createOrder(Order newOrder) {
        LOGGER.info("Attempt to save order [{}]", newOrder);
        newOrder.setId(new EntityId(KeyGenerator.getUUID()));
        orders.add(newOrder);
        return newOrder.getId();
    }

    public List<Order> findAll() {
        return orders;
    }

    public Order findOrder(String id) {
        for (Order order : orders) {
            if (order.getId().getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public void addService(String id, Service service) {
        for (Order order : orders) {
            if (order.getId().getId().equals(id)) {
                order.getServices().add(service);
            }
        }
    }

    public void update(Order newOrder) {
        for (Order order : orders) {
            if (order.getId().getId().equals(newOrder.getId().getId())) {
                orders.add(newOrder);
                orders.remove(order);
            }
        }
    }

    public List<Order> findOrdersByUserId(String id) {
        List<Order> orderList = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().getId().getId().equals(id)) {
                orderList.add(order);
            }
        }
        return orderList;
    }
}
