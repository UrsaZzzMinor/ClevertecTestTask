package com.clever;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class CashReceipt {
    private static Logger rootLogger = LogManager.getRootLogger();
    private double totalPrice;
    private double cardDiscountPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void countTotalPrice(Customer customer){
        if (customer.getCard().getDiscount() == 0){
            countTotalPrice(customer.getCart().getProductsInCart());
        } else {
            countTotalPrice(customer.getCart().getProductsInCart(), customer.getCard());
        }
    }

    public double countTotalPrice(ArrayList<Product> products) {
        totalPrice = 0;
        for (Product p: products) {
            totalPrice += p.countCost();
        }
        return totalPrice;
    }

    public double countTotalPrice(ArrayList<Product> products, Card card) {
        totalPrice = countTotalPrice(products);
        cardDiscountPrice = totalPrice * card.getDiscount()/100.;
        return totalPrice - cardDiscountPrice;
    }
    //StringBuilder
    public String cashReceiptDisplay(ArrayList<Product> products, Card card){
        String format = "%-5s %-20s %-10s %-10s %-10s %n";
        StringBuilder check = new StringBuilder();
        check.append(String.format(format, "Qty", "ProductName", "Price", "Cost", "Discount"));//"Qty " + "ProductName " + "Price " + "Cost " + "Discount" + "\n";
        check.append("-".repeat(60)).append("\n");
        for (Product p: products){
            double discount = 0;
            if (p instanceof DiscountProduct && p.quantity > 5){
                discount = p.quantity * p.price * (((DiscountProduct) p).getDiscount()/100.);
            }
            check.append(String.format(format, p.quantity, p.name, p.price, p.countCost(), discount == 0 ? "-" : discount));//p.quantity + " " + p.name + " " + p.price + " " + p.countCost();
        }
        check.append("-".repeat(60)).append("\n");
        check.append("-".repeat(60)).append("\n");
        format = "%-25s %30s %n";
        check.append(String.format(format, "ProductsCost", totalPrice));
        check.append(String.format(format, "CardDiscount " + card.getDiscount() + "%", cardDiscountPrice));
        check.append(String.format(format, "total".toUpperCase(), card.getDiscount() == 0 ? countTotalPrice(products) : countTotalPrice(products, card)));
        return check.toString();
    }

    public void writeToFile(String cashReceiptDisplay){
        String file = "src/main/resources/cashReceipt.txt";
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(cashReceiptDisplay);
        } catch (IOException e) {
            rootLogger.error("Something is wrong with a file : " + e.getMessage());
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException | NullPointerException e) {
                rootLogger.error("Unable to close BufferedWriter : " + e.getMessage());
            }
        }
    }
}
