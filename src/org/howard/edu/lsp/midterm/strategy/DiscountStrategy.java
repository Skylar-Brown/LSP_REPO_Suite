package org.howard.edu.lsp.midterm.strategy;

/**
 * Defines a strategy for calculating a discounted price.
 * Author: Skylar Brown
 */
public interface DiscountStrategy {
    /**
     * Calculates the final price after applying a discount strategy.
     *
     * @param price the original price
     * @return the final discounted price
     */
    double calculatePrice(double price);
}