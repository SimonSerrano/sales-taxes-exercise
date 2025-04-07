package com.marmouset.receipt.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.marmouset.cart.adapter.CartParser;
import com.marmouset.cart.adapter.InvalidCartFormatException;
import com.marmouset.cart.entity.CartFactory;
import com.marmouset.cart.entity.CartFactoryImpl;
import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.Category;
import com.marmouset.product.entity.ProductFactory;
import com.marmouset.product.entity.ProductFactoryImpl;
import com.marmouset.product.entity.ProductOptions;
import com.marmouset.product.taxed.entity.TaxedProductFactory;
import com.marmouset.product.taxed.entity.TaxedProductFactoryImpl;
import com.marmouset.receipt.entity.ReceiptFactory;
import com.marmouset.receipt.entity.ReceiptFactoryImpl;
import com.marmouset.receipt.usecase.IssueReceiptUseCase;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the cart controller.
 */
public class ReceiptControllerImplTest {

  private IssueReceiptUseCase useCaseMock;
  private ReceiptController controller;
  private CartFactory cartFactory;
  private ReceiptFactory receiptFactory;
  private ProductFactory productFactory;
  private TaxedProductFactory taxedProductFactory;
  private CartParser parserMock;
  private ReceiptPresenter presenterMock;

  @BeforeEach
  void setUp() {
    cartFactory = new CartFactoryImpl();
    receiptFactory = new ReceiptFactoryImpl();
    productFactory = new ProductFactoryImpl();
    taxedProductFactory = new TaxedProductFactoryImpl();
    parserMock = mock(CartParser.class);
    presenterMock = mock(ReceiptPresenter.class);
    useCaseMock = mock(IssueReceiptUseCase.class);
    controller = new ReceiptControllerImpl(
        useCaseMock,
        parserMock,
        presenterMock);
  }

  @Test
  void shouldReturnCartForSimpleGoods() throws InvalidCartFormatException {
    String cartStr = "1 book at 12.49\r\n"
        + //
        " 1 music CD at 14.99\r\n"
        + //
        " 1 chocolate bar at 0.85";

    String expected = " 1 book: 12.49" + System.lineSeparator()
        + //
        " 1 music CD: 16.49" + System.lineSeparator()
        + //
        " 1 chocolate bar: 0.85" + System.lineSeparator()
        + //
        " Sales Taxes: 1.50 Total : 29.83";

    var book = productFactory.create(new ProductOptions()
        .withDescription("book")
        .withPrice(12, 49).withCategory(Category.BOOKS));
    var musicCd = productFactory.create(new ProductOptions()
        .withDescription("music CD")
        .withPrice(14, 99));
    var chocolateBar = productFactory.create(new ProductOptions()
        .withDescription("chocolate bar")
        .withPrice(0, 85).withCategory(Category.FOOD));

    var cart = cartFactory.create()
        .add(book)
        .add(musicCd)
        .add(chocolateBar);

    var receipt = receiptFactory.create(
        Arrays.asList(
            taxedProductFactory.create(
                book, new Price(12, 49)),
            taxedProductFactory.create(
                musicCd, new Price(16, 49)),
            taxedProductFactory.create(
                chocolateBar, new Price(0, 85))));

    when(parserMock.parse(cartStr)).thenReturn(cart);
    when(useCaseMock.issueReceipt(cart))
        .thenReturn(receipt);
    when(presenterMock.present(receipt)).thenReturn(expected);
    assertEquals(expected, controller.issueReceipt(cartStr));
  }

}
