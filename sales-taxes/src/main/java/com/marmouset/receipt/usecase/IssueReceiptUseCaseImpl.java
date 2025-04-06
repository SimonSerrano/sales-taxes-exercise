package com.marmouset.receipt.usecase;

import com.marmouset.cart.entity.Cart;
import com.marmouset.receipt.entity.Receipt;
import com.marmouset.receipt.entity.ReceiptImpl;

public class IssueReceiptUseCaseImpl implements IssueReceiptUseCase {

  @Override
  public Receipt issueReceipt(Cart cart) {

    var receipt = new ReceiptImpl();
    return receipt;
  }

}
