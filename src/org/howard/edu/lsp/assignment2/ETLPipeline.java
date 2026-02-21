package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ETLPipeline {
    public static void main(String[] args) {
        // File paths
        String inputFile = "data/products.csv";
        String outputFile = "data/transformed_products.csv";

        // Counters for Summary
        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        // Extract - Read the CSV + Error handling for missing file 

        // Check if input file exists
        File input = new File(inputFile);
        if (!input.exists()) {
            System.out.println("Error: Input file '" + inputFile + "' not found.");
            return;
        }

        List<String[]> validRows = new ArrayList<>();
        String headerLine = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            headerLine = reader.readLine(); // read header

            if (headerLine == null) {
                System.out.println("Error: Input file is empty.");
                return;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                rowsRead++;
                
                // Skip blank lines
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }
                
                // Parse the line
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
                
                // Try to parse ProductID and Price
                try {
                    Integer.parseInt(fields[0]); // ProductID
                    new BigDecimal(fields[2]);   // Price
                    validRows.add(fields);       // Valid row
                } catch (NumberFormatException e) { 
                    rowsSkipped++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Transform - Apply Rules
        List<String[]> transformedRows = new ArrayList<>();

        for (String[] row : validRows) {
            String productId = row[0];
            String name = row[1];
            BigDecimal price = new BigDecimal(row[2]);
            String category = row[3];

            // Rule 1: Convert name to UPPERCASE
            name = name.toUpperCase(); 

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

            // Create transformed row
            String[] transformedRow = {
                productId,
                name,
                String.format("%.2f", price), 
                category,
                priceRange
            };

            transformedRows.add(transformedRow);
            rowsTransformed++;
        }

        // Load - Write output
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Write header
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            // Write transformed rows
            for (String[] row : transformedRows) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
            return;
        }
        
        // Print Summary
        System.out.println("ETL Pipeline Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputFile);
    }
}