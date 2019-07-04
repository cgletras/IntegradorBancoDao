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
                CallSendEmail(this,auctionID);
                return "Bid successfully entered";
            default:
                return "Bids could only be placed on active auctions";
        }
    }

    private void CallSendEmail(InsertBid insertBid, Long auctionID) {
        insertBid.observerList.add(request -> {
            BidDao bidDao = new BidDAO();
            List<Bid> bids = bidDao.findBidsByAuction(auctionID);
            for(Bid bid : bids){
                Email from = new Email("notification@leilaoquadrinhos.com");
                String subject = "Notificação de Lance no Leilão do Quadrinho "+ bid.getAuction().getProduct().getTitle();
                Email to = new Email(bid.getUser().getEmail());
                Content content = new Content("text/html", "<p><strong><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Ol&aacute; "+ bid.getUser().getName() +",</span></span></strong></p>\n" +
                        "\n" +
                        "<p>&nbsp;</p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Informamos que outro usu&aacute;rio cobriu seu&nbsp;lance no quadrinho "+ bid.getAuction().getProduct().getTitle() +".&nbsp;</span></span></p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Para continuar na disputa acesse nossa p&aacute;gina:&nbsp;</span></span></p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\"><a href=\"https://leilaodequadrinhos.com/leilao/"+ auctionID +"\">https://leilaodequadrinhos.com/leilao/"+ auctionID +"</a></span></span></p>\n" +
                        "\n" +
                        "<p>&nbsp;</p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Agradecemos sua participa&ccedil;&atilde;o!</span></span></p>\n" +
                        "\n" +
                        "<p>&nbsp;</p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Atenciosamente,</span></span></p>\n" +
                        "\n" +
                        "<p><strong><font face=\"comic sans ms, cursive\"><span style=\"font-size:16px\">Equipe Leil&atilde;o de Quadrinhos</span></font></strong></p>\n");
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
            }

            return "Notificações por email enviadas com sucesso";
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