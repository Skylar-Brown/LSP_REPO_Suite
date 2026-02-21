package org.howard.edu.lsp.assignment3;

/**
 * Represents a product record read from the input file.
 */
public class Product {
  private final String productId;
  private final String name;
  private final String price;
  private final String category;

  /**
   * Constructs a Product with raw string fields (already trimmed).
   *
   * @param productId product ID string
   * @param name product name string
   * @param price price string
   * @param category category string
   */
  public Product(String productId, String name, String price, String category) {
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

  public String getPrice() {
    return price;
  }

  public String getCategory() {
    return category;
  }
}