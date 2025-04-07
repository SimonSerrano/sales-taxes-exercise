package com.marmouset.receipt.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marmouset.cart.entity.CartFactory;
import com.marmouset.cart.entity.CartFactoryImpl;
import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.ProductFactory;
import com.marmouset.product.entity.ProductFactoryImpl;
import com.marmouset.product.entity.ProductOptions;
import com.marmouset.product.entity.taxed.TaxedProductFactory;
import com.marmouset.product.entity.taxed.TaxedProductFactoryImpl;
import com.marmouset.receipt.entity.ReceiptFactory;
import com.marmouset.receipt.entity.ReceiptFactoryImpl;
import com.marmouset.tax.entity.TaxPlan;
import com.marmouset.tax.entity.TaxPlanImpl;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the issue receipt use case.
 */
public class IssueReceiptUseCaseImplTest {

  private IssueReceiptUseCase useCase;

  private CartFactory cartFactory;
  private ReceiptFactory receiptFactory;
  private ProductFactory productFactory;
  private TaxedProductFactory taxedProductFactory;
  private TaxPlan taxPlan;

  @BeforeEach
  void setUp() {
    productFactory = new ProductFactoryImpl();
    cartFactory = new CartFactoryImpl();
    receiptFactory = new ReceiptFactoryImpl();
    taxedProductFactory = new TaxedProductFactoryImpl();
    taxPlan = new TaxPlanImpl();
    useCase = new IssueReceiptUseCaseImpl(
        taxPlan, receiptFactory, taxedProductFactory);
  }

  @Test
  void shouldReturnReceiptOnSimpleProducts() {
    var cart = cartFactory.create();
    var products = Arrays.asList(
        productFactory.create(new ProductOptions().withPrice(10, 95)),
        productFactory.create(new ProductOptions().withPrice(20, 99)),
        productFactory.create(new ProductOptions().withPrice(15, 45)));
    products.forEach(cart::add);
    var taxedProducts = Arrays.asList(
        taxedProductFactory.create(
            products.get(0), new Price()
                .add(products.get(0).getPrice()).add(new Price(1, 10))),
        taxedProductFactory.create(
            products.get(1), new Price()
                .add(products.get(1).getPrice()).add(new Price(2, 10))),
        taxedProductFactory.create(
            products.get(2), new Price()
                .add(products.get(2).getPrice()).add(new Price(1, 55))));

    var expected = receiptFactory.create(taxedProducts);
    var result = useCase.issueReceipt(cart);
    assertEquals(expected, result);
    assertEquals(new Price(4, 75), result.calculateTaxedValue());
    assertEquals(new Price(52, 14), result.calculateTotal());
  }

  @Test
  void shouldReturnReceiptOnExemptedProducts() {

  }

  @Test
  void shouldReturnReceiptOnImportedProducts() {

  }

  @Test
  void shouldReturnReceiptOnMixedProducts() {

  }

  /**
   * Theres no specification on empty carts, I chose to return an exception
   * because empty carts should not happen in the domain.
   */
  @Test
  void shouldThrowOnEmptyCart() {
    var cart = cartFactory.create();
    var expected = receiptFactory.create(Collections.emptyList());
    assertEquals(expected, useCase.issueReceipt(cart));
  }

}
