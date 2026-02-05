
package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ETLPipeline {
    public static void main(String[] args) {
        String inputFile = "data/products.csv";
        String outputFile = "data/transformed_products.csv";
        
        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;
        
        File input = new File(inputFile);
        if (!input.exists()) {
            System.out.println("Error: Input file '" + inputFile + "' not found.");
            return;
        }
        
        List<String[]> validRows = new ArrayList<>();
        String headerLine = null;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            headerLine = reader.readLine();
            
            if (headerLine == null) {
                System.out.println("Error: Input file is empty.");
                return;
            }
            
            String line;
            while ((line = reader.readLine()) != null) {
                rowsRead++;
                
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }
                
                String[] fields = line.split(",", -1);
                
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }
                
                if (fields.length != 4) {
                    rowsSkipped++;
                    continue;
                }
                
                try {
                    Integer.parseInt(fields[0]);
                    new BigDecimal(fields[2]);
                    validRows.add(fields);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        
        List<String[]> transformedRows = new ArrayList<>();
        
        for (String[] row : validRows) {
            String productId = row[0];
            String name = row[1];
            BigDecimal price = new BigDecimal(row[2]);
            String category = row[3];
            
            name = name.toUpperCase();
            
            boolean wasElectronics = category.equals("Electronics");
            if (wasElectronics) {
                price = price.multiply(new BigDecimal("0.90"));
            }
            
            price = price.setScale(2, RoundingMode.HALF_UP);
            
            if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
                category = "Premium Electronics";
            }
            
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
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();
            
            for (String[] row : transformedRows) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
            return;
        }
        
        System.out.println("ETL Pipeline Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputFile);
    }
}
