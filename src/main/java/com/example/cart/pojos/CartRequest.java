package com.example.cart.pojos;

public class CartRequest {

  private int numberOfBooks;
  private int numberOfDvds;
  private int numberOfVouchers;
  private boolean clearCart;

  public CartRequest(int numberOfBooks, int numberOfDvds, int numberOfVouchers, boolean clearCart) {
    this.numberOfBooks = numberOfBooks;
    this.numberOfDvds = numberOfDvds;
    this.numberOfVouchers = numberOfVouchers;
    this.clearCart = clearCart;
  }

  public boolean isClearCart() {
    return clearCart;
  }

  public int getNumberOfBooks() {
    return numberOfBooks;
  }

  public int getNumberOfDvds() {
    return numberOfDvds;
  }

  public int getNumberOfVouchers() {
    return numberOfVouchers;
  }
}
