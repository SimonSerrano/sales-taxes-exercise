package com.marmouset.product.entity.taxed;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.Product;

/**
 * This class is responsible for creating taxed products.
 */
public class TaxedProductFactoryImpl implements TaxedProductFactory {

  @Override
  public TaxedProduct create(
      Product product,
      Price taxedPrice) {
    return new TaxedProductImpl(product, taxedPrice);
  }

}
