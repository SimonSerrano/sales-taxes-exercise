package com.marmouset.product.entity.taxed;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.Product;

/**
 * This class represents a taxed product.
 */
public class TaxedProductImpl implements TaxedProduct {

  private final Product product;
  private final Price taxedPrice;

  TaxedProductImpl(Product product, Price taxedPrice) {
    this.product = product;
    this.taxedPrice = taxedPrice;
  }

  @Override
  public Price getTaxedPrice() {
    return taxedPrice;
  }

  @Override
  public Product getProduct() {
    return product;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((product == null) ? 0 : product.hashCode());
    result = prime * result
        + ((taxedPrice == null) ? 0 : taxedPrice.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    TaxedProductImpl other = (TaxedProductImpl) obj;
    if (product == null) {
      if (other.product != null) {
        return false;
      }
    } else if (!product.equals(other.product)) {
      return false;
    }
    if (taxedPrice == null) {
      if (other.taxedPrice != null) {
        return false;
      }
    } else if (!taxedPrice.equals(other.taxedPrice)) {
      return false;
    }
    return true;
  }

}
