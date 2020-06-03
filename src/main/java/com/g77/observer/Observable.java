package com.g77.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T> {
    private List<Observer<T>> observers = new ArrayList<>();

    public Observable(){};

    public void addObserver(Observer<T> observer) { this.observers.add(observer); }

    /*public void removeObserver(Observer<T> com.g77.observer) { this.observers.remove(com.g77.observer);}*/

    public void notifyObservers(T subject){
        for (Observer<T> o : observers)
            o.changed(subject);
    }
}
