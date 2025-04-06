package com.marmouset.product.entity;

public interface Product {
  boolean isImported();

  Category getCategory();

  float getPrice();
}
