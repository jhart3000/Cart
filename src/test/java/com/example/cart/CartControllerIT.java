package com.example.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerIT {

  @Autowired private MockMvc mockMvc;

  @Test
  public void addItemsToCartGives200Response() throws Exception {
    mockMvc.perform(get("/add/book/1/dvd/1/voucher/4/true")).andExpect(status().isOk());
  }

  @Test
  public void payAndPrintReceiptGives200Response() throws Exception {
    mockMvc.perform(get("/add/book/1/dvd/3/voucher/4/true")).andExpect(status().isOk());
    mockMvc.perform(get("/payAndPrintReceipt/wednesday")).andExpect(status().isOk());
  }
}
