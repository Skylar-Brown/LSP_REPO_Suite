package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * IntegerSet models a mathematical set of integers.
 * A set cannot contain duplicate values.
 * Supports standard set operations: union, intersection, difference, and complement.
 *
 * @author Student
 * @version 1.0
 */
public class IntegerSet {

    /** Internal storage for set elements. */
    private ArrayList<Integer> set = new ArrayList<>();

    /**
     * Default constructor. Creates an empty IntegerSet.
     */
    public IntegerSet() {}

    /**
     * Removes all elements from this set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set and set b contain exactly the same elements,
     * regardless of order.
     *
     * @param b the other IntegerSet to compare
     * @return true if both sets have the same elements, false otherwise
     */
    public boolean equals(IntegerSet b) {
        if (this.length() != b.length()) return false;
        ArrayList<Integer> copy = new ArrayList<>(this.set);
        Collections.sort(copy);
        ArrayList<Integer> bCopy = new ArrayList<>(b.set);
        Collections.sort(bCopy);
        return copy.equals(bCopy);
    }

    /**
     * Returns true if this set contains the specified value.
     *
     * @param value the integer to search for
     * @return true if the value is in the set, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
     *
     * @return the largest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) throw new IllegalStateException("Set is empty");
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in the set.
     *
     * @return the smallest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) throw new IllegalStateException("Set is empty");
        return Collections.min(set);
    }

    /**
     * Adds an integer to the set. If the item already exists, it is not added again.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an integer from the set. If the item is not present, does nothing.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new IntegerSet containing all elements from this set and set b
     * (no duplicates).
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the union
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        for (int item : intSetb.set) {
            if (!result.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing only elements common to both this set and set b.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the intersection
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (intSetb.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in this set but NOT in set b.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the difference (this - b)
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (!intSetb.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in set b but NOT in this set.
     * This is the complement of this set with respect to set b.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the complement (b - this)
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : intSetb.set) {
            if (!this.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if the set is empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of this set in ascending order.
     * Format: [1, 2, 3] or [] for an empty set.
     *
     * @return string representation of the set
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}