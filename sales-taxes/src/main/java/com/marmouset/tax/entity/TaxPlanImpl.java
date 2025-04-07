package com.marmouset.tax.entity;

import com.marmouset.product.entity.Product;

/**
 * This class holds rules for the default tax plan.
 */
public class TaxPlanImpl implements TaxPlan {

  @Override
  public int getRate(Product product) {
    return 10;
  }

}
