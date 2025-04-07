package com.marmouset.receipt.entity;

import com.marmouset.product.entity.taxed.TaxedProduct;
import java.util.Collection;

/**
 * This class is responsible for creating receipts.
 */
public class ReceiptFactoryImpl implements ReceiptFactory {

  /**
   * Creates a receipt.
   *
   * @param products the products in the receipt, taxed
   */
  public Receipt create(Collection<TaxedProduct> products) {
    return new ReceiptImpl(products);
  }
}
