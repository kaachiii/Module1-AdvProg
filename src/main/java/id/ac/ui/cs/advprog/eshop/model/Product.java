package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    public Product() {
        // Generate random UUID for product ID
        this.productId = UUID.randomUUID().toString();
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = Math.max(0, productQuantity);
    }
}
