package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListAggregatorTest {
    private List<Integer> list = new ArrayList<>();
    private IListDeduplicator stub;

    class StubListDeduplicastor implements IListDeduplicator{

        @Override
        public List<Integer> deduplicate (IListSorter ils){
            List<Integer> li = new ArrayList<>();
            li.add(1);
            li.add(2);
            li.add(4);
            return li;
        }
    }

    @Before
    public void setup(){

        this.list.add(1);
        this.list.add(2);
        this.list.add(4);
        this.list.add(2);
        this.list.add(5);
    }

    @Test
    public void sum() {

        ListAggregator aggregator = new ListAggregator(this.list);

        int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public void max() {


        ListAggregator aggregator = new ListAggregator(this.list);

        int max = aggregator.max();

        assertEquals(5, max);
    }

    @Test
    public void max_neg(){
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(-4);
        list.add(-5);

        ListAggregator aggregator = new ListAggregator(list);

        int max = aggregator.max();

        assertEquals(-1,max);
    }

    @Test
    public void min() {


        ListAggregator aggregator = new ListAggregator(this.list);

        int min = aggregator.min();

        assertEquals(1, min);
    }

    @Test
    public void distinct() {

        ListAggregator aggregator = new ListAggregator(this.list);

        int distinct = aggregator.distinct(new ListDeduplicator(list));

        assertEquals(4, distinct);
    }

    @Test
    public void distinct2() {

        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(4);
        input.add(2);


        ListAggregator aggregator = new ListAggregator(input);



        assertEquals(3,aggregator.distinct(new StubListDeduplicastor()));
    }
}