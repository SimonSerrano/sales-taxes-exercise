package com.marmouset.tax.entity;

import com.marmouset.product.entity.Category;
import java.util.List;
import java.util.Objects;

/**
 * This class is responsible for creating tax plans.
 */
public class TaxPlanFactoryImpl implements TaxPlanFactory {

  @Override
  public TaxPlan create(List<Category> exemptions) {
    return new TaxPlanImpl(
        Objects.requireNonNull(exemptions, "Exemptions cannot be null"));
  }

}
