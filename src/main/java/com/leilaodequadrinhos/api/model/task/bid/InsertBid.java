package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.BidDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.BidDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.Bid;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;
import com.leilaodequadrinhos.api.observer.interfaces.IObservableTask;
import com.leilaodequadrinhos.api.observer.interfaces.IObserverSendEmail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InsertBid implements Task, IObservableTask {

    private List<IObserverSendEmail> observerList = new ArrayList<>();
    private static final String API_KEY = "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";

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BidDao bidDao = new BidDAO();
        UserDAO userDao = new UserDAO();
        AuctionDao auctionDAO = new AuctionDAO();
        String format = "dd/MM/yyyy";
        Bid bid = new Bid();
        bid.setBidDate(new SimpleDateFormat(format).parse(request.getParameter("dateOfBid")));
        Long userID = Long.valueOf((((User) request.getSession().getAttribute("user")).getUserID()));
        User user = userDao.findById(userID);
        bid.setUser(user);

        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        Auction auction = (Auction) auctionDAO.findById(auctionID);

        bid.setBidValue(auction.getDefaultBid());
        bid.setAuction(auction);

        switch (auction.getAuctionStatus().getStatus()) {
            case "ATIVO":
                bidDao.insert(bid);
                CallSendEmail(this);
                return "Bid successfully entered";
            default:
                return "Bids could only be placed on active auctions";
        }
    }

    private void CallSendEmail(InsertBid insertBid) {
        insertBid.observerList.add(request -> {
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
        });
    }

    @Override
    public void add(IObserverSendEmail observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(IObserverSendEmail observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(HttpServletRequest request) throws IOException {
        for (IObserverSendEmail iObserverSendEmail : observerList) {
            iObserverSendEmail.sendEmail(request);
        }
    }
}