package com.aor.numbers;

import java.util.ArrayList;
import java.util.List;

public class ListFilterer {
    private List<Integer> list;

    public ListFilterer(List<Integer> list){
        this.list = list;
    }

    public List<Integer> filter(IListFilter filter){

        List<Integer> result = new ArrayList<>();

        for (int num : list)
            if (filter.accept(num))
                result.add(num);

        return result;

    }

}
