package com.marmouset.receipt.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marmouset.cart.entity.CartFactory;
import com.marmouset.cart.entity.CartFactoryImpl;
import com.marmouset.receipt.entity.ReceiptFactory;
import com.marmouset.receipt.entity.ReceiptFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IssueReceiptUseCaseImplTest {

  private IssueReceiptUseCase useCase;

  private CartFactory cartFactory;
  private ReceiptFactory receiptFactory;

  @BeforeEach
  void setUp() {
    cartFactory = new CartFactoryImpl();
    receiptFactory = new ReceiptFactoryImpl();
    useCase = new IssueReceiptUseCaseImpl();
  }

  @Test
  void shouldReturnReceiptOnSimpleProducts() {
  }

  @Test
  void shouldReturnReceiptOnExemptedProducts() {

  }

  @Test
  void shouldReturnReceiptOnImportedProducts() {

  }

  @Test
  void shouldReturnReceiptOnMixedProducts() {

  }

  /**
   * Theres no specification on empty carts, I chose to return an exception
   * because empty carts should not happen in the domain.
   */
  @Test
  void shouldThrowOnEmptyCart() {
    var cart = cartFactory.create();
    var expected = receiptFactory.create();
    assertEquals(expected, useCase.issueReceipt(cart));
  }

}
