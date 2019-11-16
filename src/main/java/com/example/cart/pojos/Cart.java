package com.example.cart.pojos;

public class Cart {

  private int numberOfBooks;
  private int numberOfDvds;
  private int numberOfVouchers;

  public Cart(int numberOfBooks, int numberOfDvds, int numberOfVouchers) {
    this.numberOfBooks = numberOfBooks;
    this.numberOfDvds = numberOfDvds;
    this.numberOfVouchers = numberOfVouchers;
  }

  public int getNumberOfBooks() {
    return numberOfBooks;
  }

  public void setNumberOfBooks(int numberOfBooks) {
    this.numberOfBooks = numberOfBooks;
  }

  public int getNumberOfDvds() {
    return numberOfDvds;
  }

  public void setNumberOfDvds(int numberOfDvds) {
    this.numberOfDvds = numberOfDvds;
  }

  public int getNumberOfVouchers() {
    return numberOfVouchers;
  }

  public void setNumberOfVouchers(int numberOfVouchers) {
    this.numberOfVouchers = numberOfVouchers;
  }
}
