package com.leilaodequadrinhos.api.model.task.notification;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IObserverSendEmail {

    String sendEmail(HttpServletRequest request) throws IOException;
}
