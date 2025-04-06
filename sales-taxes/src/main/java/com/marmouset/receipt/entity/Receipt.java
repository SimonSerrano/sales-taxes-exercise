package com.marmouset.receipt.entity;

import java.util.Collection;

import com.marmouset.product.entity.TaxedProduct;

public interface Receipt {
  Collection<TaxedProduct<?>> getProducts();

  float calculateTaxedValue();

  float calculateTotal();

  String print();
}
