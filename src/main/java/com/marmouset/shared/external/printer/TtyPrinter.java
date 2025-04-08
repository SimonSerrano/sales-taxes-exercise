package com.marmouset.shared.external.printer;

/**
 * This class prints a string on the console output.
 */
public class TtyPrinter implements Printer {

  @Override
  public void print(String str) {
    System.out.println(str);
  }

  @Override
  public void print() {
    System.out.println();
  }

}
