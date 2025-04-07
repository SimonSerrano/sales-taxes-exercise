package com.marmouset.cart.entity;

/**
 * This class is responsible for creating Carts.
 */
public class CartFactoryImpl implements CartFactory {

  @Override
  public Cart create() {
    return new CartImpl();
  }

}
