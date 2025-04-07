package com.marmouset.product.entity;

/**
 * This interface describes how to create Products.
 * Classes implementing this interface are responsible for creating Products.
 */
public interface ProductFactory {

  /**
   * Creates a Product.
   *
   * @param options the creation options
   * @return the Product
   */
  Product create(ProductOptions options);

}
