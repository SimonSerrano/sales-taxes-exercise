package com.marmouset.receipt.entity;

import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.taxed.TaxedProduct;
import java.util.Collection;
import java.util.List;

/**
 * This class represents a receipt.
 */
public class ReceiptImpl implements Receipt {

  private final Collection<TaxedProduct> products;

  ReceiptImpl(Collection<TaxedProduct> products) {
    this.products = products;
  }

  @Override
  public Collection<TaxedProduct> getProducts() {
    return products;
  }

  @Override
  public Price calculateTaxedValue() {
    var taxedTotal = calculateTotal();
    var untaxedPrices = products.stream().map(tp -> {
      return tp.getProduct().getPrice();
    }).toList();
    var untaxedTotal = sum(untaxedPrices);

    return new Price().add(taxedTotal).substract(untaxedTotal);
  }

  @Override
  public Price calculateTotal() {
    List<Price> prices = products.stream()
        .map(TaxedProduct::getTaxedPrice).toList();
    return sum(prices);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((products == null) ? 0 : products.hashCode());
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
    ReceiptImpl other = (ReceiptImpl) obj;
    if (products == null) {
      if (other.products != null) {
        return false;
      }
    } else if (!products.equals(other.products)) {
      return false;
    }
    return true;
  }

  private Price sum(List<Price> prices) {
    var cents = prices.stream().mapToInt(Price::getCents).sum();
    var decimalsToAdd = cents / 100;
    var decimals = prices.stream()
        .mapToInt(Price::getDecimal).sum() + decimalsToAdd;
    return new Price(decimals, cents - decimalsToAdd * 100);
  }

}
