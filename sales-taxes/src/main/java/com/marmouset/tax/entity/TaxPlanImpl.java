package com.marmouset.tax.entity;

import com.marmouset.product.entity.Category;
import com.marmouset.product.entity.Product;
import java.util.List;

/**
 * This class holds rules for the default tax plan.
 */
public class TaxPlanImpl implements TaxPlan {

  private final List<Category> exemptions;

  /**
   * Creates a tax plan with list of exempted goods.
   *
   * @param exemptions list of exempted goods
   */
  public TaxPlanImpl(List<Category> exemptions) {
    this.exemptions = exemptions;
  }

  @Override
  public int getRate(Product product) {
    if (exemptions.contains(product.getCategory())) {
      return 0;
    }
    return 10;
  }

}
