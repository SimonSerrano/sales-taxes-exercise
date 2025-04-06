package com.marmouset.cart.entity;

import java.util.Collection;

import com.marmouset.product.entity.Product;

public interface Cart {
  Cart add(Product product);

  Collection<? extends Product> getProducts();
}
