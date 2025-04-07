package com.marmouset.cart.entity;

import com.marmouset.product.entity.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * This class represents the behaviour of a cart.
 */
public class CartImpl implements Cart {

  private final Collection<Product> products;

  CartImpl() {
    products = new ArrayList<>();
  }

  @Override
  public Cart add(Product product) {
    products.add(Objects.requireNonNull(
        product, "A null product cannot be added to a cart"));
    return this;
  }

  @Override
  public Collection<? extends Product> getProducts() {
    return products;
  }

}
