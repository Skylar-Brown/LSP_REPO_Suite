package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 * Author: Skylar Brown
 */
public class MemberDiscount implements DiscountStrategy {
    /**
     * Applies a 10% discount for a member customer.
     *
     * @param price the original price
     * @return the discounted price
     */
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}