package project.persistence.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "orders")
@Table(name = "orders")
public class Orders implements Serializable {
    final static Logger logger = LoggerFactory.getLogger(Orders.class);
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @NotNull
    @Size(min=2, message="Title cannot be empty, it needs to contain at least one character.")
    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Product> orderProducts = new HashSet<>();

    @Column(name="order_state")
    private String orderState;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonIgnore
    public Set<Product> getOrderTasks() {
        return orderProducts;
    }

    public void setOrderTasks(Set<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String order_state) {
        this.orderState = order_state;
    }
    public String toString(){
            return "Order id : " + this.getOrderId().toString() + ",\n title: " + this.getTitle() + ",\n description: " + this.getDescription() + "\n number of tasks remaining :" + this.getOrderTasks().size();
    }
}
