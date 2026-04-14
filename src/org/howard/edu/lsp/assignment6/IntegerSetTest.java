package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for IntegerSet.
 * Includes normal and edge cases for every method.
 */
public class IntegerSetTest {

    @Test
    public void testClearNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);

        set.clear();

        assertEquals(0, set.length());
        assertTrue(set.isEmpty());
        assertEquals("[]", set.toString());
    }

    @Test
    public void testClearEdgeCaseAlreadyEmpty() {
        IntegerSet set = new IntegerSet();

        set.clear();

        assertTrue(set.isEmpty());
        assertEquals("[]", set.toString());
    }

    @Test
    public void testLengthNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.add(3);

        assertEquals(3, set.length());
    }

    @Test
    public void testLengthEdgeCaseEmptySet() {
        IntegerSet set = new IntegerSet();

        assertEquals(0, set.length());
    }

    @Test
    public void testEqualsNormalCase() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set2.add(1);
        set2.add(2);

        assertTrue(set1.equals(set2));
    }

    @Test
    public void testEqualsEdgeCaseSameElementsDifferentOrder() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(3);
        set2.add(1);
        set2.add(2);

        assertTrue(set1.equals(set2));
    }

    @Test
    public void testEqualsEdgeCaseDifferentSets() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(1);
        set2.add(3);

        assertFalse(set1.equals(set2));
    }

    @Test
    public void testEqualsEdgeCaseNullSet() {
        IntegerSet set = new IntegerSet();
        set.add(1);

        assertFalse(set.equals(null));
    }

    @Test
    public void testContainsNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(5);

        assertTrue(set.contains(5));
    }

    @Test
    public void testContainsEdgeCaseValueNotPresent() {
        IntegerSet set = new IntegerSet();
        set.add(5);

        assertFalse(set.contains(10));
    }

    @Test
    public void testLargestNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(9);
        set.add(3);

        assertEquals(9, set.largest());
    }

    @Test
    public void testLargestEdgeCaseSingleElement() {
        IntegerSet set = new IntegerSet();
        set.add(7);

        assertEquals(7, set.largest());
    }

    @Test
    public void testLargestEdgeCaseEmptySet() {
        IntegerSet set = new IntegerSet();

        assertThrows(IllegalStateException.class, () -> set.largest());
    }

    @Test
    public void testSmallestNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(4);
        set.add(2);
        set.add(9);

        assertEquals(2, set.smallest());
    }

    @Test
    public void testSmallestEdgeCaseSingleElement() {
        IntegerSet set = new IntegerSet();
        set.add(11);

        assertEquals(11, set.smallest());
    }

    @Test
    public void testSmallestEdgeCaseEmptySet() {
        IntegerSet set = new IntegerSet();

        assertThrows(IllegalStateException.class, () -> set.smallest());
    }

    @Test
    public void testAddNormalCase() {
        IntegerSet set = new IntegerSet();

        set.add(1);
        set.add(2);

        assertEquals(2, set.length());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
    }

    @Test
    public void testAddEdgeCaseDuplicateValues() {
        IntegerSet set = new IntegerSet();

        set.add(5);
        set.add(5);

        assertEquals(1, set.length());
        assertEquals("[5]", set.toString());
    }

    @Test
    public void testRemoveNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);

        set.remove(1);

        assertFalse(set.contains(1));
        assertEquals(1, set.length());
    }

    @Test
    public void testRemoveEdgeCaseValueNotPresent() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);

        set.remove(10);

        assertEquals(2, set.length());
        assertEquals("[1, 2]", set.toString());
    }

    @Test
    public void testUnionNormalCase() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(2);
        set2.add(3);

        IntegerSet result = set1.union(set2);

        assertEquals("[1, 2, 3]", result.toString());
        assertEquals("[1, 2]", set1.toString());
        assertEquals("[2, 3]", set2.toString());
    }

    @Test
    public void testUnionEdgeCaseWithEmptySet() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet emptySet = new IntegerSet();

        set1.add(1);
        set1.add(2);

        IntegerSet result = set1.union(emptySet);

        assertEquals("[1, 2]", result.toString());
        assertEquals("[1, 2]", set1.toString());
        assertEquals("[]", emptySet.toString());
    }

    @Test
    public void testIntersectNormalCase() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(3);
        set2.add(4);

        IntegerSet result = set1.intersect(set2);

        assertEquals("[2, 3]", result.toString());
    }

    @Test
    public void testIntersectEdgeCaseNoCommonElements() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(3);
        set2.add(4);

        IntegerSet result = set1.intersect(set2);

        assertEquals("[]", result.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDiffNormalCase() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);

        IntegerSet result = set1.diff(set2);

        assertEquals("[1, 3]", result.toString());
    }

    @Test
    public void testDiffEdgeCaseIdenticalSets() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(1);
        set2.add(2);

        IntegerSet result = set1.diff(set2);

        assertEquals("[]", result.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testComplementNormalCase() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(1);
        set2.add(2);
        set2.add(3);

        IntegerSet result = set1.complement(set2);

        assertEquals("[3]", result.toString());
    }

    @Test
    public void testComplementEdgeCaseDisjointSets() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(3);
        set2.add(4);

        IntegerSet result = set1.complement(set2);

        assertEquals("[3, 4]", result.toString());
    }

    @Test
    public void testIsEmptyNormalCaseEmptySet() {
        IntegerSet set = new IntegerSet();

        assertTrue(set.isEmpty());
    }

    @Test
    public void testIsEmptyEdgeCaseNonEmptySet() {
        IntegerSet set = new IntegerSet();
        set.add(100);

        assertFalse(set.isEmpty());
    }

    @Test
    public void testToStringNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(1);
        set.add(2);

        assertEquals("[1, 2, 3]", set.toString());
    }

    @Test
    public void testToStringEdgeCaseEmptySet() {
        IntegerSet set = new IntegerSet();

        assertEquals("[]", set.toString());
    }
}