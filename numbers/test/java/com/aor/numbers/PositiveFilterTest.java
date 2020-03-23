package com.aor.numbers;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class PositiveFilterTest {
    private List<Integer> list;

    @Test
    public void test1(){
        PositiveFilter pf = new PositiveFilter();

        assertFalse(pf.accept(0));
        assertTrue(pf.accept(1));
        assertFalse(pf.accept(-1));

    }
}
