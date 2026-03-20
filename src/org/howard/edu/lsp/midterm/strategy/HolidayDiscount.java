package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday pricing.
 * Author: Skylar Brown
 */
public class HolidayDiscount implements DiscountStrategy {
    /**
     * Applies a 15% discount for holiday pricing.
     *
     * @param price the original price
     * @return the discounted price
     */
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}