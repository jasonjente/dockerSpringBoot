package project.persistence.dto;

import project.persistence.model.Product;

public class ProductDTO {
    private Long productId;
    private String state;
    private String productName;
    private String description;
    private String productCode;
    private Double price;

    public ProductDTO(Long productId, String state, String productName, String description, String productCode, Double price) {
        this.productId = productId;
        this.state = state;
        this.productName = productName;
        this.description = description;
        this.productCode = productCode;
        this.price = price;
    }

    public ProductDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product productFromDTO(){
        return new Product(this.getProductId(), this.getState(), this.getProductName(), this.getDescription(), this.getProductCode(), this.getPrice());
    }
}
