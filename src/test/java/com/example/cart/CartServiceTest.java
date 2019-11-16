package com.example.cart;

import com.example.cart.exception.CartException;
import com.example.cart.pojos.Cart;
import com.example.cart.pojos.CartRequest;
import com.example.cart.pojos.Receipt;
import com.example.cart.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class CartServiceTest {

  @Autowired CartService service;

  @Test
  public void testAddItemsToCartIfClearCartIsTrue() {
    CartRequest cartRequest = new CartRequest(3, 4, 1, true);
    Cart cart = service.addItemsToCart(cartRequest);
    assertThat(cart.getNumberOfBooks()).isEqualTo(3);
    assertThat(cart.getNumberOfDvds()).isEqualTo(4);
    assertThat(cart.getNumberOfVouchers()).isEqualTo(1);
    Cart cart2 = service.addItemsToCart(cartRequest);
    assertThat(cart2.getNumberOfBooks()).isEqualTo(3);
    assertThat(cart2.getNumberOfDvds()).isEqualTo(4);
    assertThat(cart2.getNumberOfVouchers()).isEqualTo(1);
  }

  @Test
  public void testAddItemsToCartIfClearCartIsNotTrue() {
    CartRequest cartRequest = new CartRequest(3, 4, 1, true);
    Cart cart = service.addItemsToCart(cartRequest);
    assertThat(cart.getNumberOfBooks()).isEqualTo(3);
    assertThat(cart.getNumberOfDvds()).isEqualTo(4);
    assertThat(cart.getNumberOfVouchers()).isEqualTo(1);
    CartRequest cartRequestFalse = new CartRequest(3, 4, 1, false);
    Cart cart2 = service.addItemsToCart(cartRequestFalse);
    assertThat(cart2.getNumberOfBooks()).isEqualTo(6);
    assertThat(cart2.getNumberOfDvds()).isEqualTo(8);
    assertThat(cart2.getNumberOfVouchers()).isEqualTo(2);
  }

  @Test
  public void testAddItemsToCartCreateCartIfItsAlreadyNull() {
    CartRequest cartRequest = new CartRequest(3, 4, 1, false);
    Cart cart = service.addItemsToCart(cartRequest);
    assertThat(cart.getNumberOfBooks()).isEqualTo(3);
    assertThat(cart.getNumberOfDvds()).isEqualTo(4);
    assertThat(cart.getNumberOfVouchers()).isEqualTo(1);
    CartRequest cartRequestFalse = new CartRequest(3, 4, 1, false);
    Cart cart2 = service.addItemsToCart(cartRequestFalse);
    assertThat(cart2.getNumberOfBooks()).isEqualTo(6);
    assertThat(cart2.getNumberOfDvds()).isEqualTo(8);
    assertThat(cart2.getNumberOfVouchers()).isEqualTo(2);
  }

  @Test
  public void testPayAndPrintingReceiptOnFridayZeroBooks() {
    CartRequest cartRequest = new CartRequest(0, 3, 1, true);
    service.addItemsToCart(cartRequest);
    Receipt receipt = service.payAndPrintReceipt("Friday");
    assertThat(receipt.getNumberOfBooks()).isEqualTo(0);
    assertThat(receipt.getNumberOfDvds()).isEqualTo(3);
    assertThat(receipt.getNumberOfVouchers()).isEqualTo(1);
    assertThat(receipt.getTotalPrice()).isEqualTo(33.5);
    assertThat(receipt.getBookPriceWithOffer()).isEqualTo(0);
    assertThat(receipt.getDvdPriceWithOffer()).isEqualTo(13.5);
    assertThat(receipt.getVoucherPrice()).isEqualTo(20);
  }

  @Test
  public void testPayAndPrintingReceiptOnMondayZeroDvds() {
    CartRequest cartRequest = new CartRequest(4, 0, 1, true);
    service.addItemsToCart(cartRequest);
    Receipt receipt = service.payAndPrintReceipt("Monday");
    assertThat(receipt.getNumberOfBooks()).isEqualTo(4);
    assertThat(receipt.getNumberOfDvds()).isEqualTo(0);
    assertThat(receipt.getNumberOfVouchers()).isEqualTo(1);
    assertThat(receipt.getTotalPrice()).isEqualTo(38);
    assertThat(receipt.getBookPriceWithOffer()).isEqualTo(18);
    assertThat(receipt.getDvdPriceWithOffer()).isEqualTo(0);
    assertThat(receipt.getVoucherPrice()).isEqualTo(20);
  }

  @Test
  public void testPayAndPrintingReceiptOnMondayZeroVouchers() {
    CartRequest cartRequest = new CartRequest(4, 3, 0, true);
    service.addItemsToCart(cartRequest);
    Receipt receipt = service.payAndPrintReceipt("Monday");
    assertThat(receipt.getNumberOfBooks()).isEqualTo(4);
    assertThat(receipt.getNumberOfDvds()).isEqualTo(3);
    assertThat(receipt.getNumberOfVouchers()).isEqualTo(0);
    assertThat(receipt.getTotalPrice()).isEqualTo(33);
    assertThat(receipt.getBookPriceWithOffer()).isEqualTo(18);
    assertThat(receipt.getDvdPriceWithOffer()).isEqualTo(15);
    assertThat(receipt.getVoucherPrice()).isEqualTo(0);
  }

  @Test
  public void throwExceptionWhenCartIsNull() {
    Throwable thrown = catchThrowable(() -> service.payAndPrintReceipt("Monday"));
    assertThat(thrown)
        .isInstanceOf(CartException.class)
        .hasMessage("You must add items to cart before paying and printing receipt");
  }
}
