package com.marmouset.receipt.usecase;

import com.marmouset.cart.entity.Cart;
import com.marmouset.price.entity.Price;
import com.marmouset.product.entity.taxed.TaxedProduct;
import com.marmouset.product.entity.taxed.TaxedProductFactory;
import com.marmouset.receipt.entity.Receipt;
import com.marmouset.receipt.entity.ReceiptFactory;
import com.marmouset.tax.entity.TaxPlan;
import java.util.List;

/**
 * This class issues receipts from a cart.
 */
public class IssueReceiptUseCaseImpl implements IssueReceiptUseCase {

  private final TaxPlan taxPlan;
  private final ReceiptFactory receiptFactory;
  private final TaxedProductFactory taxedProductFactory;

  /**
   * Creates an issue receipt use case.
   *
   * @param taxPlan             a tax plan
   * @param receiptFactory      a receipt factory
   * @param taxedProductFactory a taxed product factory
   */
  public IssueReceiptUseCaseImpl(TaxPlan taxPlan, ReceiptFactory receiptFactory,
      TaxedProductFactory taxedProductFactory) {
    this.taxPlan = taxPlan;
    this.receiptFactory = receiptFactory;
    this.taxedProductFactory = taxedProductFactory;
  }

  @Override
  public Receipt issueReceipt(Cart cart) {
    var products = cart.getProducts();
    List<TaxedProduct> taxedProducts = products.stream().map(p -> {
      var rate = taxPlan.getRate(p);
      var taxedPrice = new Price().add(p.getPrice()).applyRate(rate);
      return taxedProductFactory.create(p, taxedPrice);
    }).toList();
    return receiptFactory.create(taxedProducts);
  }

}
