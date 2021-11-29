package com.clever;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static Logger rootLogger = LogManager.getRootLogger();

    public static void main(String[] args){
        ArrayList<Product> products = new ArrayList<>(Arrays.asList(new Product("Milk", 4),
                new Product("Bread", 10),
                new Product("Salt", 2),
                new DiscountProduct(10, "BubbleGum", 5),
                new DiscountProduct(20, "Cake", 100)));

        Card[] cards = {new Card(1233, 20),
                new Card(1234, 10)};

        Customer customer = new Customer();
        try {
            customer.getCart().fillCart(products, customer.getCard());
        } catch (IncorrectFileNameException e) {
            rootLogger.error("Unable to find a File : " + e.getMessage());
        }catch (IncorrectDataException e){
            rootLogger.error(e.getMessage());
        }
        customer.checkCard(cards);
        CashReceipt cashReceipt = new CashReceipt();
        cashReceipt.countTotalPrice(customer);
        cashReceipt.writeToFile(cashReceipt.cashReceiptDisplay(customer.getCart().getProductsInCart(), customer.getCard()));
        System.out.println(cashReceipt.cashReceiptDisplay(customer.getCart().getProductsInCart(), customer.getCard()));
    }
}
