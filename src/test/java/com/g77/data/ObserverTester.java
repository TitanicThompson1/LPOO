package com.g77.data;

import com.g77.observer.Observable;
import com.g77.observer.Observer;
import org.junit.Test;
import org.mockito.Mockito;

public class ObserverTester {

    public class StringObserver implements Observer<String> {

        @Override
        public void changed(String subject) {

        }
    }

    public class StringObservable extends Observable<String>{

    }

    @Test
    public void notifyTester(){
        StringObserver mockObserver = Mockito.mock(StringObserver.class);
        StringObservable mockObservable = new StringObservable();
        StringObservable spyObservable = Mockito.spy(mockObservable);

        spyObservable.addObserver(mockObserver);

        spyObservable.notifyObservers("Hello");

        Mockito.verify(mockObserver).changed("Hello");

    }
}
