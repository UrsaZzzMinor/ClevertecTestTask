package com.clever;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShoppingCart {
    private static Logger rootLogger = LogManager.getRootLogger();
    private ArrayList<Product> productsInCart = new ArrayList<>();

    public ArrayList<Product> getProductsInCart() {
        return productsInCart;
    }

    public void fillCart(ArrayList<Product> products, Card card) throws IncorrectFileNameException {
        fillCart(products, card , "src/main/resources/data.txt" );
    }

    public void fillCart(ArrayList<Product> products, Card card, String file) throws IncorrectFileNameException {
        ArrayList<String> data = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while (br.ready()){
                data.add(br.readLine());
            }
        }catch (FileNotFoundException e) {
            //rootLogger.error("Unable to find a File : " + e.getMessage());
            throw new IncorrectFileNameException("Incorrect filename : " + e.getMessage(), e);
        }  catch (IOException e){
            rootLogger.error("Something is wrong with a File" + e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException | NullPointerException e) {
                rootLogger.error("Unable to close BufferedReader : " + e.getMessage());
            }
        }


        for (String str: data){

            String[] field = str.split("\\s{0,200}-" + "\\s{0,200}");

            try{
                if (!field[0].equalsIgnoreCase("card")) {
                    productsInCart.add(products.get(Integer.parseInt(field[0])));
                    productsInCart.get(data.indexOf(str)).setQuantity(Integer.parseInt(field[1]));
                } else {
                    card.setNumber(Integer.parseInt(field[1]));
                }
            } catch (NumberFormatException e){
                //rootLogger.error("Invalid data : " + e.getMessage());
                throw new IncorrectDataException("Invalid data : " + e.getMessage(), e);
            } catch (IndexOutOfBoundsException e){
                //rootLogger.error("Missing data : " + e.getMessage());
                throw new IncorrectDataException("Missing data : " + e.getMessage(), e);
            }

        }
    }
}
