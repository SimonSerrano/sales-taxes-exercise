package com.marmouset.product.entity;

import com.marmouset.price.entity.Price;
import java.util.Objects;

/**
 * This class represents a product.
 */
public class ProductImpl implements Product {

  private final int quantity;
  private final String description;
  private final boolean isImported;
  private final Category category;
  private final Price price;

  ProductImpl(
      int quantity,
      String description,
      Category category,
      Price price,
      boolean isImported) {
    if (quantity < 1) {
      throw new IllegalArgumentException("Quantity cannot be lower than 1");
    }
    this.quantity = quantity;
    this.description = Objects.requireNonNull(
        description, "Description cannot be null");
    if (this.description.isEmpty()) {
      throw new IllegalArgumentException("Description cannot be empty");
    }
    this.isImported = isImported;
    this.category = Objects.requireNonNull(
        category,
        "A product's category cannot be null");
    this.price = Objects.requireNonNull(
        price,
        "A product's price cannot be null");
  }

  @Override
  public boolean isImported() {
    return isImported;
  }

  @Override
  public Category getCategory() {
    return category;
  }

  @Override
  public Price getPrice() {
    return price;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (isImported ? 1231 : 1237);
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
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
    ProductImpl other = (ProductImpl) obj;
    if (isImported != other.isImported) {
      return false;
    }
    if (category != other.category) {
      return false;
    }
    if (price == null) {
      if (other.price != null) {
        return false;
      }
    } else if (!price.equals(other.price)) {
      return false;
    }
    return true;
  }

  @Override
  public int getQuantity() {
    return quantity;
  }

  @Override
  public String getDescription() {
    return description;
  }

}
