package com.marmouset.cart.adapter;

/**
 * Class to represent an invalid cart format exception.
 */
public class InvalidCartFormatException extends Exception {
  /**
   * Creates an InvalidCartFormatException.
   *
   * @param cart the cart
   */
  public InvalidCartFormatException(String cart) {
    super(String.format("Invalid cart string: %s", cart));
  }
}
