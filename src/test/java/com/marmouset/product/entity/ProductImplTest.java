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
        () -> new ProductImpl(1, "toto", null, new Price(), false));
  }

  @Test
  void shouldThrowIfPriceIsNull() {
    assertThrows(
        NullPointerException.class,
        () -> new ProductImpl(1, "toto", Category.ANY, null, false));
  }

  @Test
  void shouldThrowIfQuantityIsZeroOrNegative() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ProductImpl(0, "toto", Category.ANY, null, false));
  }

  @Test
  void shouldThrowIfDescriptionIsNull() {
    assertThrows(
        NullPointerException.class,
        () -> new ProductImpl(1, null, Category.ANY, null, false));
  }

  @Test
  void shouldThrowIfDescriptionIsEmpty() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ProductImpl(1, "", Category.ANY, null, false));
  }
}
