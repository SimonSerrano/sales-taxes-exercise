package com.marmouset.cart.entity;

import com.marmouset.product.entity.Product;
import java.util.Collection;

/**
 * This interface represents a Cart.
 */
public interface Cart {
  /**
   * Adds a product to the cart.
   *
   * @param product the product
   * @return this
   */
  Cart add(Product product);

  /**
   * Gets the products in the cart.
   *
   * @return the products
   */
  Collection<? extends Product> getProducts();
}
