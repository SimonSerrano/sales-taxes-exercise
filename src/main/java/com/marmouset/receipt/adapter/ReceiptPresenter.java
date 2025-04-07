package com.marmouset.receipt.adapter;

import com.marmouset.receipt.entity.Receipt;

/**
 * This interface describes how to present a receipt.
 */
public interface ReceiptPresenter {

  /**
   * Present a receipt.
   *
   * @param receipt the receipt
   * @return the presented string
   */
  String present(Receipt receipt);
}
