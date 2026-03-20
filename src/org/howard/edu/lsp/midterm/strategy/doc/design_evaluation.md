# Design Evaluation

The current `PriceCalculator` design is hard to maintain because all discount rules are placed inside one method using multiple `if` statements. This means every time a new customer type or discount rule is added, the class must be edited directly. Over time, this makes the class harder to manage and more likely to break when changes are made.

The design also violates good object-oriented structure because the class is responsible for too many discount decisions instead of delegating them to separate objects. A better approach is to use the Strategy Pattern so each discount behavior is placed in its own class. This improves extensibility, makes the code easier to test, and allows new pricing strategies to be added without changing the main calculator logic.