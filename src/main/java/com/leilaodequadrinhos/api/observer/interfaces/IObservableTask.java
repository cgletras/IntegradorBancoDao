package com.leilaodequadrinhos.api.observer.interfaces;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IObservableTask {

    void add(IObserverSendEmail observer);
    void remove(IObserverSendEmail observer);
    void notifyObserver(HttpServletRequest request) throws IOException;

}
