package org.howard.edu.lsp.assignment3;

public class Main {
  public static void main(String[] args) {
    String inputFile = "data/products.csv";
    String outputFile = "data/transformed_products.csv";

    ETLPipeline pipeline = new ETLPipeline(
        new CsvProductReader(),
        new DefaultProductTransformer(),
        new CsvProductWriter()
    );

    ETLPipeline.Summary summary = pipeline.run(inputFile, outputFile);
    if (summary == null) {
      return; // matches A2 early exits
    }

    System.out.println("ETL Pipeline Summary:");
    System.out.println("Rows read: " + summary.getRowsRead());
    System.out.println("Rows transformed: " + summary.getRowsTransformed());
    System.out.println("Rows skipped: " + summary.getRowsSkipped());
    System.out.println("Output written to: " + outputFile);
  }
}