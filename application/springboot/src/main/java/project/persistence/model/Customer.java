package project.persistence.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "CUSTOMER_FIRST_NAME")
    private String customerFirstName;

    @Column (name = "CUSTOMER_LAST_NAME")
    private String customerLastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Orders> orders;

    public Customer() {

    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String username) {
        this.customerFirstName = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
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

    public Customer(Long id, String customerFirstName, String customerLastName, String email, String address, Set<Orders> orders) {
        this.id = id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.email = email;
        this.address = address;
        this.orders = orders;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + customerFirstName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void addOrderToCustomer(Orders order){
        if(orders == null){
            orders = new HashSet<>();
        }
        orders.add(order);
    }
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }
}
