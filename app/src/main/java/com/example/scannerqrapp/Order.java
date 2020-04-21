package com.example.scannerqrapp;

import java.util.ArrayList;

public class Order {

    private ArrayList<Product> products;
    private String orderName;

    public Order(String orderName) {
        this.orderName = orderName;
        products = new ArrayList<>();

    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}
