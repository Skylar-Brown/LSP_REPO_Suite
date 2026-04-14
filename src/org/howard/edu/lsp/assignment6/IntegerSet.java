package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;

/**
 * IntegerSet models a mathematical set of integers.
 * A set contains no duplicate values.
 */
public class IntegerSet {
    private ArrayList<Integer> set = new ArrayList<Integer>();

    /**
     * Default constructor.
     */
    public IntegerSet() {
    }

    /**
     * Clears the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if both sets contain exactly the same elements.
     *
     * @param b the other set
     * @return true if equal, false otherwise
     */
    public boolean equals(IntegerSet b) {
        if (b == null) {
            return false;
        }

        if (this.length() != b.length()) {
            return false;
        }

        ArrayList<Integer> thisCopy = new ArrayList<Integer>(this.set);
        ArrayList<Integer> otherCopy = new ArrayList<Integer>(b.set);

        Collections.sort(thisCopy);
        Collections.sort(otherCopy);

        return thisCopy.equals(otherCopy);
    }

    /**
     * Returns true if the set contains the value.
     *
     * @param value integer to search for
     * @return true if present, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest value in the set.
     *
     * @return largest integer
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("IntegerSet is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest value in the set.
     *
     * @return smallest integer
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("IntegerSet is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds item to the set if not already present.
     *
     * @param item integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes item from the set if present.
     *
     * @param item integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new set containing elements in either set.
     *
     * @param intSetb other set
     * @return union of this set and intSetb
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        for (Integer value : this.set) {
            result.add(value);
        }

        if (intSetb != null) {
            for (Integer value : intSetb.set) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements common to both sets.
     *
     * @param intSetb other set
     * @return intersection of this set and intSetb
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            return result;
        }

        for (Integer value : this.set) {
            if (intSetb.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements in this set but not in intSetb.
     *
     * @param intSetb other set
     * @return difference of this set and intSetb
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            for (Integer value : this.set) {
                result.add(value);
            }
            return result;
        }

        for (Integer value : this.set) {
            if (!intSetb.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements in intSetb but not in this set.
     *
     * @param intSetb other set
     * @return complement of this set relative to intSetb
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            return result;
        }

        for (Integer value : intSetb.set) {
            if (!this.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns true if the set is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns the set in ascending order.
     *
     * @return string representation in format [1, 2, 3]
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<Integer>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}