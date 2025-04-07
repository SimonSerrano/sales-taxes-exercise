package com.marmouset.product.entity.taxed;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.Product;
import java.util.Objects;

/**
 * This class is responsible for creating taxed products.
 */
public class TaxedProductFactoryImpl implements TaxedProductFactory {

  @Override
  public TaxedProduct create(
      Product product,
      Price taxedPrice) {
    return new TaxedProductImpl(
        Objects.requireNonNull(product, "Product cannot be null"),
        Objects.requireNonNull(taxedPrice, "Price cannot be null"));
  }

}
