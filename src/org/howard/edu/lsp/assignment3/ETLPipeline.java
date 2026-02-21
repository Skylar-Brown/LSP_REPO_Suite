package org.howard.edu.lsp.assignment3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Orchestrates Extract -> Transform -> Load while keeping the same behavior as A2.
 */
public class ETLPipeline {
  private final CsvProductReader reader;
  private final ProductTransformer transformer;
  private final CsvProductWriter writer;

  /**
   * Constructs an ETLPipeline with injected dependencies.
   *
   * @param reader CSV reader
   * @param transformer product transformer strategy
   * @param writer CSV writer
   */
  public ETLPipeline(CsvProductReader reader, ProductTransformer transformer, CsvProductWriter writer) {
    this.reader = reader;
    this.transformer = transformer;
    this.writer = writer;
  }

  /**
   * Runs the pipeline and returns a summary matching A2's counters.
   */
  public static class Summary {
    private final int rowsRead;
    private final int rowsTransformed;
    private final int rowsSkipped;

    public Summary(int rowsRead, int rowsTransformed, int rowsSkipped) {
      this.rowsRead = rowsRead;
      this.rowsTransformed = rowsTransformed;
      this.rowsSkipped = rowsSkipped;
    }

    public int getRowsRead() { return rowsRead; }
    public int getRowsTransformed() { return rowsTransformed; }
    public int getRowsSkipped() { return rowsSkipped; }
  }

  /**
   * Runs Extract -> Transform -> Load.
   *
   * Error handling behavior matches A2:
   * - If input file missing: print error and return null
   * - If file truly empty (no header): print error and return null
   * - If header exists but no data: writes header-only output and prints summary
   *
   * @param inputFile relative input file path
   * @param outputFile relative output file path
   * @return Summary if successful; null if it exited early due to missing/empty file
   */
  public Summary run(String inputFile, String outputFile) {
    File input = new File(inputFile);
    if (!input.exists()) {
      System.out.println("Error: Input file '" + inputFile + "' not found.");
      return null;
    }

    CsvProductReader.ReadResult readResult;
    try {
      readResult = reader.read(inputFile);
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
      return null;
    }

    if (readResult.isFileHadNoHeader()) {
      System.out.println("Error: Input file is empty.");
      return null;
    }

    List<String[]> transformedRows = new ArrayList<>();
    int rowsTransformed = 0;

    for (Product p : readResult.getValidProducts()) {
      transformedRows.add(transformer.transform(p));
      rowsTransformed++;
    }

    try {
      writer.write(outputFile, transformedRows);
    } catch (IOException e) {
      System.out.println("Error writing output file: " + e.getMessage());
      return null;
    }

    return new Summary(readResult.getRowsRead(), rowsTransformed, readResult.getRowsSkipped());
  }
}