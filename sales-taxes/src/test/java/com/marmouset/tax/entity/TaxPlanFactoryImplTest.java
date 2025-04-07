package com.marmouset.tax.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests tax plan factory impl.
 */
public class TaxPlanFactoryImplTest {

  private TaxPlanFactory factory;

  @BeforeEach
  void setUp() {
    factory = new TaxPlanFactoryImpl();
  }

  @Test
  void shouldThrowExceptionIfExemptionsAreNull() {
    assertThrows(NullPointerException.class, () -> factory.create(null));
  }

}
