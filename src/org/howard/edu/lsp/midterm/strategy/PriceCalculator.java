package org.howard.edu.lsp.midterm.strategy;

/**
 * Uses a discount strategy to calculate a final price.
 * Author: Skylar Brown
 */
public class PriceCalculator {
    private DiscountStrategy strategy;

    /**
     * Sets the discount strategy to use.
     *
     * @param strategy the pricing strategy
     */
    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original price
     * @return the final price after applying the strategy
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}