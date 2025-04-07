package com.marmouset.tax.entity;

import com.marmouset.product.entity.Category;
import java.util.List;

/**
 * This interface describes how a tax plan is created.
 */
public interface TaxPlanFactory {
  /**
   * Creates a tax plan from exemptions.
   *
   * @param exemptions the exemptions
   * @return a tax plan
   */
  TaxPlan create(List<Category> exemptions);
}
