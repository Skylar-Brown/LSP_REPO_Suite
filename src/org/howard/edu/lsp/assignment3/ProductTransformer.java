package org.howard.edu.lsp.assignment3;

/**
 * Strategy interface for transforming products.
 */
public interface ProductTransformer {
  /**
   * Transforms an input product into an output row for the transformed CSV.
   *
   * @param product input product
   * @return a transformed CSV row with 5 fields:
   *         ProductID, Name, Price, Category, PriceRange
   */
  String[] transform(Product product);
}