package project.service;

import project.exception.DataServiceException;
import project.persistence.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer) throws DataServiceException;

    Customer getCustomerById(Long customerId) throws DataServiceException;

    Customer updateCustomer(Long customerId, Customer newCustomer) throws DataServiceException;

    Customer updateCustomer(Customer customer) throws DataServiceException;
    List<Customer> getAllCustomers();

}
