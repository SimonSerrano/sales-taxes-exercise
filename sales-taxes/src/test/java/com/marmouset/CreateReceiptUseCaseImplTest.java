package com.marmouset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateReceiptUseCaseImplTest {

  private CreateReceiptUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateReceiptUseCaseImpl();
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

  }

}
