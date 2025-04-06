package com.marmouset.product.entity;

public interface TaxedProduct<T extends Product> {
  T getProduct();

  float getTaxedPrice();
}
