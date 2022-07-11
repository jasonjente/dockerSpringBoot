package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.dao.CustomerRepository;
import project.persistence.model.Customer;
import project.exception.DataServiceException;
import project.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    CustomerRepository customerRepository;
    private String baseErrorMessage = "{} , {} . Is user-error: {}";

    @Override
    public Customer createCustomer(Customer customer) throws DataServiceException {
        logger.info("createCustomer - Enter:");
        validateCustomer(customer);
        customerRepository.save(customer);
        logger.info("createCustomer - Leave:");
        return customer;
    }

    @Override
    public Customer getCustomerById(Long customerId) throws DataServiceException {
        Optional<Customer> ret;
        ret = customerRepository.findById(customerId);
        if(ret.isPresent()) {
            return ret.get();
        }else {
            String errorMessage = "No customer was found for id :" + customerId +".";
            String causeOfError = "Unknown customer id.";
            boolean isUserError = true;
            logger.error(this.baseErrorMessage, errorMessage , causeOfError , isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer newCustomer) throws DataServiceException {
        logger.info("createCustomer - Enter:");
        validateCustomer(newCustomer);
        newCustomer.setId(customerId);
        customerRepository.save(newCustomer);
        logger.info("createCustomer - Leave:");
        return newCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer) throws DataServiceException {
        logger.info("createCustomer() - Enter:");
        validateCustomer(customer);
        customerRepository.save(customer);
        logger.info("createCustomer() - Leave:");
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    private void validateCustomer(Customer customer) throws DataServiceException {
        if(customer.getAddress() == null || customer.getAddress().isEmpty()){
            String errorMessage = "Customer address cannot be empty";
            String causeOfError = "Empty address.";
            boolean isUserError = true;
            logger.error(this.baseErrorMessage, errorMessage , causeOfError , isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
        if(customer.getEmail() == null || customer.getEmail().isEmpty()){
            String errorMessage = "Customer email cannot be empty";
            String causeOfError = "Empty email.";
            boolean isUserError = true;
            logger.error(this.baseErrorMessage, errorMessage , causeOfError , isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(customer.getCustomerFirstName() == null || customer.getCustomerFirstName().isEmpty()
                ||customer.getCustomerLastName() == null || customer.getCustomerLastName().isEmpty()){
            String errorMessage = "Customer first and last name cannot be empty";
            String causeOfError = "Empty first or last name.";
            boolean isUserError = true;
            logger.error(this.baseErrorMessage, errorMessage , causeOfError , isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

    }
}
