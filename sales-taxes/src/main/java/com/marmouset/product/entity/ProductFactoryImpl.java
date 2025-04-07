package com.marmouset.product.entity;

import java.util.Objects;

/**
 * This class is responsible for creating Products.
 */
public class ProductFactoryImpl implements ProductFactory {

  @Override
  public Product create(ProductOptions options) {
    Objects.requireNonNull(options, "Product options cannot be null");
    return new ProductImpl(
        options.getCategory(),
        options.getPrice(),
        options.isImported());
  }

}
