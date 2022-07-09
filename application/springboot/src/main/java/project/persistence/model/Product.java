package project.persistence.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="Products")
@Table(name="products")
public class Product implements Serializable {
    final static Logger logger = LoggerFactory.getLogger(Product.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "state")
    private String state;

    @Column(name = "title", nullable = false, length = 150)
    private String title;
    @Column(name = "description", length = 2000)
    private String description;

    public Product(){
        logger.info(" A new product was created. ");
    }

    public Long getProductId() {
        return productId;
    }
    public String getState() {
        return state;
    }

    public void setKey(long key){
        this.productId = key;
    }

    public void setState(String state) {
        logger.info("Changed state to: "+ state );
        this.state = state;
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

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String toString(){
        return "Product ID: " +getProductId() + ", state: " + getState() +
                ", title: " + getTitle() + ", description: " + getDescription();
    }
}
