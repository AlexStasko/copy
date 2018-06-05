package by.yakunina.copy.service;

import by.yakunina.copy.model.Customer;
import by.yakunina.copy.model.support.EntityId;

public class CustomerService {

    public EntityId createCustomer(Customer customer) {
        return new EntityId("1");
    }

    public Customer updateCustomer(Customer customer) {
        return customer;
    }

    public void deleteCustomer(EntityId id) {

    }

    public Customer findCustomer(EntityId id) {
        return new Customer.CustomerBuilder()
                .build();
    }
}
