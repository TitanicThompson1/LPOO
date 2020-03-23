package com.aor.numbers;

public class PositiveFilter implements IListFilter {

    @Override
    public boolean accept(int number) {
        return number>0;
    }
}
