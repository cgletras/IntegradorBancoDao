package com.leilaodequadrinhos.api.observer.interfaces;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IObserverSendEmail {

    String sendEmail(HttpServletRequest request) throws IOException;
}
