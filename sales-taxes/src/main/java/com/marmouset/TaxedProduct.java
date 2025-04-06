package com.marmouset;

public interface TaxedProduct<T extends Product> {
  T getProduct();

  float getTaxedPrice();
}
