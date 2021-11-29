package com.clever;

public class Product {
    protected String name;
    protected int quantity;
    protected double price;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double countCost(){
        return price * quantity;
    }

}
