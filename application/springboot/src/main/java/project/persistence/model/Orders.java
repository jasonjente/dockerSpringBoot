package project.persistence.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity(name = "orders")
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "RECIPIENT_FIRST_NAME", nullable = false, length = 50)
    private String recipientFirstName;

    @Column(name = "RECIPIENT_LAST_NAME", nullable = false, length = 50)
    private String recipientLastName;

    @Column(name = "RECIPIENT_ADDRESS", length = 2000)
    private String recipientAddress;

    @Column(name = "RECIPIENT_EMAIL", nullable = false, length = 120)
    private String recipientEmail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    @Column(name = "order_state")
    private String orderState;

    @Column(name = "TOTAL")
    private Double total;

    public Double getTotal() {
        return total;
    }

    public void calculateTotal() {
        Double currentTotal = 0D;
        for(OrderItem orderItem :orderItems){
            Integer quantity = orderItem.getQuantity();
            Double price = orderItem.getProduct().getPrice();
            currentTotal += price*quantity;
        }

        this.total = currentTotal;
    }

    public Orders(Long orderId, String recipientFirstName, String recipientLastName, String recipientAddress,
                  String recipientEmail, Set<OrderItem> orderItems, String orderState, Double total) {
        this.orderId = orderId;
        this.recipientFirstName = recipientFirstName;
        this.recipientLastName = recipientLastName;
        this.recipientAddress = recipientAddress;
        this.recipientEmail = recipientEmail;
        this.orderItems = orderItems;
        this.orderState = orderState;
        this.total = total;
    }

    public Orders() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getRecipientFirstName() {
        return recipientFirstName;
    }

    public void setRecipientFirstName(String recipientFirstName) {
        this.recipientFirstName = recipientFirstName;
    }

    public String getRecipientLastName() {
        return recipientLastName;
    }

    public void setRecipientLastName(String recipientLastName) {
        this.recipientLastName = recipientLastName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public void setTotal() {
        calculateTotal();
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        calculateTotal();
    }
}
