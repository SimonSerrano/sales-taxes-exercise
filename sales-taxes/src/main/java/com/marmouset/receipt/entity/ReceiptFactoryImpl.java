package com.marmouset.receipt.entity;

import java.util.Collection;

import com.marmouset.product.entity.taxed.TaxedProduct;

/**
 * This class is responsible for creating receipts.
 */
public class ReceiptFactoryImpl implements ReceiptFactory {

  public Receipt create(Collection<TaxedProduct> products) {
    return new ReceiptImpl(products);
  }
}
