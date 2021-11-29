package com.clever;

public class Card {
    private int number;
    private int discount;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Card() {
    }

    public Card(int number, int discount) {
        this.number = number;
        this.discount = discount;
    }

}
