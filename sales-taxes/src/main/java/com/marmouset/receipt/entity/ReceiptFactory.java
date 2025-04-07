package com.marmouset.receipt.entity;

import com.marmouset.product.taxed.entity.TaxedProduct;
import java.util.Collection;

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
