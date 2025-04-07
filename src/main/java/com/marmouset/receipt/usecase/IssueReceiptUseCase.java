package com.marmouset.receipt.usecase;

import com.marmouset.cart.entity.Cart;
import com.marmouset.receipt.entity.Receipt;

/**
 * This class describes how to issue a receipt.
 */
public interface IssueReceiptUseCase {

  /**
   * Issue a receipt from a cart.
   *
   * @param cart the cart
   * @return the receipt
   */
  Receipt issueReceipt(Cart cart);
}
