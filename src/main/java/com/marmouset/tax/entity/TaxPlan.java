package com.marmouset.tax.entity;

import com.marmouset.product.entity.Product;

/**
 * This class represents the tax plan.
 */
public interface TaxPlan {
  /**
   * Get the tax rate depending on the product.
   *
   * @param product the product
   * @return the rate
   */
  int getRate(Product product);
}
