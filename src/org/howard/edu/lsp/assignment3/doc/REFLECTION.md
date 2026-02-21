# REFLECTION_A3.md (Assignment 3)

## Overview
In Assignment 2, my ETL pipeline was implemented in a single Java file (`ETLPipeline.java`) with all logic inside `main()`. It successfully performed Extract, Transform, and Load for `data/products.csv` and produced `data/transformed_products.csv`. In Assignment 3, I redesigned the same solution to be more object-oriented while keeping the **exact same behavior**: same input and output paths, same transformations in the same order, and the same error handling and skipping rules.

---

## What is different about the design?
### Assignment 2 (A2)
- A2 was mostly procedural/monolithic:
  - Reading/validation logic (Extract) was embedded in `main()`.
  - Transformation rules (Transform) were embedded in `main()`.
  - Writing logic (Load) was embedded in `main()`.
  - Summary counting and console printing were also embedded in `main()`.

This worked, but it mixed multiple responsibilities, making the code harder to extend or test independently.

### Assignment 3 (A3)
A3 decomposes the pipeline into focused classes:
- **`CsvProductReader`**: Reads the CSV file, trims fields, validates row structure, skips invalid rows, and tracks counters.
- **`Product`**: Encapsulates the product fields (`ProductID`, `Name`, `Price`, `Category`) so they can be passed around as an object.
- **`ProductTransformer` (interface)**: Defines a common transformation contract.
- **`DefaultProductTransformer`**: Applies the exact A2 transformation rules, in the same order.
- **`CsvProductWriter`**: Writes the output file with the required header and row formatting.
- **`ETLPipeline`**: Orchestrates Extract → Transform → Load and returns a summary of counts.
- **`Main`**: Minimal entry point that calls the pipeline using the same relative paths and prints the same summary output format.

---

## How is Assignment 3 more object-oriented?
A3 is more object-oriented because it emphasizes:
- Objects with state (`Product`)
- Clear class responsibilities (reader, transformer, writer, pipeline)
- Interfaces and interchangeable behavior (`ProductTransformer`)

Instead of one large method doing everything, each class models a meaningful concept in the program.

---

## Which OO ideas did you use?

### Object + Class
- `Product` represents a real-world “product record” as an object with fields and getters.
- `CsvProductReader`, `CsvProductWriter`, and `ETLPipeline` are classes representing roles in the ETL workflow.

### Encapsulation
- `Product` fields are private and can only be accessed through getters.
- The parsing and validation details are encapsulated inside `CsvProductReader`.
- The output formatting and header writing are encapsulated inside `CsvProductWriter`.

### Inheritance / Polymorphism
- I used **polymorphism** through the `ProductTransformer` interface.
  - `DefaultProductTransformer` implements this interface.
  - Because the pipeline depends on the interface, it could support different transformers in the future without changing pipeline logic.
- (I did not use class inheritance beyond interface implementation because this assignment’s requirements were satisfied cleanly with composition + interfaces.)

---

## Preserving correctness (matching A2 behavior)
The most important requirement was that A3 behaves exactly like A2:
- Same relative paths:
  - Input: `data/products.csv`
  - Output: `data/transformed_products.csv`
- Same Extract behavior:
  - Skip blank lines
  - Split by comma, trim all fields
  - Require exactly 4 fields
  - Skip rows where ProductID can’t parse as an integer
  - Skip rows where Price can’t parse as a BigDecimal
- Same Transform behavior (same order):
  1) Name → uppercase  
  2) 10% discount only when category is exactly `"Electronics"`  
  3) Round HALF_UP to 2 decimals  
  4) If originally Electronics and final rounded price > 500.00 → `"Premium Electronics"`  
  5) PriceRange thresholds:
     - <= 10.00 → Low
     - <= 100.00 → Medium
     - <= 500.00 → High
     - else → Premium
- Same Load behavior:
  - Output header: `ProductID,Name,Price,Category,PriceRange`
  - Price formatted to exactly two decimals
- Same error handling and console output text:
  - Missing file prints: `Error: Input file 'data/products.csv' not found.` and exits cleanly.
  - Truly empty file prints: `Error: Input file is empty.` and exits cleanly.
  - Otherwise prints the summary in the same format as A2.

---

## How I tested A3 to confirm it matches A2
1. **Provided sample input test**
   - I ran A3 with the given `data/products.csv` sample.
   - I confirmed `data/transformed_products.csv` matched the expected output rows and formatting exactly.

2. **Missing file test**
   - I temporarily renamed `data/products.csv` so it did not exist.
   - I confirmed the program printed the same missing-file message and did not crash or print a stack trace.

3. **Empty file test**
   - I created a completely empty file at `data/products.csv`.
   - I confirmed the program printed `Error: Input file is empty.` and exited cleanly, matching A2’s behavior.

4. **Header-only test**
   - I created a file with only the header line.
   - I confirmed the program wrote the output header and printed a summary with 0 rows read/transformed/skipped.

---

## Conclusion
Assignment 3 improves the design by applying object-oriented decomposition without changing functionality. Compared to A2’s single-file approach, A3 is easier to read, test, and extend because it separates concerns into well-named classes, encapsulates data in a `Product` object, and uses a transformer interface for polymorphism—all while maintaining the exact Assignment 2 behavior and output.