package com.example.cart.pojos;

public class Receipt {

  private int numberOfBooks;
  private int numberOfDvds;
  private int numberOfVouchers;
  private double bookPriceWithOffer;
  private double dvdPriceWithOffer;
  private double voucherPrice;
  private double totalPrice;

  public Receipt(
      int numberOfBooks,
      int numberOfDvds,
      int numberOfVouchers,
      double bookPriceWithOffer,
      double dvdPriceWithOffer,
      double voucherPrice,
      double totalPrice) {
    this.numberOfBooks = numberOfBooks;
    this.numberOfDvds = numberOfDvds;
    this.numberOfVouchers = numberOfVouchers;
    this.bookPriceWithOffer = bookPriceWithOffer;
    this.dvdPriceWithOffer = dvdPriceWithOffer;
    this.voucherPrice = voucherPrice;
    this.totalPrice = totalPrice;
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

  public double getBookPriceWithOffer() {
    return bookPriceWithOffer;
  }

  public double getDvdPriceWithOffer() {
    return dvdPriceWithOffer;
  }

  public double getVoucherPrice() {
    return voucherPrice;
  }

  public double getTotalPrice() {
    return totalPrice;
  }
}
