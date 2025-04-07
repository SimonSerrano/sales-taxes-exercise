package com.marmouset.product.entity;

import com.marmouset.price.entity.Price;

/**
 * This interface represents a product.
 */
public interface Product {

  /**
   * get the quantity of that product.
   *
   * @return the quantity
   */
  int getQuantity();

  /**
   * Get the product description.
   *
   * @return the description
   */
  String getDescription();

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
