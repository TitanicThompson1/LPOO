package com.g77.observer;

public interface Observer<T> {
    void changed(T subject);
}
