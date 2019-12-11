package com.example.userregistration.Model;

public class CartItem {
    private String pid, productName, productPrice,quantity;

    public CartItem() {
    }

    public CartItem(String pid, String productName, String productPrice, String quantity) {
        this.pid = pid;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
