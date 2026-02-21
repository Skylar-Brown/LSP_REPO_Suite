package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes transformed rows to transformed_products.csv.
 */
public class CsvProductWriter {

  /**
   * Writes output file with the exact header and row format used in A2.
   *
   * @param outputPath relative output path (data/transformed_products.csv)
   * @param transformedRows list of transformed rows (each length 5)
   * @throws IOException if file cannot be written
   */
  public void write(String outputPath, List<String[]> transformedRows) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
      writer.write("ProductID,Name,Price,Category,PriceRange");
      writer.newLine();

      for (String[] row : transformedRows) {
        writer.write(String.join(",", row));
        writer.newLine();
      }
    }
  }
}