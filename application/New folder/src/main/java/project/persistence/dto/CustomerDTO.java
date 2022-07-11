package project.persistence.dto;

import project.persistence.model.Customer;
import project.persistence.model.Orders;

import java.util.Set;

public class CustomerDTO {

    private Long id;
    private String customerFirstName;
    private String customerLastName;
    private String email;
    private String address;
    private Set<Orders> orders;

    public CustomerDTO(Long id, String customerFirstName, String customerLastName, String email, String address, Set<Orders> orders) {
        this.id = id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.email = email;
        this.address = address;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Customer getCustomerFromDTO() {
        //(Long id, String customerFirstName, String customerLastName, String email, String address, Set<Orders> orders)
        return new Customer(this.getId(),this.getCustomerFirstName(), this.getCustomerLastName(), this.getEmail(),
                this.getAddress(), this.getOrders());
    }
}
