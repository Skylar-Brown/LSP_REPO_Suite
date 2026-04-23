package org.howard.edu.lsp.finalexam.question3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for GradeCalculator.
 */
public class GradeCalculatorTest {

    @Test
    public void testAverage() {
        GradeCalculator calculator = new GradeCalculator();

        assertEquals(85.0, calculator.average(80, 85, 90), 0.001);
    }

    @Test
    public void testLetterGrade() {
        GradeCalculator calculator = new GradeCalculator();

        assertEquals("A", calculator.letterGrade(95.0));
        assertEquals("B", calculator.letterGrade(85.0));
        assertEquals("C", calculator.letterGrade(75.0));
        assertEquals("D", calculator.letterGrade(65.0));
        assertEquals("F", calculator.letterGrade(55.0));
    }

    @Test
    public void testIsPassing() {
        GradeCalculator calculator = new GradeCalculator();

        assertTrue(calculator.isPassing(70.0));
        assertFalse(calculator.isPassing(59.0));
    }

    @Test
    public void testLetterGradeBoundaryAt90() {
        GradeCalculator calculator = new GradeCalculator();

        assertEquals("A", calculator.letterGrade(90.0));
    }

    @Test
    public void testIsPassingBoundaryAt60() {
        GradeCalculator calculator = new GradeCalculator();

        assertTrue(calculator.isPassing(60.0));
    }

    @Test
    public void testAverageThrowsExceptionForNegativeScore() {
        GradeCalculator calculator = new GradeCalculator();

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.average(-1, 80, 90);
        });
    }

    @Test
    public void testAverageThrowsExceptionForScoreAbove100() {
        GradeCalculator calculator = new GradeCalculator();

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.average(80, 101, 90);
        });
    }
}