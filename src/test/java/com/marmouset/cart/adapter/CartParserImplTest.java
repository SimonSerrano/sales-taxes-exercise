package com.marmouset.cart.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.marmouset.cart.entity.CartFactory;
import com.marmouset.cart.entity.CartFactoryImpl;
import com.marmouset.product.entity.Category;
import com.marmouset.product.entity.ProductFactory;
import com.marmouset.product.entity.ProductFactoryImpl;
import com.marmouset.product.entity.ProductOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the cart parser.
 */
public class CartParserImplTest {

  private CartParser parser;
  private CartFactory cartFactory;
  private ProductFactory productFactory;

  @BeforeEach
  void setUp() {
    cartFactory = new CartFactoryImpl();
    productFactory = new ProductFactoryImpl();
    parser = new CartParserImpl(cartFactory, productFactory);
  }

  @Test
  void shouldParseCart() throws InvalidCartFormatException {

    String cart = "1 book at 12.49\r\n"
        + //
        " 1 music CD at 14.99\r\n"
        + //
        " 1 chocolate bar at 0.85";

    var book = productFactory.create(new ProductOptions()
        .withDescription("book")
        .withPrice(12, 49).withCategory(Category.BOOKS));
    var musicCd = productFactory.create(new ProductOptions()
        .withDescription("music CD")
        .withPrice(14, 99));
    var chocolateBar = productFactory.create(new ProductOptions()
        .withDescription("chocolate bar")
        .withPrice(0, 85).withCategory(Category.FOOD));

    var expected = cartFactory.create()
        .add(book)
        .add(musicCd)
        .add(chocolateBar);

    assertEquals(expected, parser.parse(cart));
  }

  @Test
  void shouldParseSingleLineCart() throws InvalidCartFormatException {

    String cart = "1 book at 12.49";

    var book = productFactory.create(new ProductOptions()
        .withDescription("book")
        .withPrice(12, 49).withCategory(Category.BOOKS));

    var expected = cartFactory.create()
        .add(book);

    assertEquals(expected, parser.parse(cart));
  }

  @Test
  void shouldParseSimplePriceCart() throws InvalidCartFormatException {

    String cart = "1 book at 12";

    var book = productFactory.create(new ProductOptions()
        .withDescription("book")
        .withPrice(12, 0).withCategory(Category.BOOKS));

    var expected = cartFactory.create()
        .add(book);

    assertEquals(expected, parser.parse(cart));
  }

  @Test
  void shouldThrowInvalidFormatException() {
    String definetlyNotCart = "Not a cart";
    assertThrows(
        InvalidCartFormatException.class, () -> parser.parse(definetlyNotCart));
  }

  @Test
  void shouldThrowInvalidFormatExceptionWhenLineIsInvalid() {
    String definetlyNotCart = "1 book at 12.49\r\n"
        + //
        " 1 music CD at 14.99\r\n"
        + //
        " 1 perfume at ";
    assertThrows(
        InvalidCartFormatException.class, () -> parser.parse(definetlyNotCart));
  }

  @Test
  void shouldThrowInvalidFormatExceptionWhenStartsWithString() {
    String definetlyNotCart = "1 book at 12.49\r\n"
        + //
        " A music CD at 14.99\r\n"
        + //
        " 1 chocolate bar at 0.85";
    assertThrows(
        InvalidCartFormatException.class, () -> parser.parse(definetlyNotCart));
  }

  @Test
  void shouldThrowInvalidFormatExceptionWhenCurrencyIsInvalid() {
    String definetlyNotCart = "1 book at 12,49";
    assertThrows(
        InvalidCartFormatException.class, () -> parser.parse(definetlyNotCart));
  }

}
