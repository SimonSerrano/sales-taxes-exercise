package com.marmouset.receipt.entity;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.taxed.TaxedProduct;
import java.util.Collection;

/**
 * This interface represents a receipt of a cart after tax.
 */
public interface Receipt {
  /**
   * Get the products of the receipt.
   *
   * @return the taxed products
   */
  Collection<TaxedProduct> getProducts();

  /**
   * Calculate the taxed value.
   *
   * @return the taxed price
   */
  Price calculateTaxedValue();

  /**
   * Calculate the total price of the receipt.
   *
   * @return the total price
   */
  Price calculateTotal();
}
