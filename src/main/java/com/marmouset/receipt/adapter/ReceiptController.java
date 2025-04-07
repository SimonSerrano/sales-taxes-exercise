package com.marmouset.receipt.adapter;

import com.marmouset.cart.adapter.InvalidCartFormatException;

/**
 * This interface describes how to control carts.
 */
public interface ReceiptController {
  /**
   * Issues a receipt from a parseable cart.
   *
   * @param cart the cart representation as a string
   * @return the receipt representation as a string
   * @throws InvalidCartFormatException when the cart is invalid
   */
  String issueReceipt(String cart) throws InvalidCartFormatException;
}
