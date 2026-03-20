# Improved Design Using CRC Cards

## Class:
Order

## Responsibilities:
- Store order information such as customer name, email, item, and price
- Provide access to order data
- Represent a single order as a domain object

## Collaborators:
- PricingService
- ReceiptPrinter
- OrderRepository
- EmailService
- ActivityLogger

---

## Class:
PricingService

## Responsibilities:
- Calculate tax for an order
- Apply discount rules
- Compute final total

## Collaborators:
- Order

---

## Class:
ReceiptPrinter

## Responsibilities:
- Format and print a receipt
- Display customer, item, and total information

## Collaborators:
- Order
- PricingService

---

## Class:
OrderRepository

## Responsibilities:
- Save order records to a file or other storage
- Manage persistence of processed orders

## Collaborators:
- Order
- PricingService

---

## Class:
EmailService

## Responsibilities:
- Send confirmation messages to customers
- Use customer email information from the order

## Collaborators:
- Order

---

## Class:
ActivityLogger

## Responsibilities:
- Log order processing activity
- Record timestamps or other system events

## Collaborators:
- Order

---

## Class:
OrderProcessor

## Responsibilities:
- Coordinate the steps required to process an order
- Delegate specialized work to the appropriate helper classes

## Collaborators:
- Order
- PricingService
- ReceiptPrinter
- OrderRepository
- EmailService
- ActivityLogger