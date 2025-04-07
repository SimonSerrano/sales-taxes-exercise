package com.marmouset.receipt.adapter;

import com.marmouset.cart.adapter.CartParser;
import com.marmouset.cart.adapter.InvalidCartFormatException;
import com.marmouset.receipt.usecase.IssueReceiptUseCase;

/**
 * This class parses a cart and returns a receipt.
 */
public class ReceiptControllerImpl implements ReceiptController {

  private final IssueReceiptUseCase issueReceiptUseCase;
  private final CartParser parser;
  private final ReceiptPresenter presenter;

  /**
   * Creates a cart controller.
   *
   * @param issueReceiptUseCase the use case to issue a receipt
   * @param parser              the parser for the cart
   * @param presenter           the presenter for the receipt
   */
  public ReceiptControllerImpl(
      IssueReceiptUseCase issueReceiptUseCase,
      CartParser parser,
      ReceiptPresenter presenter) {
    this.issueReceiptUseCase = issueReceiptUseCase;
    this.parser = parser;
    this.presenter = presenter;
  }

  @Override
  public String issueReceipt(String cartStr) throws InvalidCartFormatException {
    var cart = parser.parse(cartStr);
    var receipt = issueReceiptUseCase.issueReceipt(cart);
    return presenter.present(receipt);
  }

}
