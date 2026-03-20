package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 * Author: Skylar Brown
 */
public class VipDiscount implements DiscountStrategy {
    /**
     * Applies a 20% discount for a VIP customer.
     *
     * @param price the original price
     * @return the discounted price
     */
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}