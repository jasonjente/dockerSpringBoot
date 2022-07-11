package project.persistence.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="Products")
@Table(name="products")
public class Product implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Product.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "state")
    private String state;

    @Column(name = "title", nullable = false, length = 150)
    private String productName;
    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "PRODUCT_CODE", nullable = false, length = 100)
    private String productCode;

    @Column(name = "price")
    private Double price;

    public Product() {
    }

    public Product(Long productId, String state, String productName, String description, String productCode, Double price) {
        this.productId = productId;
        this.state = state;
        this.productName = productName;
        this.description = description;
        this.productCode = productCode;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        logger.info("Changed state to: {}", state );
        this.state = state;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String toString(){
        return "Product ID: " +getProductId() + ", state: " + getState() +
                ", title: " + getProductName() + ", description: " + getDescription();
    }
}
