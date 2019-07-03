package com.leilaodequadrinhos.api.observer.interfaces;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IObservableTask {

    List<IObserverSendEmail> observerList = new ArrayList<>();

    void add(IObserverSendEmail observer);
    void remove(IObserverSendEmail observer);
    void notifyObserver(HttpServletRequest request) throws IOException;

}
