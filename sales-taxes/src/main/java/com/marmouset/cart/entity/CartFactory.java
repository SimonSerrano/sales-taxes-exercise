package com.marmouset.cart.entity;

/**
 * Interface for the Cart factory.
 * Classes implementing this interface are responsible for creating carts
 */
public interface CartFactory {
  /**
   * Creates a Cart.
   *
   * @return a Cart
   */
  Cart create();
}
