package com.clever;

public class Customer {
    private ShoppingCart cart = new ShoppingCart();
    private Card card = new Card();

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void checkCard(Card[] cards){
        for (Card c: cards){
            if (c.getNumber() == card.getNumber()){
                card = c;
            }
        }
    }
}
