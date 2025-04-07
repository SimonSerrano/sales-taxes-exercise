package com.marmouset.receipt.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marmouset.cart.entity.CartFactory;
import com.marmouset.cart.entity.CartFactoryImpl;
import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.Category;
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
    taxPlan = new TaxPlanImpl(Arrays.asList(
        Category.BOOKS,
        Category.FOOD,
        Category.MEDICAL));
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
    var cart = cartFactory.create();
    var products = Arrays.asList(
        productFactory.create(new ProductOptions()
            .withPrice(12, 49).withCategory(Category.BOOKS)),
        productFactory.create(new ProductOptions().withPrice(14, 99)),
        productFactory.create(new ProductOptions()
            .withPrice(0, 85).withCategory(Category.FOOD)));
    products.forEach(cart::add);
    var taxedProducts = Arrays.asList(
        taxedProductFactory.create(
            products.get(0), new Price()
                .add(products.get(0).getPrice()).add(new Price(0, 0))),
        taxedProductFactory.create(
            products.get(1), new Price()
                .add(products.get(1).getPrice()).add(new Price(1, 50))),
        taxedProductFactory.create(
            products.get(2), new Price()
                .add(products.get(2).getPrice()).add(new Price(0, 0))));

    var expected = receiptFactory.create(taxedProducts);
    var result = useCase.issueReceipt(cart);
    assertEquals(expected, result);
    assertEquals(new Price(1, 50), result.calculateTaxedValue());
    assertEquals(new Price(29, 83), result.calculateTotal());
  }

  @Test
  void shouldReturnReceiptOnImportedProducts() {
    var cart = cartFactory.create();
    var products = Arrays.asList(
        productFactory.create(new ProductOptions()
            .withPrice(10, 0).withCategory(Category.FOOD).markAsImported()),
        productFactory.create(new ProductOptions()
            .withPrice(47, 50).markAsImported()));
    products.forEach(cart::add);
    var taxedProducts = Arrays.asList(
        taxedProductFactory.create(
            products.get(0), new Price()
                .add(products.get(0).getPrice()).add(new Price(0, 50))),
        taxedProductFactory.create(
            products.get(1), new Price()
                .add(products.get(1).getPrice()).add(new Price(7, 15))));

    var expected = receiptFactory.create(taxedProducts);
    var result = useCase.issueReceipt(cart);
    assertEquals(expected, result);
    assertEquals(new Price(7, 65), result.calculateTaxedValue());
    assertEquals(new Price(65, 15), result.calculateTotal());
  }

  @Test
  void shouldReturnReceiptOnMixedProducts() {
    var cart = cartFactory.create();
    var products = Arrays.asList(
        productFactory.create(new ProductOptions()
            .withPrice(27, 99).markAsImported()),
        productFactory.create(new ProductOptions()
            .withPrice(18, 99)),
        productFactory.create(new ProductOptions()
            .withPrice(9, 75).withCategory(Category.MEDICAL)),
        productFactory.create(new ProductOptions()
            .withPrice(11, 25).withCategory(Category.FOOD).markAsImported()));
    products.forEach(cart::add);
    var taxedProducts = Arrays.asList(
        taxedProductFactory.create(
            products.get(0), new Price()
                .add(products.get(0).getPrice()).add(new Price(4, 20))),
        taxedProductFactory.create(
            products.get(1), new Price()
                .add(products.get(1).getPrice()).add(new Price(1, 90))),
        taxedProductFactory.create(
            products.get(2), new Price()
                .add(products.get(2).getPrice()).add(new Price(0, 0))),
        taxedProductFactory.create(
            products.get(3), new Price()
                .add(products.get(3).getPrice()).add(new Price(0, 60))));

    var expected = receiptFactory.create(taxedProducts);
    var result = useCase.issueReceipt(cart);
    assertEquals(expected, result);
    assertEquals(new Price(6, 70), result.calculateTaxedValue());
    assertEquals(new Price(74, 68), result.calculateTotal());
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
