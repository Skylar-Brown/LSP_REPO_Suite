package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 * Author: Skylar Brown
 */
public class RegularDiscount implements DiscountStrategy {
    /**
     * Returns the original price for a regular customer.
     *
     * @param price the original price
     * @return the original price
     */
    public double calculatePrice(double price) {
        return price;
    }
}