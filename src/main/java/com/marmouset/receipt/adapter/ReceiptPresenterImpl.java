package com.marmouset.receipt.adapter;

import com.marmouset.product.taxed.entity.TaxedProduct;
import com.marmouset.receipt.entity.Receipt;
import java.util.Collection;

/**
 * Presents a receipt.
 */
public class ReceiptPresenterImpl implements ReceiptPresenter {

  @Override
  public String present(Receipt receipt) {
    Collection<TaxedProduct> products = receipt.getProducts();
    StringBuilder strBuilder = new StringBuilder();
    for (TaxedProduct taxedProduct : products) {
      var product = taxedProduct.getProduct();
      strBuilder.append(" ")
          .append(product.getQuantity())
          .append(" ")
          .append(product.getDescription())
          .append(": ")
          .append(taxedProduct.getTaxedPrice().getDecimal())
          .append(".")
          .append(taxedProduct.getTaxedPrice().getCents())
          .append(System.lineSeparator());
    }

    var taxedValue = receipt.calculateTaxedValue();
    var total = receipt.calculateTotal();

    strBuilder.append(" Sales Taxes: ")
        .append(taxedValue.getDecimal())
        .append(".")
        .append(taxedValue.getCents())
        .append(" Total: ")
        .append(total.getDecimal())
        .append(".")
        .append(total.getCents());

    return strBuilder.toString();
  }

}
