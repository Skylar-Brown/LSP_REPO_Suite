package org.howard.edu.lsp.midterm.strategy;

public class Driver {
    /**
     * Runs the price calculator with different customer strategies.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();
        double price = 100.0;

        calculator.setStrategy(new RegularDiscount());
        System.out.println("REGULAR: " + calculator.calculatePrice(price));

        calculator.setStrategy(new MemberDiscount());
        System.out.println("MEMBER: " + calculator.calculatePrice(price));

        calculator.setStrategy(new VipDiscount());
        System.out.println("VIP: " + calculator.calculatePrice(price));

        calculator.setStrategy(new HolidayDiscount());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(price));
    }
}