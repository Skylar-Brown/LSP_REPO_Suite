package org.howard.edu.lsp.assignment5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test cases for IntegerSet.
 * Covers all required methods including edge cases.
 */
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;

    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    // ─── clear() ────────────────────────────────────────────────────────────────

    @Test
    public void testClearNonEmpty() {
        set1.add(1); set1.add(2);
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    @Test
    public void testClearAlreadyEmpty() {
        set1.clear(); // should not throw
        assertTrue(set1.isEmpty());
    }

    // ─── length() ───────────────────────────────────────────────────────────────

    @Test
    public void testLengthEmpty() {
        assertEquals(0, set1.length());
    }

    @Test
    public void testLengthAfterAdds() {
        set1.add(10); set1.add(20); set1.add(30);
        assertEquals(3, set1.length());
    }

    // ─── equals() ───────────────────────────────────────────────────────────────

    @Test
    public void testEqualsSameElements() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(3); set2.add(1); set2.add(2);
        assertTrue(set1.equals(set2));
    }

    @Test
    public void testEqualsDifferentElements() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(3);
        assertFalse(set1.equals(set2));
    }

    @Test
    public void testEqualsDifferentSizes() {
        set1.add(1); set1.add(2);
        set2.add(1);
        assertFalse(set1.equals(set2));
    }

    @Test
    public void testEqualsBothEmpty() {
        assertTrue(set1.equals(set2));
    }

    // ─── contains() ─────────────────────────────────────────────────────────────

    @Test
    public void testContainsPresent() {
        set1.add(5);
        assertTrue(set1.contains(5));
    }

    @Test
    public void testContainsAbsent() {
        set1.add(5);
        assertFalse(set1.contains(99));
    }

    @Test
    public void testContainsEmptySet() {
        assertFalse(set1.contains(1));
    }

    // ─── largest() ──────────────────────────────────────────────────────────────

    @Test
    public void testLargest() {
        set1.add(3); set1.add(1); set1.add(7); set1.add(2);
        assertEquals(7, set1.largest());
    }

    @Test
    public void testLargestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.largest());
    }

    @Test
    public void testLargestEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> set1.largest());
    }

    // ─── smallest() ─────────────────────────────────────────────────────────────

    @Test
    public void testSmallest() {
        set1.add(3); set1.add(1); set1.add(7); set1.add(2);
        assertEquals(1, set1.smallest());
    }

    @Test
    public void testSmallestSingleElement() {
        set1.add(-5);
        assertEquals(-5, set1.smallest());
    }

    @Test
    public void testSmallestEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> set1.smallest());
    }

    // ─── add() ──────────────────────────────────────────────────────────────────

    @Test
    public void testAddNoDuplicates() {
        set1.add(5); set1.add(5);
        assertEquals(1, set1.length());
    }

    @Test
    public void testAddMultipleUnique() {
        set1.add(1); set1.add(2); set1.add(3);
        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }

    // ─── remove() ───────────────────────────────────────────────────────────────

    @Test
    public void testRemoveExisting() {
        set1.add(1); set1.add(2); set1.add(3);
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
    }

    @Test
    public void testRemoveNonExistent() {
        set1.add(1);
        set1.remove(99); // should not throw
        assertEquals(1, set1.length());
    }

    // ─── union() ────────────────────────────────────────────────────────────────

    @Test
    public void testUnion() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.union(set2);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(4, result.length());
    }

    @Test
    public void testUnionDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(3);
        set1.union(set2);
        assertEquals(2, set1.length());
        assertEquals(1, set2.length());
    }

    @Test
    public void testUnionWithEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.union(set2);
        assertEquals(2, result.length());
    }

    // ─── intersect() ────────────────────────────────────────────────────────────

    @Test
    public void testIntersect() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertEquals(2, result.length());
    }

    @Test
    public void testIntersectNoCommon() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIntersectDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.intersect(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // ─── diff() ─────────────────────────────────────────────────────────────────

    @Test
    public void testDiff() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.contains(1));
        assertEquals(1, result.length());
    }

    @Test
    public void testDiffAllShared() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(2);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDiffDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2);
        set1.diff(set2);
        assertEquals(2, set1.length());
    }

    // ─── complement() ───────────────────────────────────────────────────────────

    @Test
    public void testComplement() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertTrue(result.contains(4));
        assertEquals(1, result.length());
    }

    @Test
    public void testComplementAllShared() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(2);
        IntegerSet result = set1.complement(set2);
        assertTrue(result.isEmpty());
    }

    // ─── isEmpty() ──────────────────────────────────────────────────────────────

    @Test
    public void testIsEmptyTrue() {
        assertTrue(set1.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }

    // ─── toString() ─────────────────────────────────────────────────────────────

    @Test
    public void testToStringEmpty() {
        assertEquals("[]", set1.toString());
    }

    @Test
    public void testToStringSorted() {
        set1.add(3); set1.add(1); set1.add(2);
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    public void testToStringSingleElement() {
        set1.add(7);
        assertEquals("[7]", set1.toString());
    }
}