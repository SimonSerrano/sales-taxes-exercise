package com.marmouset.cart.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the cart implementation.
 */
public class CartImplTest {

  @Test
  void shouldThrowErrorIfAddedProductIsNull() {
    var cart = new CartFactoryImpl().create();
    assertThrows(NullPointerException.class, () -> cart.add(null));
  }

}
