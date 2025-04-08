package com.marmouset;

import com.marmouset.cart.adapter.CartParserImpl;
import com.marmouset.cart.adapter.InvalidCartFormatException;
import com.marmouset.cart.entity.CartFactoryImpl;
import com.marmouset.product.entity.Category;
import com.marmouset.product.entity.ProductFactoryImpl;
import com.marmouset.product.taxed.entity.TaxedProductFactoryImpl;
import com.marmouset.receipt.adapter.ReceiptController;
import com.marmouset.receipt.adapter.ReceiptControllerImpl;
import com.marmouset.receipt.adapter.ReceiptPresenterImpl;
import com.marmouset.receipt.entity.ReceiptFactoryImpl;
import com.marmouset.receipt.usecase.IssueReceiptUseCaseImpl;
import com.marmouset.shared.external.printer.Printer;
import com.marmouset.shared.external.printer.TtyPrinter;
import com.marmouset.tax.entity.TaxPlanFactoryImpl;
import java.util.Arrays;

/**
 * This app issues a receipt from a cart given a tax plan.
 *
 */
public class App {
  /**
   * Issues a receipt from a cart, given a tax plan.
   *
   * @throws InvalidCartFormatException when the cart cannot be parsed
   * 
   */
  public static void main(String[] args) throws InvalidCartFormatException {
    var printer = new TtyPrinter();
    var taxPlanFactory = new TaxPlanFactoryImpl();
    var controller = new ReceiptControllerImpl(new IssueReceiptUseCaseImpl(
        taxPlanFactory.create(
            Arrays.asList(
                Category.BOOKS,
                Category.FOOD,
                Category.MEDICAL)),
        new ReceiptFactoryImpl(),
        new TaxedProductFactoryImpl()),
        new CartParserImpl(
            new CartFactoryImpl(),
            new ProductFactoryImpl()),
        new ReceiptPresenterImpl());

    printExample(printer,
        "Example 1", " 1 book at 12.49\r\n"
            + //
            " 1 music CD at 14.99\r\n"
            + //
            " 1 chocolate bar at 0.85",
        controller);

    printExample(printer,
        "Example 2", " 1 imported box of chocolates at 10.00\r\n"
            + //
            " 1 imported bottle of perfume at 47.50",
        controller);

    printExample(printer,
        "Example 3", " 1 imported bottle of perfume at 27.99\r\n"
            + //
            " 1 bottle of perfume at 18.99\r\n"
            + //
            " 1 packet of headache pills at 9.75\r\n"
            + //
            " 1 box of imported chocolates at 11.25",
        controller);
  }

  private static void printExample(
      Printer printer,
      String exTitle,
      String input,
      ReceiptController controller) throws InvalidCartFormatException {
    printer.print(
        exTitle + " ===========================================");
    printer.print();
    printer.print();
    printer.print();
    printer.print(input);
    printer.print();
    printer.print();
    printer.print(controller.issueReceipt(input));
    printer.print();
    printer.print();
    printer.print();
  }
}
