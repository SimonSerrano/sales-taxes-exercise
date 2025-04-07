package com.marmouset.receipt.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.ProductFactory;
import com.marmouset.product.entity.ProductFactoryImpl;
import com.marmouset.product.entity.ProductOptions;
import com.marmouset.product.taxed.entity.TaxedProductFactory;
import com.marmouset.product.taxed.entity.TaxedProductFactoryImpl;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the receipt implementation.
 */
public class ReceiptImplTest {
  private Receipt receipt;
  private TaxedProductFactory taxedProductFactory;
  private ProductFactory productFactory;

  @BeforeEach
  void setUp() {
    taxedProductFactory = new TaxedProductFactoryImpl();
    productFactory = new ProductFactoryImpl();
    receipt = new ReceiptImpl(
        Arrays.asList(
            taxedProductFactory.create(
                productFactory.create(
                    new ProductOptions()
                        .withPrice(10, 0)),
                new Price(10, 80)),
            taxedProductFactory.create(
                productFactory.create(
                    new ProductOptions()
                        .withPrice(20, 0)),
                new Price(21, 70))));
  }

  @Test
  void shouldReturnTheTotalPrice() {
    assertEquals(new Price(32, 50), receipt.calculateTotal());
  }

  @Test
  void shouldReturnTheTaxedPrice() {
    assertEquals(new Price(2, 50), receipt.calculateTaxedValue());
  }

}
