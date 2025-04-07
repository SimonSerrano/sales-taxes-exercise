package com.marmouset.receipt.entity;

import java.util.Collection;

import com.marmouset.product.entity.taxed.TaxedProduct;

/**
 * This interface describes how to create receipts.
 * Classes implementing this interface are responsible for creatin Receipts
 */
public interface ReceiptFactory {
  /**
   * Creates a receipt from products.
   *
   * @param products the products
   * @return a receipt
   */
  Receipt create(Collection<TaxedProduct> products);
}
