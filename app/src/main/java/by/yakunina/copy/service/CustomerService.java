package by.yakunina.copy.service;

import by.yakunina.copy.model.Customer;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.support.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerService {


    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private List<Customer> customers = new ArrayList<>();

    public EntityId createCustomer(Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setId(new EntityId(KeyGenerator.getUUID()));
        newCustomer.setName(customer.getName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setPhoneNumber(customer.getPhoneNumber());
        customers.add(newCustomer);
        return newCustomer.getId();
    }

    public Customer updateCustomer(Customer customer) {
        return customer;
    }

    public void deleteCustomer(EntityId id) {

    }

    public Customer findCustomer(EntityId id) {
        LOGGER.info("Customers: [{}]", customers);
        for(Customer customer : customers) {
            if (customer.getId().getId().equals(id.getId())) {
                return customer;
            }
        }
        return null;
    }
}
