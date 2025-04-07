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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((products == null) ? 0 : products.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CartImpl other = (CartImpl) obj;
    if (products == null) {
      if (other.products != null) {
        return false;
      }
    } else if (!products.equals(other.products)) {
      return false;
    }
    return true;
  }

}
