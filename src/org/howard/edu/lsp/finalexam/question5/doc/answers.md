# Question 5

## Heuristic 1:
Name:
Keep related data and behavior in the same class.

Explanation:
This heuristic improves readability and maintainability by ensuring that data and the methods that operate on that data are located together. In lecture, this was explained as avoiding “data-only” classes and instead designing objects that fully manage their own state and behavior. This makes code easier to understand and reduces the need for external classes to manipulate internal data.

---

## Heuristic 2:
Name:
Hide implementation details (encapsulation).

Explanation:
This heuristic improves maintainability by preventing other parts of the program from depending on internal implementation details. In lecture, this was illustrated by keeping helper methods private and only exposing necessary functionality through public methods. This allows the internal implementation to change without affecting other parts of the system.

---

## Heuristic 3:
Name:
Avoid exposing too many public methods.

Explanation:
This heuristic improves readability and reduces complexity by limiting how much of a class is accessible from outside. In lecture, this was explained as reducing the “surface area” of a class to prevent misuse and unintended interactions. A smaller, well-defined interface makes the class easier to use correctly and maintain over time.