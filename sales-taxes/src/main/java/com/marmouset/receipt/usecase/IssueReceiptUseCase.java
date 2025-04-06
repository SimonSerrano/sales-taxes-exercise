package com.marmouset.receipt.usecase;

import com.marmouset.cart.entity.Cart;
import com.marmouset.receipt.entity.Receipt;

public interface IssueReceiptUseCase {

  Receipt issueReceipt(Cart cart);
}
