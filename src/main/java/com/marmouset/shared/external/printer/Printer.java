package com.marmouset.shared.external.printer;

/**
 * This interface describes how a string would be printed.
 */
public interface Printer {

  /**
   * Prints a string.
   *
   * @param str the string to print
   */
  void print(String str);

  /**
   * Prints a blank line.
   *
   */
  void print();

}
