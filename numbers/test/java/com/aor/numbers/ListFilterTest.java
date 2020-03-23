package com.aor.numbers;


import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListFilterTest {

    @Test
    public void test1(){
        List<Integer> input = new ArrayList<>();
        input.add(1); input.add(-1); input.add(0);


        ListFilterer lf = new ListFilterer(input);

        PositiveFilter pf = Mockito.mock(PositiveFilter.class);
        Mockito.when(pf.accept(0)).thenReturn(false);
        Mockito.when(pf.accept(1)).thenReturn(true);
        Mockito.when(pf.accept(-1)).thenReturn(false);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);

        List<Integer> result = lf.filter(pf);

        assertEquals(expected,result);

    }

    @Test
    public void test2(){
        List<Integer> input = new ArrayList<>();
        input.add(3); input.add(0); input.add(7);

        ListFilterer lf = new ListFilterer(input);

        DivisibleByFilter dbf = Mockito.mock(DivisibleByFilter.class);
        Mockito.when(dbf.accept(3)).thenReturn(true);
        Mockito.when(dbf.accept(0)).thenReturn(true);
        Mockito.when(dbf.accept(7)).thenReturn(false);

        List<Integer> expected = new ArrayList<>();
        expected.add(3);expected.add(0);

        List<Integer> res = lf.filter(dbf);

        assertEquals(expected, res);
    }
}
