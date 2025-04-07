package com.marmouset.tax.entity;

import com.marmouset.product.entity.Category;
import com.marmouset.product.entity.Product;
import java.util.List;

/**
 * This class holds rules for the default tax plan.
 */
public class TaxPlanImpl implements TaxPlan {

  private final List<Category> exemptions;

  TaxPlanImpl(List<Category> exemptions) {
    this.exemptions = exemptions;
  }

  @Override
  public int getRate(Product product) {
    var result = 10;
    if (exemptions.contains(product.getCategory())) {
      result = 0;
    }

    if (product.isImported()) {
      result += 5;
    }

    return result;
  }

}
