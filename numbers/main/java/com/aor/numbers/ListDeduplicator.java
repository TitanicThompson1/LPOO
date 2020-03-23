package com.aor.numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * An utility class that removes duplicate numbers
 * from a list.
 */
public class ListDeduplicator implements IListDeduplicator {
    private final List<Integer> list;

    public ListDeduplicator(List<Integer> list) {
        this.list = list;
    }

    /**
     * Removes duplicate numbers from a list.
     * @return A list having the same numbers as the original
     * but without duplicates. The order of the numbers might
     * change.
     */
    @Override
    public List<Integer> deduplicate(IListSorter ils) {
        List<Integer> sorted = ils.sort();
        List<Integer> unique = new ArrayList<>();

        Integer last = null;

        for (Integer number : sorted)
            if (!number.equals(last)) {
                last = number;
                unique.add(number);
            }

        return unique;
    }
}
