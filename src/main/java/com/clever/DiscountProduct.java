package com.clever;

public class DiscountProduct extends Product{
    private int discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public DiscountProduct(int discount, String name, double price) {
        super(name, price);
        this.discount = discount;
    }

    @Override
    public double countCost(){
        if (quantity > 5){
            return quantity * price * ((100 - discount) / 100.);
        } else {
            return super.countCost();
        }

    }
}
