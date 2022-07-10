package project.service;

import project.persistence.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    Customer updateCustomer(Long customerId, Customer newCustomer);

    Customer updateCustomer(Customer customer);
    List<Customer> getAllCustomers();

}
