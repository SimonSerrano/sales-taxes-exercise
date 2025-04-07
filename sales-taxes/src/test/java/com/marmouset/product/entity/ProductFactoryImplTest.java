package com.marmouset.product.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the product factory.
 */
public class ProductFactoryImplTest {

  private ProductFactory factory;

  @BeforeEach
  void setUp() {
    factory = new ProductFactoryImpl();
  }

  @Test
  void shouldThrowErrorIfOptionsAreNull() {
    assertThrows(NullPointerException.class, () -> factory.create(null));
  }

}
