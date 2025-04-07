package com.marmouset.product.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.marmouset.price.entity.Price;
import org.junit.jupiter.api.Test;

/**
 * Tests product impl.
 */
public class ProductImplTest {

  @Test
  void shouldThrowIfCategoryIsNull() {
    assertThrows(
        NullPointerException.class,
        () -> new ProductImpl(null, new Price(), false));
  }

  @Test
  void shouldThrowIfPriceIsNull() {
    assertThrows(
        NullPointerException.class,
        () -> new ProductImpl(Category.ANY, null, false));
  }
}
