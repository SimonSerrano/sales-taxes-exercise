package com.marmouset.product.entity.taxed;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.Product;

/**
 * This class represents a taxed product.
 */
public interface TaxedProduct {
  /**
   * Get the taxed actual product.
   *
   * @return the product
   */
  Product getProduct();

  /**
   * Get the taxed price.
   *
   * @return the taxed price
   */
  Price getTaxedPrice();
}
