package com.marmouset.receipt.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.Category;
import com.marmouset.product.entity.ProductFactory;
import com.marmouset.product.entity.ProductFactoryImpl;
import com.marmouset.product.entity.ProductOptions;
import com.marmouset.product.taxed.entity.TaxedProductFactory;
import com.marmouset.product.taxed.entity.TaxedProductFactoryImpl;
import com.marmouset.receipt.entity.ReceiptFactory;
import com.marmouset.receipt.entity.ReceiptFactoryImpl;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the receipt presenter.
 */
public class ReceiptPresenterImplTest {

  private ReceiptPresenter presenter;
  private ReceiptFactory receiptFactory;
  private TaxedProductFactory taxedProductFactory;
  private ProductFactory productFactory;

  @BeforeEach
  void setUp() {
    receiptFactory = new ReceiptFactoryImpl();
    taxedProductFactory = new TaxedProductFactoryImpl();
    productFactory = new ProductFactoryImpl();
    presenter = new ReceiptPresenterImpl();
  }

  @Test
  void shouldPresentTheReceipt() {
    var book = productFactory.create(new ProductOptions()
        .withDescription("book")
        .withPrice(12, 49).withCategory(Category.BOOKS));
    var musicCd = productFactory.create(new ProductOptions()
        .withDescription("music CD")
        .withPrice(14, 99));
    var chocolateBar = productFactory.create(new ProductOptions()
        .withDescription("chocolate bar")
        .withPrice(0, 85).withCategory(Category.FOOD));
    var receipt = receiptFactory.create(
        Arrays.asList(
            taxedProductFactory.create(
                book, new Price(12, 49)),
            taxedProductFactory.create(
                musicCd, new Price(16, 49)),
            taxedProductFactory.create(
                chocolateBar, new Price(0, 85))));
    String expected = " 1 book: 12.49" + System.lineSeparator()
        + //
        " 1 music CD: 16.49" + System.lineSeparator()
        + //
        " 1 chocolate bar: 0.85" + System.lineSeparator()
        + //
        " Sales Taxes: 1.50 Total: 29.83";
    assertEquals(expected, presenter.present(receipt));
  }

}
