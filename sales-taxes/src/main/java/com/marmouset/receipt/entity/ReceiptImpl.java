package com.marmouset.receipt.entity;

import java.util.Collection;

import com.marmouset.product.entity.TaxedProduct;

public class ReceiptImpl implements Receipt {

  @Override
  public Collection<TaxedProduct<?>> getProducts() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getProducts'");
  }

  @Override
  public float calculateTaxedValue() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateTaxedValue'");
  }

  @Override
  public float calculateTotal() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateTotal'");
  }

  @Override
  public String print() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'print'");
  }

}
