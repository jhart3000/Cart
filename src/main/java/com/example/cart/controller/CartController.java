package com.example.cart.controller;

import com.example.cart.pojos.Cart;
import com.example.cart.pojos.CartRequest;
import com.example.cart.pojos.Receipt;
import com.example.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

  @Autowired CartService service;

  @GetMapping("/add/book/{numberOfBooks}/dvd/{numberOfDvds}/voucher/{numberOfVouchers}/{clearCart}")
  public Cart addItemsToCart(
      @PathVariable("numberOfBooks") int numberOfBooks,
      @PathVariable("numberOfDvds") int numberOfDvds,
      @PathVariable("numberOfVouchers") int numberOfVouchers,
      @PathVariable("clearCart") boolean clearCart) {

    CartRequest cartRequest =
        new CartRequest(numberOfBooks, numberOfDvds, numberOfVouchers, clearCart);
    return service.addItemsToCart(cartRequest);
  }

  @GetMapping("/payAndPrintReceipt/{day}")
  public Receipt payAndPrintReceipt(@PathVariable("day") String day) {
    return service.payAndPrintReceipt(day);
  }
}
