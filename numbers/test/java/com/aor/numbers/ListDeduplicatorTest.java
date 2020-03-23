package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListDeduplicatorTest {
    private List<Integer> list = new ArrayList<>();

    class StubSorter implements  IListSorter {

        @Override
        public List<Integer> sort() {
            List<Integer> li = new ArrayList<>();
            li.add(1);
            li.add(2);
            li.add(2);
            li.add(4);
            return li;
        }
    }

    @Before
    public void setup(){

        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);
    }

    @Test
    public void deduplicate() {

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);
        expected.add(5);

        ListDeduplicator deduplicator = new ListDeduplicator(list);
        List<Integer> distinct = deduplicator.deduplicate(new ListSorter(list));

        assertEquals(expected, distinct);
    }

    @Test
    public void deduplicate2() {

        List<Integer> li = new ArrayList<>();
        li.add(1);
        li.add(2);
        li.add(4);
        li.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);

        ListDeduplicator deduplicator = new ListDeduplicator(list);
        assertEquals(expected,deduplicator.deduplicate(new StubSorter()));
    }


}