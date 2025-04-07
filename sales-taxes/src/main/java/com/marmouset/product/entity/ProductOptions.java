package com.marmouset.product.entity;

import com.marmouset.price.entity.Price;

/**
 * This class is responsible for holding product creation options.
 */
public class ProductOptions {

  private Category category;
  private Price price;
  private boolean isImported;

  /**
   * Creates a ProductOptions.
   */
  public ProductOptions() {
    category = Category.ANY;
    price = null;
    isImported = false;
  }

  /**
   * Sets the product's category.
   *
   * @param category its category
   * @return this
   */
  public ProductOptions withCategory(Category category) {
    this.category = category;
    return this;
  }

  /**
   * Sets the product's price.
   *
   * @param decimal its price's decimal
   * @param cents   its price's cents
   * @return this
   */
  public ProductOptions withPrice(int decimal, int cents) {
    price = new Price(decimal, cents);
    return this;
  }

  /**
   * Marks the product as imported.
   *
   * @return this
   */
  public ProductOptions markAsImported() {
    this.isImported = true;
    return this;
  }

  public Category getCategory() {
    return category;
  }

  public boolean isImported() {
    return isImported;
  }

  public Price getPrice() {
    return price;
  }

}
