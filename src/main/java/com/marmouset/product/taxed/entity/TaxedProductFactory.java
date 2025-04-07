package com.marmouset.product.taxed.entity;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.Product;

/**
 * This interface describes how to create a taxed product.
 * Classes implementing this class are responsible for TaxedProduct creation
 */
public interface TaxedProductFactory {
  /**
   * Creates a taxed product based on a product and a taxed price.
   *
   * @param product    the actual product
   * @param taxedPrice the taxed price
   * @return the taxed product
   */
  TaxedProduct create(Product product, Price taxedPrice);
}
