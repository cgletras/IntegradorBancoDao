package com.leilaodequadrinhos.api.model.task.notification;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.leilaodequadrinhos.api.model.task.Task;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotificationByEmail implements Task {

    private static final String API_KEY = "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";

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Email from = new Email(request.getParameter("emailFrom"));
        String subject = request.getParameter("titleEmail");
        Email to = new Email(request.getParameter("emailTo"));
        Content content = new Content("text/plain", request.getParameter("content"));
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request requestSG = new Request();
        try {
            requestSG.setMethod(Method.POST);
            requestSG.setEndpoint("mail/send");
            requestSG.setBody(mail.build());
            Response responseSG = sg.api(requestSG);
            System.out.println(responseSG.getStatusCode());
            System.out.println(responseSG.getBody());
            System.out.println(responseSG.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
        return "E-mail enviado com sucesso";
    }
}