package com.marmouset.receipt.entity;

public class ReceiptFactoryImpl implements ReceiptFactory {

  public Receipt create() {
    return new ReceiptImpl();
  }
}
