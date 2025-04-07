package com.marmouset.price.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * This class tests if the price implementation is correct.
 */
public class PriceTest {

  @Test
  void shouldThrowExceptionIfDecimalNegative() {
    assertThrows(IllegalArgumentException.class, () -> new Price(-1, 40));
  }

  @Test
  void shouldThrowExceptionIfCentsNegative() {
    assertThrows(IllegalArgumentException.class, () -> new Price(1, -40));
  }

  @Test
  void shouldThrowExceptionIfCentsMoreThan99() {
    assertThrows(IllegalArgumentException.class, () -> new Price(1, 100));
  }

  @Test
  void shouldAddPrice() {
    var price1 = new Price(6, 40);
    var price2 = new Price(3, 70);
    var expected = new Price(10, 10);
    assertEquals(expected, price1.add(price2));
  }

  @Test
  void shouldApplyRate() {
    var price = new Price(10, 50);
    var expected = new Price(15, 75);
    assertEquals(expected, price.applyRate(50));
  }

  @Test
  void shouldApplyRateOnRoundedValues() {
    var price = new Price(14, 99);
    var expected = new Price(16, 49);
    assertEquals(expected, price.applyRate(10));
  }

  @Test
  void shouldSubstract() {
    var price1 = new Price(4, 30);
    var price2 = new Price(3, 50);
    var expected = new Price(0, 80);
    assertEquals(expected, price1.substract(price2));
  }

  @Test
  void shouldThrowWhenSubstractingResultWillBeNegative() {
    var price1 = new Price(4, 30);
    var price2 = new Price(3, 50);
    assertThrows(ArithmeticException.class, () -> price2.substract(price1));
  }

}
