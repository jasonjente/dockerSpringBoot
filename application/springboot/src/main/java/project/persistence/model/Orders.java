package project.persistence.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
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

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "products", nullable = false)
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
    public Set<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String order_state) {
        this.orderState = order_state;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", orderProducts=" + orderProducts +
                ", orderState='" + orderState + '\'' +
                '}';
    }
}
