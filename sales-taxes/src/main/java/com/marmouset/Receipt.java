package com.marmouset;

import java.util.Collection;

public interface Receipt {
  Collection<TaxedProduct<?>> getProducts();

  float calculateTaxedValue();

  float calculateTotal();

  String print();
}
