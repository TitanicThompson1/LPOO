package com.aor.numbers;

public class DivisibleByFilter implements IListFilter {
    private int div;

    public DivisibleByFilter(int div) {this.div = div;}

    @Override
    public boolean accept(int number) {
        boolean eDivisivel = (number%div) == 0;

        return eDivisivel;
    }
}
