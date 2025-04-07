package com.marmouset.cart.adapter;

import com.marmouset.cart.entity.Cart;

/**
 * This interface describes how a cart string is parsed.
 */
public interface CartParser {
  /**
   * Parses a cart string.
   *
   * @param cart the cart to parse
   * @return a cart
   */
  Cart parse(String cart) throws InvalidCartFormatException;
}
