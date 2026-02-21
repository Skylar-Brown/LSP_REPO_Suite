package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Applies the exact Assignment 2 transformation rules in the same order.
 */
public class DefaultProductTransformer implements ProductTransformer {

  /**
   * Applies A2 rules:
   * 1) Name -> uppercase
   * 2) If category == "Electronics", apply 10% discount
   * 3) Round HALF_UP to 2 decimals
   * 4) If originally Electronics and final rounded price > 500.00 => Premium Electronics
   * 5) Compute PriceRange using thresholds: <=10 Low, <=100 Medium, <=500 High, else Premium
   *
   * @param product input product
   * @return transformed row (5 fields)
   */
  @Override
  public String[] transform(Product product) {
    String productId = product.getProductId();

    // Rule 1: Convert name to UPPERCASE
    String name = product.getName().toUpperCase();

    BigDecimal price = product.getPrice();
    String category = product.getCategory();

    // Rule 2: If Electronics, apply 10% discount
    boolean wasElectronics = category.equals("Electronics");
    if (wasElectronics) {
      price = price.multiply(new BigDecimal("0.90"));
    }

    // Round price to 2 decimal places (round-half-up)
    price = price.setScale(2, RoundingMode.HALF_UP);

    // Rule 3: If price > 500 AND was Electronics, change to Premium Electronics
    if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
      category = "Premium Electronics";
    }

    // Rule 4: Determine PriceRange
    String priceRange;
    if (price.compareTo(new BigDecimal("10.00")) <= 0) {
      priceRange = "Low";
    } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
      priceRange = "Medium";
    } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
      priceRange = "High";
    } else {
      priceRange = "Premium";
    }

    return new String[] {
        productId,
        name,
        String.format("%.2f", price),
        category,
        priceRange
    };
  }
}
