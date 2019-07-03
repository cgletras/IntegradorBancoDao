package com.leilaodequadrinhos.api.model.task.notification;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IObservableTask {

    void add(IObserverSendEmail observer);
    void remove(IObserverSendEmail observer);
    void notifyObserver(HttpServletRequest request) throws IOException;

}
