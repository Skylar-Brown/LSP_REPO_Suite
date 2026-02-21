# AI_PROMPTS_A3.md (Assignment 3)

Below are prompts I used with a generative AI assistant (ChatGPT) and excerpts of responses. I reviewed and adapted all suggestions to ensure they matched the exact Assignment 2 behavior (same input/output paths, transformations, and error handling).

---

## Prompt 1
**“How can I redesign my Assignment 2 ETL pipeline into a more object-oriented design while keeping the same behavior?”**

**Response excerpt (summary):**
The AI suggested decomposing the program into separate classes for reading (Extract), transforming (Transform), writing (Load), and keeping a small `Main` class as an entry point. It also suggested using a `Product` class to encapsulate product fields and a `ProductTransformer` interface to support polymorphism.

**How I used/adapted it:**
I implemented `CsvProductReader`, `DefaultProductTransformer`, `CsvProductWriter`, and `ETLPipeline` as separate classes. I used a `ProductTransformer` interface so that different transformation strategies could be swapped in later, but kept `DefaultProductTransformer` to exactly match A2 rules.

---

## Prompt 2
**“What classes make sense for an ETL pipeline? Can you suggest responsibilities for each?”**

**Response excerpt (summary):**
- Reader: parse CSV, validate rows, skip invalid lines
- Transformer: apply business rules in correct order
- Writer: output CSV header and rows
- Pipeline: orchestrate the steps and produce summary counts

**How I used/adapted it:**
I made sure the reader enforces the exact A2 validation rules:
- skip blank lines
- require exactly 4 fields
- ProductID must parse as int
- Price must parse as BigDecimal
- trim whitespace
I also preserved A2’s counting behavior and output format.

---

## Prompt 3
**“How can I incorporate encapsulation, polymorphism, and clean code in this redesign?”**

**Response excerpt (summary):**
Use a `Product` class with private fields + getters (encapsulation) and a `ProductTransformer` interface with an implementation (polymorphism). Keep each class focused on one job (single responsibility).

**How I used/adapted it:**
I created `Product` with private fields and getters. I used the `ProductTransformer` interface and `DefaultProductTransformer` implementation. I also kept one public class per file and wrote Javadocs for each public class and method.

---

## Prompt 4
**“Can you help outline what to write in my reflection comparing A2 vs A3?”**

**Response excerpt (summary):**
The AI suggested describing the monolithic nature of A2 vs. the decomposed structure of A3, naming OO concepts used, and describing tests to prove both versions match.

**How I used/adapted it:**
I wrote the reflection to specifically compare my A2 single-file approach to my A3 multi-class approach and documented tests for missing input file, empty input file, and the provided sample input.