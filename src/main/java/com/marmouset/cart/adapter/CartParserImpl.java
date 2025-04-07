package com.marmouset.cart.adapter;

import com.marmouset.cart.entity.Cart;
import com.marmouset.cart.entity.CartFactory;
import com.marmouset.product.adapter.CategoryKeywords;
import com.marmouset.product.entity.Category;
import com.marmouset.product.entity.Product;
import com.marmouset.product.entity.ProductFactory;
import com.marmouset.product.entity.ProductOptions;
import java.util.Arrays;
import java.util.List;

/**
 * This class is responsible for parsing the cart string value.
 */
public class CartParserImpl implements CartParser {

  private final CartFactory cartFactory;
  private final ProductFactory productFactory;

  /**
   * Creates a CartParserImpl.
   *
   * @param cartFactory    the cart factory
   * @param productFactory the product factory
   */
  public CartParserImpl(
      CartFactory cartFactory,
      ProductFactory productFactory) {
    this.cartFactory = cartFactory;
    this.productFactory = productFactory;
  }

  @Override
  public Cart parse(String cartStr) throws InvalidCartFormatException {
    var cart = cartFactory.create();

    List<String> lines = cartStr.lines().toList();
    for (String line : lines) {
      cart.add(parseLine(line));
    }
    return cart;
  }

  private Product parseLine(String line) throws InvalidCartFormatException {
    if (!line.contains(" at ")) {
      throw new InvalidCartFormatException(line);
    }
    String[] qualities = line.trim().replace(" at ", " ").split(" ");
    if (qualities.length < 3) {
      throw new InvalidCartFormatException(line);
    }
    String quantity = qualities[0];
    String price = qualities[qualities.length - 1];

    if (!isInteger(quantity) || !isCurrencyAmount(price)) {
      throw new InvalidCartFormatException(line);
    }

    String productDescription = String.join(
        " ",
        Arrays.copyOfRange(qualities, 1, qualities.length - 1));

    return createProduct(productDescription, price, quantity);
  }

  private Product createProduct(
      String productDescription,
      String price,
      String quantity) {
    var options = new ProductOptions()
        .withDescription(productDescription)
        .withQuantity(Integer.parseInt(quantity));

    if (productDescription.contains("imported")) {
      options.markAsImported();
    }

    options.withCategory(detectCategory(productDescription));

    String[] priceElements = price.split("\\.");
    options.withPrice(Integer.parseInt(priceElements[0]),
        priceElements.length == 2 ? Integer.parseInt(priceElements[1]) : 0);

    return productFactory.create(options);
  }

  private Category detectCategory(String productDescription) {
    if (containsKeyword(productDescription, CategoryKeywords.BOOK)) {
      return Category.BOOKS;
    }
    if (containsKeyword(productDescription, CategoryKeywords.FOOD)) {
      return Category.FOOD;
    }
    if (containsKeyword(productDescription, CategoryKeywords.MEDICAL)) {
      return Category.MEDICAL;
    }

    return Category.ANY;
  }

  private boolean containsKeyword(
      String productDescription,
      List<String> keywords) {
    String lowerCaseProductDesc = productDescription.toLowerCase();
    for (String keyword : keywords) {
      if (lowerCaseProductDesc.contains(keyword.toLowerCase())) {
        return true;
      }
    }

    return false;
  }

  private boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isCurrencyAmount(String str) {
    return str.matches("^[0-9]+(\\.[0-9]{1,2})?$");
  }

}
