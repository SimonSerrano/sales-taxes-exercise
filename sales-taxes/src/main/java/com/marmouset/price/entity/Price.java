package com.marmouset.price.entity;

/**
 * This class represents a price as a currency value.
 */
public class Price {

  private int decimal;
  private int cents;

  /**
   * Creates a zero value price.
   */
  public Price() {
    this(0, 0);
  }

  /**
   * Creates a price.
   *
   * @param decimal the decimal
   * @param cents   the cents
   */
  public Price(int decimal, int cents) {
    if (decimal < 0 || cents < 0) {
      throw new IllegalArgumentException("Decimal or cents cannot be negative");
    }

    if (cents > 99) {
      throw new IllegalArgumentException("Cents cannot me 100 or more");
    }

    this.decimal = decimal;
    this.cents = cents;
  }

  /**
   * Get the decimal part of a price.
   *
   * @return the decimal part of a price.
   */
  public int getDecimal() {
    return decimal;
  }

  /**
   * Gets the cents of a price.
   *
   * @return the cents
   */
  public int getCents() {
    return cents;
  }

  /**
   * Adds a price to this price.
   *
   * @param price the price to add
   * @return this
   */
  public Price add(Price price) {
    var cents = this.cents + price.cents;
    var forDecimals = cents / 100;
    this.decimal = this.decimal + price.decimal + forDecimals;
    this.cents = cents - forDecimals * 100;
    return this;
  }

  /**
   * Substract a price with another.
   *
   * @param price the price to substract
   * @return this
   */
  public Price substract(Price price) {
    if (!isGreaterOrEquals(price)) {
      throw new ArithmeticException(
          "Cannot substract a price greater than this one");
    }

    this.decimal = decimal - price.decimal;
    var cents = this.cents - price.cents;
    if (cents < 0) {
      this.decimal--;
      this.cents = 100 - Math.abs(cents);
    } else {
      this.cents = cents;
    }

    return this;
  }

  /**
   * Apply the given rate to the current price.
   *
   * @param rate the rate to apply
   * @return this
   */
  public Price applyRate(int rate) {
    var ratedDecimal = (this.decimal * rate);
    var decimalRemainder = ratedDecimal % 100;
    var cents = ((this.cents * rate) / 100.0) + decimalRemainder;
    // It is fine to cast to an int since cents are already constrained to be
    // between 0 and 99
    var roundedCents = (int) Math.round(cents / 5.0f) * 5;
    var decimal = ratedDecimal / 100;
    add(new Price(decimal, roundedCents));
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + decimal;
    result = prime * result + cents;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Price other = (Price) obj;
    if (decimal != other.decimal) {
      return false;
    }
    if (cents != other.cents) {
      return false;
    }
    return true;
  }

  private boolean isGreaterOrEquals(Price price) {
    if (this.decimal == price.decimal) {
      return this.cents >= price.cents;
    }

    return this.decimal >= price.decimal;
  }

}
