package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product record from products.csv.
 * Encapsulates fields so parsing and transformation logic can be separated.
 */
public class Product {
  private final String productId;
  private final String name;
  private final BigDecimal price;
  private final String category;

  /**
   * Constructs a Product.
   *
   * @param productId ProductID as a String (validated elsewhere)
   * @param name product name
   * @param price product price
   * @param category product category
   */
  public Product(String productId, String name, BigDecimal price, String category) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.category = category;
  }

  public String getProductId() {
    return productId;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getCategory() {
    return category;
  }
}