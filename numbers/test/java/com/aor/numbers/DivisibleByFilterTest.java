package com.aor.numbers;

import org.junit.Test;

import static org.junit.Assert.*;


public class DivisibleByFilterTest {
    @Test
    public void test(){
        DivisibleByFilter dbf3 = new DivisibleByFilter(3);

        assertTrue(dbf3.accept(3));
        assertTrue(dbf3.accept(9));
        assertFalse(dbf3.accept(2));
        assertTrue(dbf3.accept(0));
    }
}
