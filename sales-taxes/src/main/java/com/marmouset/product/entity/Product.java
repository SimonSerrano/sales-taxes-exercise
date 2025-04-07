package com.marmouset.product.entity;

import com.marmouset.price.entity.Price;

/**
 * This interface represents a product.
 */
public interface Product {
  /**
   * Whether the product is imported.
   *
   * @return true if the product is imported
   */
  boolean isImported();

  /**
   * Get the products category.
   *
   * @return the category
   */
  Category getCategory();

  /**
   * Get the product's price.
   *
   * @return the price
   */
  Price getPrice();
}
