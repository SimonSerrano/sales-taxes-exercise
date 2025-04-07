package com.marmouset.product.entity.taxed;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.ProductFactoryImpl;
import com.marmouset.product.entity.ProductOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the taxed product factory.
 */
public class TaxedProductFactoryImplTest {

  private TaxedProductFactory factory;

  @BeforeEach
  void setUp() {
    factory = new TaxedProductFactoryImpl();
  }

  @Test
  void shouldThrowExceptionIfProductIsNull() {
    assertThrows(
        NullPointerException.class, () -> factory.create(null, new Price()));
  }

  @Test
  void shouldThrowExceptionIfPriceIsNull() {
    assertThrows(
        NullPointerException.class,
        () -> factory.create(
            new ProductFactoryImpl().create(new ProductOptions()), null));
  }

}
