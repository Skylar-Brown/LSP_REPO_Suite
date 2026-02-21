package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads products from a CSV file and applies the same validation rules as A2.
 */
public class CsvProductReader {

  /**
   * Result object holding parsed valid products plus row counters.
   */
  public static class ReadResult {
    private final List<Product> validProducts;
    private final int rowsRead;
    private final int rowsSkipped;
    private final boolean fileHadNoHeader; // true only if file is truly empty (no header line)

    /**
     * Constructs a ReadResult.
     *
     * @param validProducts list of valid products
     * @param rowsRead number of non-header lines encountered (including blank/bad)
     * @param rowsSkipped number of skipped lines
     * @param fileHadNoHeader true if headerLine was null (truly empty file)
     */
    public ReadResult(List<Product> validProducts, int rowsRead, int rowsSkipped, boolean fileHadNoHeader) {
      this.validProducts = validProducts;
      this.rowsRead = rowsRead;
      this.rowsSkipped = rowsSkipped;
      this.fileHadNoHeader = fileHadNoHeader;
    }

    public List<Product> getValidProducts() {
      return validProducts;
    }

    public int getRowsRead() {
      return rowsRead;
    }

    public int getRowsSkipped() {
      return rowsSkipped;
    }

    public boolean isFileHadNoHeader() {
      return fileHadNoHeader;
    }
  }

  /**
   * Reads products.csv, skipping invalid rows exactly like A2:
   * - counts rowsRead for each non-header line read
   * - skips blank lines
   * - splits by comma and trims fields
   * - must have exactly 4 fields
   * - ProductID must parse as int
   * - Price must parse as BigDecimal
   *
   * @param inputPath relative path (e.g., data/products.csv)
   * @return ReadResult containing valid products and counters
   * @throws IOException if file cannot be read
   */
  public ReadResult read(String inputPath) throws IOException {
    int rowsRead = 0;
    int rowsSkipped = 0;
    List<Product> validProducts = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
      String headerLine = reader.readLine(); // header
      if (headerLine == null) {
        // truly empty file
        return new ReadResult(validProducts, 0, 0, true);
      }

      String line;
      while ((line = reader.readLine()) != null) {
        rowsRead++;

        // Skip blank lines
        if (line.trim().isEmpty()) {
          rowsSkipped++;
          continue;
        }

        String[] fields = line.split(",", -1);

        // Trim whitespace from each field
        for (int i = 0; i < fields.length; i++) {
          fields[i] = fields[i].trim();
        }

        // Validate: must have exactly 4 fields
        if (fields.length != 4) {
          rowsSkipped++;
          continue;
        }

        // Try parse ProductID and Price
        try {
          Integer.parseInt(fields[0]); // ProductID
          BigDecimal price = new BigDecimal(fields[2]); // Price

          String productId = fields[0];
          String name = fields[1];
          String category = fields[3];

          validProducts.add(new Product(productId, name, price, category));
        } catch (NumberFormatException e) {
          rowsSkipped++;
        }
      }
    }

    return new ReadResult(validProducts, rowsRead, rowsSkipped, false);
  }
}