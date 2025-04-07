package com.marmouset.cart.entity;

import com.marmouset.product.entity.Product;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the behaviour of a cart.
 */
public class CartImpl implements Cart {

  private final Collection<Product> products;

  /**
   * Creates a cart.
   */
  public CartImpl() {
    products = new ArrayList<>();
  }

  @Override
  public Cart add(Product product) {
    products.add(product);
    return this;
  }

  @Override
  public Collection<? extends Product> getProducts() {
    return products;
  }

}
