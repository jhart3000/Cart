package com.example.cart.service;

import com.example.cart.client.PaymentClient;
import com.example.cart.exception.CartException;
import com.example.cart.pojos.Book;
import com.example.cart.pojos.Cart;
import com.example.cart.pojos.CartRequest;
import com.example.cart.pojos.DVD;
import com.example.cart.pojos.Receipt;
import com.example.cart.pojos.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired private PaymentClient client;

  private Cart cart;

  public Cart addItemsToCart(CartRequest cartRequest) {
    if (cartRequest.isClearCart() || cart == null) {
      cart = null;
      cart =
          new Cart(
              cartRequest.getNumberOfBooks(),
              cartRequest.getNumberOfDvds(),
              cartRequest.getNumberOfVouchers());
    } else {
      cart.setNumberOfBooks(cart.getNumberOfBooks() + cartRequest.getNumberOfBooks());
      cart.setNumberOfDvds(cart.getNumberOfDvds() + cartRequest.getNumberOfDvds());
      cart.setNumberOfVouchers(cart.getNumberOfVouchers() + cartRequest.getNumberOfVouchers());
    }
    return cart;
  }

  public Receipt payAndPrintReceipt(String day) throws CartException {

    if (cart == null) {
      throw new CartException("You must add items to cart before paying and printing receipt");
    }

    int ZERO = 0;
    double dvdTotal = ZERO;
    double voucherTotal = ZERO;
    double bookTotal = ZERO;

    if (cart.getNumberOfDvds() != ZERO) {
      dvdTotal = calculateDvdTotalWithOffer(day);
    }
    if (cart.getNumberOfVouchers() != ZERO) {
      voucherTotal = calculateVoucherTotal();
    }
    if (cart.getNumberOfBooks() != ZERO) {
      bookTotal = calculateBookTotal();
    }

    double totalPurchasePrice = dvdTotal + voucherTotal + bookTotal;

    client.callPaymentClient(totalPurchasePrice);

    Receipt receipt =
        new Receipt(
            cart.getNumberOfBooks(),
            cart.getNumberOfDvds(),
            cart.getNumberOfVouchers(),
            bookTotal,
            dvdTotal,
            voucherTotal,
            totalPurchasePrice);

    cart = null;
    return receipt;
  }

  private double calculateDvdTotalWithOffer(String day) {
    DVD dvd = new DVD();
    double dvdsTotal = cart.getNumberOfDvds() * dvd.getPrice();

    if (day.equalsIgnoreCase("Friday")) {
      return dvdsTotal * 0.9;
    } else {
      return dvdsTotal;
    }
  }

  private double calculateVoucherTotal() {
    Voucher voucher = new Voucher();
    return cart.getNumberOfVouchers() * voucher.getPrice();
  }

  private double calculateBookTotal() {
    Book book = new Book();
    int totalNumberOfBooks = cart.getNumberOfBooks();
    double bookPrice = book.getPrice();

    double remainderOfBooksAfterDividingByTwo = totalNumberOfBooks % 2;
    double numberOfBooksHalfPrice = totalNumberOfBooks - remainderOfBooksAfterDividingByTwo;
    return numberOfBooksHalfPrice * bookPrice * 0.5 + remainderOfBooksAfterDividingByTwo * bookPrice;
  }
}
