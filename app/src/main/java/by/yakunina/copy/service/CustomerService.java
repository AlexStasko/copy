package by.yakunina.copy.service;

import by.yakunina.copy.model.Customer;

public class CustomerService {

    public int createCustomer(Customer customer) {
        return -1;
    }

    public Customer updateCustomer(Customer customer) {
        return customer;
    }

    public void deleteCustomer(int id) {

    }

    public Customer findCustomer(int id) {
        return new Customer.CustomerBuilder()
                .build();
    }
}
